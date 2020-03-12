/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Order;
import entity.Product;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel Gomez
 */
public class Cart {

    private Map<Product, Integer> products = new HashMap();
    
    private Order order;

    /*public Product sacar(Product p) {
        int n = products.indexOf(p);
        return (n < 0) ? null : (Product) products.get(n);
    }

    public Product sacar(int c) {
        Product p = new Product();
        p.setProdId(c);
        return (Product) products.get(c);

    }*/
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product removeProductOne(Product prod) {
        long prodId = prod.getProdId();
        if (products.isEmpty()) {
            return null;
        } else {
            for (Product p : products.keySet()) {
                if (p.getProdId() == prodId) {
                    if (products.get(p) == 1) {
                        products.remove(p);
                    } else {
                        products.replace(p, products.get(p) - 1);
                    }
                }
            }
            return prod;
        }
    }

    public Product removeProductAll(Product prod) {
        long prodId = prod.getProdId();
        if (products.isEmpty()) {
            return null;
        } else {
            for (Product p : products.keySet()) {
                if (p.getProdId() == prodId) {
                    products.remove(p);
                }
            }
            return prod;
        }
    }

    public void addProduct(Product prod) {
        long prodId = prod.getProdId();
        if (products.isEmpty()) {
            products.put(prod, 1);
        } else {
            for (Product p : products.keySet()) {
                if (p.getProdId() == prodId) {
                    products.replace(p, products.get(p) + 1);
                } else {
                    products.put(prod, 1);
                }
            }
        }

    }

    public void clearCart() {
        products.clear();
    }

    public int productsCount() {
        return products.size();
    }
}
