/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControllers;

import JPAControllers.exceptions.IllegalOrphanException;
import JPAControllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Invoice;
import entity.Customer;
import entity.Order;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Daniel Gomez
 */
public class OrderJpaController implements Serializable {

    public OrderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Order order) throws IllegalOrphanException {
        if (order.getProductList() == null) {
            order.setProductList(new ArrayList<Product>());
        }
        List<String> illegalOrphanMessages = null;
        Invoice invoiceOrphanCheck = order.getInvoice();
        if (invoiceOrphanCheck != null) {
            Order oldOrderOfInvoice = invoiceOrphanCheck.getOrder();
            if (oldOrderOfInvoice != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Invoice " + invoiceOrphanCheck + " already has an item of type Order whose invoice column cannot be null. Please make another selection for the invoice field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invoice invoice = order.getInvoice();
            if (invoice != null) {
                invoice = em.getReference(invoice.getClass(), invoice.getInvoiceId());
                order.setInvoice(invoice);
            }
            Customer customer = order.getCustomer();
            if (customer != null) {
                customer = em.getReference(customer.getClass(), customer.getCustId());
                order.setCustomer(customer);
            }
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : order.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getProdId());
                attachedProductList.add(productListProductToAttach);
            }
            order.setProductList(attachedProductList);
            em.persist(order);
            if (invoice != null) {
                invoice.setOrder(order);
                invoice = em.merge(invoice);
            }
            if (customer != null) {
                customer.getOrders().add(order);
                customer = em.merge(customer);
            }
            for (Product productListProduct : order.getProductList()) {
                productListProduct.getOrderList().add(order);
                productListProduct = em.merge(productListProduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Order order) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order persistentOrder = em.find(Order.class, order.getOrderId());
            Invoice invoiceOld = persistentOrder.getInvoice();
            Invoice invoiceNew = order.getInvoice();
            Customer customerOld = persistentOrder.getCustomer();
            Customer customerNew = order.getCustomer();
            List<Product> productListOld = persistentOrder.getProductList();
            List<Product> productListNew = order.getProductList();
            List<String> illegalOrphanMessages = null;
            if (invoiceOld != null && !invoiceOld.equals(invoiceNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Invoice " + invoiceOld + " since its order field is not nullable.");
            }
            if (invoiceNew != null && !invoiceNew.equals(invoiceOld)) {
                Order oldOrderOfInvoice = invoiceNew.getOrder();
                if (oldOrderOfInvoice != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Invoice " + invoiceNew + " already has an item of type Order whose invoice column cannot be null. Please make another selection for the invoice field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (invoiceNew != null) {
                invoiceNew = em.getReference(invoiceNew.getClass(), invoiceNew.getInvoiceId());
                order.setInvoice(invoiceNew);
            }
            if (customerNew != null) {
                customerNew = em.getReference(customerNew.getClass(), customerNew.getCustId());
                order.setCustomer(customerNew);
            }
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getProdId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            order.setProductList(productListNew);
            order = em.merge(order);
            if (invoiceNew != null && !invoiceNew.equals(invoiceOld)) {
                invoiceNew.setOrder(order);
                invoiceNew = em.merge(invoiceNew);
            }
            if (customerOld != null && !customerOld.equals(customerNew)) {
                customerOld.getOrders().remove(order);
                customerOld = em.merge(customerOld);
            }
            if (customerNew != null && !customerNew.equals(customerOld)) {
                customerNew.getOrders().add(order);
                customerNew = em.merge(customerNew);
            }
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    productListOldProduct.getOrderList().remove(order);
                    productListOldProduct = em.merge(productListOldProduct);
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    productListNewProduct.getOrderList().add(order);
                    productListNewProduct = em.merge(productListNewProduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = order.getOrderId();
                if (findOrder(id) == null) {
                    throw new NonexistentEntityException("The order with id " + id + " no longer exists.");
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
            Order order;
            try {
                order = em.getReference(Order.class, id);
                order.getOrderId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The order with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Invoice invoiceOrphanCheck = order.getInvoice();
            if (invoiceOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Order (" + order + ") cannot be destroyed since the Invoice " + invoiceOrphanCheck + " in its invoice field has a non-nullable order field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Customer customer = order.getCustomer();
            if (customer != null) {
                customer.getOrders().remove(order);
                customer = em.merge(customer);
            }
            List<Product> productList = order.getProductList();
            for (Product productListProduct : productList) {
                productListProduct.getOrderList().remove(order);
                productListProduct = em.merge(productListProduct);
            }
            em.remove(order);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Order> findOrderEntities() {
        return findOrderEntities(true, -1, -1);
    }

    public List<Order> findOrderEntities(int maxResults, int firstResult) {
        return findOrderEntities(false, maxResults, firstResult);
    }

    private List<Order> findOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order.class));
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

    public Order findOrder(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order> rt = cq.from(Order.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
