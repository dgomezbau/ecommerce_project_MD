/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Order;
import entity.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
        this.amount=1;
        System.err.println("Cantidad=" + this.amount + " prod id =" + prod.getProdId());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "../cart/cartList.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generateOrder(){
        
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
