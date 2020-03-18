/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

@Named(value = "addProductsForTest")
@SessionScoped

public class AddProductsForTest implements Serializable{
    
    /*public void addProd(){
        
    long productId = 2020;
       
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();

    Product productAdd = em.find(Product.class, productId);
    em.close();
    entityManagerFactory.close();
    
    Cart c = new Cart();
    c.addProduct(productAdd);
    
    }*/
}
