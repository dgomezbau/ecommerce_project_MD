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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named(value = "cart")
@SessionScoped

public class Cart implements Serializable {

    private static Map<Product, Integer> productsAndQuantity = new HashMap();

    private Order order;

    private int amount = 1;

    public void retriveCartData() {

    }

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

    public Product removeProductOne(Product prod) {
        long prodId = prod.getProdId();
        if (productsAndQuantity.isEmpty()) {
            return null;
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.getProdId() == prodId) {
                    if (productsAndQuantity.get(p) == 1) {
                        productsAndQuantity.remove(p);
                    } else {
                        productsAndQuantity.replace(p, productsAndQuantity.get(p) - 1);
                    }
                }
            }
            return prod;
        }
    }

    public Product removeProductAll(Product prod) {
        long prodId = prod.getProdId();
        if (productsAndQuantity.isEmpty()) {
            return null;
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.getProdId() == prodId) {
                    productsAndQuantity.remove(p);
                }
            }
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "../cart/cartList.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prod;

        }
    }

    public void addProduct(Product prod) {
        long prodId = prod.getProdId();
        if (productsAndQuantity.isEmpty()) {
            productsAndQuantity.put(prod, amount);
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.getProdId() == prodId) {
                    productsAndQuantity.replace(p, productsAndQuantity.get(p) + amount);
                } else {
                    productsAndQuantity.put(prod, amount);
                }
            }
        }
        this.amount = 1;
        System.err.println("Cantidad=" + this.amount + " prod id =" + prod.getProdId());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "../cart/cartList.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        Product prod = null;
        for (Product p : productsAndQuantity.keySet()) {
            prod = p;
            totalPrice = totalPrice + (Double.parseDouble(prod.getPrice()) * productsAndQuantity.get(prod));
        }
        return totalPrice;
    }

    public void generateOrder() {
        //ctrl.setIdUser(100);
        //long cusID = ctrl.getIdUser();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Customer customer = em.find(Customer.class, 110L);

        List<Product> prodList = new ArrayList();
        Product prod = null;
        double totalPrice = 0;
        for (Product p : productsAndQuantity.keySet()) {
            prodList.add(p);
            prod = p;
            totalPrice = totalPrice + (Double.parseDouble(prod.getPrice()) * productsAndQuantity.get(prod));
        }
        Order ord = new Order();
        Invoice invoice = new Invoice();

        ord.setCustId(customer.getCustId());
        ord.setTotPrice(totalPrice);
        ord.setOrderDesc("Test Description");
        ord.setOrderDt(new Date());
        ord.setProductList(prodList);
        ord.setUpdatedTime(new Date());

        invoice.setAmountDue(totalPrice);
        invoice.setOrder(ord);
        invoice.setOrderRaisedDt(new Date());
        invoice.setUpdatedTime(new Date());

        ord.setInvoice(invoice);

        this.setOrder(ord);
        
        System.err.println(this.getOrder().getCustId());

        em.close();
        entityManagerFactory.close();
        
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "../cart/orderDetail.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
