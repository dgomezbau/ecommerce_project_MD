/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Customer;
import entity.Invoice;
import entity.Order;
import entity.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named(value = "cart")
@SessionScoped

public class Cart implements Serializable {

    private Map<Product, Integer> productsAndQuantity = new HashMap();

    private Order order;

    private int amount = 1;

    private int lastAmount = 0;

    @Inject
    private Control ctrl;

    public Map<Product, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<Product> listProducts() {
        return productsAndQuantity.keySet();
    }

    public Collection<Integer> listQuantity() {
        return productsAndQuantity.values();
    }

    public void replaceProduct(Product prod) {

        productsAndQuantity.replace(prod, amount);

    }

    public Product removeProductAll(Product prod) {
        Product pcoin = null;

        if (productsAndQuantity.isEmpty()) {
            return null;
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.equals(prod)) {
                    pcoin = p;
                }
            }
            if (pcoin != null) {
                productsAndQuantity.remove(pcoin);
                return pcoin;
            }
            redirect("../cart/cartList.jsf");
            return null;
        }
    }

    public void addProduct(Product prod) {

        Product pcoin = null;

        if (productsAndQuantity.isEmpty()) {
            productsAndQuantity.put(prod, amount);
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.equals(prod)) {
                    pcoin = p;
                }
            }
        }
        if (pcoin != null) {
            productsAndQuantity.replace(pcoin, productsAndQuantity.get(pcoin) + amount);
        } else {
            productsAndQuantity.put(prod, amount);
        }

        this.amount = 1;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        Product prod = null;

        for (Product p : productsAndQuantity.keySet()) {
            prod = p;
            totalPrice = totalPrice + (prod.getPrice() * productsAndQuantity.get(prod));
        }
        return totalPrice;
    }

    public void generateOrder() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Customer customer = em.find(Customer.class, ctrl.getIdUser());

        List<Product> prodList = new ArrayList();
        Product prod = null;
        double totalPrice = 0;
        for (Product p : productsAndQuantity.keySet()) {
            prodList.add(p);
            prod = p;
            totalPrice = totalPrice + (prod.getPrice() * productsAndQuantity.get(prod));
        }

        Order ord = new Order();
        Invoice invoice = new Invoice();

        ord.setCustId(customer.getCustId());
        ord.setTotPrice(totalPrice);
        ord.setOrderDesc("Test Description");
        ord.setOrderDt(new Date());
        ord.setProductList(prodList);
        ord.setUpdatedTime(new Date());
        ord.setOrderDesc("Your adquisition on e-Toiler Paper on " + new Date());

        invoice.setAmountDue(totalPrice);
        invoice.setOrder(ord);
        invoice.setOrderRaisedDt(new Date());
        invoice.setUpdatedTime(new Date());

        ord.setInvoice(invoice);

        this.setOrder(ord);

        em.close();
        entityManagerFactory.close();

        redirect("../cart/orderDetail.jsf");
    }

    public void pay() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        em.close();
        entityManagerFactory.close();
        redirect("../home/homePage.jsf");
        clearCartAfterPay();

    }

    public Customer obtainOrderCustomer() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();
        long cusID = order.getCustId();
        Customer customer = em.find(Customer.class, cusID);
        return customer;
    }

    public void clearCart() {
        productsAndQuantity.clear();
        redirect("cartList.jsf");
    }

    private void clearCartAfterPay() {
        productsAndQuantity.clear();
    }

    public int productsCount() {
        return productsAndQuantity.size();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Control getCtrl() {
        return ctrl;
    }

    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }

    public int getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
    }

    private void redirect(String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + path);
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
