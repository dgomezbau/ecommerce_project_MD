/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControllers;

import JPAControllers.exceptions.IllegalOrphanException;
import JPAControllers.exceptions.NonexistentEntityException;
import entity.Customer;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Order;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Daniel Gomez
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) {
        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<Order>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Order> attachedOrders = new ArrayList<Order>();
            for (Order ordersOrderToAttach : customer.getOrders()) {
                ordersOrderToAttach = em.getReference(ordersOrderToAttach.getClass(), ordersOrderToAttach.getOrderId());
                attachedOrders.add(ordersOrderToAttach);
            }
            customer.setOrders(attachedOrders);
            em.persist(customer);
            for (Order ordersOrder : customer.getOrders()) {
                Customer oldCustomerOfOrdersOrder = ordersOrder.getCustomer();
                ordersOrder.setCustomer(customer);
                ordersOrder = em.merge(ordersOrder);
                if (oldCustomerOfOrdersOrder != null) {
                    oldCustomerOfOrdersOrder.getOrders().remove(ordersOrder);
                    oldCustomerOfOrdersOrder = em.merge(oldCustomerOfOrdersOrder);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustId());
            Collection<Order> ordersOld = persistentCustomer.getOrders();
            Collection<Order> ordersNew = customer.getOrders();
            List<String> illegalOrphanMessages = null;
            for (Order ordersOldOrder : ordersOld) {
                if (!ordersNew.contains(ordersOldOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order " + ordersOldOrder + " since its customer field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Order> attachedOrdersNew = new ArrayList<Order>();
            for (Order ordersNewOrderToAttach : ordersNew) {
                ordersNewOrderToAttach = em.getReference(ordersNewOrderToAttach.getClass(), ordersNewOrderToAttach.getOrderId());
                attachedOrdersNew.add(ordersNewOrderToAttach);
            }
            ordersNew = attachedOrdersNew;
            customer.setOrders(ordersNew);
            customer = em.merge(customer);
            for (Order ordersNewOrder : ordersNew) {
                if (!ordersOld.contains(ordersNewOrder)) {
                    Customer oldCustomerOfOrdersNewOrder = ordersNewOrder.getCustomer();
                    ordersNewOrder.setCustomer(customer);
                    ordersNewOrder = em.merge(ordersNewOrder);
                    if (oldCustomerOfOrdersNewOrder != null && !oldCustomerOfOrdersNewOrder.equals(customer)) {
                        oldCustomerOfOrdersNewOrder.getOrders().remove(ordersNewOrder);
                        oldCustomerOfOrdersNewOrder = em.merge(oldCustomerOfOrdersNewOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = customer.getCustId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Order> ordersOrphanCheck = customer.getOrders();
            for (Order ordersOrphanCheckOrder : ordersOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Order " + ordersOrphanCheckOrder + " in its orders field has a non-nullable customer field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer findCustomer(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
