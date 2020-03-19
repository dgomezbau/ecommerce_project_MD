/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Order;
import entity.Product;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Named(value = "cart")
@SessionScoped

public class Cart implements Serializable{

    private static Map<Product, Integer> productsAndQuantity = new HashMap();
    
    private static Order order;

    /*public Product sacar(Product p) {
        int n = products.indexOf(p);
        return (n < 0) ? null : (Product) products.get(n);
    }

    public Product sacar(int c) {
        Product p = new Product();
        p.setProdId(c);
        return (Product) products.get(c);

    }*/
    public Map<Product, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Set<Product> listProducts(){
        return productsAndQuantity.keySet();
    }
    
    public Collection<Integer> listQuantity(){
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
            return prod;
        }
    }

    public void addProduct(Product prod) {
        long prodId = prod.getProdId();
        if (productsAndQuantity.isEmpty()) {
            productsAndQuantity.put(prod, 1);
        } else {
            for (Product p : productsAndQuantity.keySet()) {
                if (p.getProdId() == prodId) {
                    productsAndQuantity.replace(p, productsAndQuantity.get(p) + 1);
                } else {
                    productsAndQuantity.put(prod, 1);
                }
            }
        }

    }

    public void clearCart() {
        productsAndQuantity.clear();
    }

    public int productsCount() {
        return productsAndQuantity.size();
    }
}
