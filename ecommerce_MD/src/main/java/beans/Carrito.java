/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Daniel Gomez
 */
public class Carrito {

    private Map<Product, Integer> products = new HashMap();

    /*public Product sacar(Product p) {
        int n = products.indexOf(p);
        return (n < 0) ? null : (Product) products.get(n);
    }

    public Product sacar(int c) {
        Product p = new Product();
        p.setProdId(c);
        return (Product) products.get(c);

    }*/
    
    public Product removeProductOne(Product p) {
        if (products.containsKey(p)) {
            if (products.get(p) == 1) {
                products.remove(p);
                return p;
            } else if (products.get(p) > 1) {
                products.replace(p, products.get(p) - 1);
                return p;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Product removeProductAll(Product p) {
        if (products.containsKey(p)) {
            products.remove(p);
            return p;
        } else {
            return null;
        }
    }

    public void addProduct(Product p) {
        if (products.containsKey(p)) {
            products.replace(p, products.get(p) + 1);
        } else {
            products.put(p, 1);
        }
    }

    public void clearCart() {
        products.clear();
    }

    public int productsCount() {
        return products.size();
    }
}
