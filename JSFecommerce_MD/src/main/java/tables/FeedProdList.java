/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "feedProdList")
@SessionScoped
public class FeedProdList implements Serializable {

    private List<Product> listProd = new ArrayList<>();
    
    private Map<Product,Integer> listProdAmount = new HashMap<>();

    public FeedProdList() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        this.listProd = query.getResultList();
        for(Product prod: listProd){
            listProdAmount.put(prod, 0);
        }
        
        

        em.close();
        entityManagerFactory.close();

    }

    public List<Product> getListProd() {
        return listProd;
    }

    public void setListProd(List<Product> Prod) {
        this.listProd = Prod;
    }

}
