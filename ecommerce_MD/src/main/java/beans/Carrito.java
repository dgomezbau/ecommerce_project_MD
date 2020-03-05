/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Product;
import java.util.Vector;

/**
 *
 * @author Daniel Gomez
 */
public class Carrito {

    private Vector products = new Vector();

    public Product sacar(Product p) {
        int n = products.indexOf(p);
        return (n < 0) ? null : (Product) products.get(n);
    }

    public Product sacar(int c) {
        Product p = new Product();
        p.setProdId(c);
        return (Product) products.get(c);

    }

    public void meter(Product p) {
        Product pr = null;
        int n = products.indexOf(p);
        if (n < 0) {
            products.add(p);
        } else {
            pr = (Product) products.get(n);
            pr.setCantidad(pr.getCantidad() + p.getCantidad()); //WORKING ON ADDING DUPLICATE PRODUCTS TO CART
        }
    }

    public void vaciar() {
        products.clear();
    }

    public int cuantos() {
        return products.size();
    }
}
