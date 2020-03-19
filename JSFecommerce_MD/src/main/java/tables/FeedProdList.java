/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class FeedProdList implements Serializable{
    
    int counter = 0;
    List<ProductPE> listProdPE = new ArrayList<>();
    
    public FeedProdList() {
        
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();
    
    TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class); //We use the namedQuery in employee instaed of yhe controller
    List<Product> listProd = query.getResultList();
    
    //Parse entity Products to ProductPE
    for(Product p: listProd){
        ProductPE pPE = new ProductPE();
        pPE.setId(p.getProdId());
        pPE.setName(p.getProdName());
        pPE.setDescription(p.getProdDescription());
        pPE.setPrice(p.getPrice());
        pPE.setUpdatedTime(p.getUpdatedTime());
        pPE.setOrderList(p.getOrderList());
        
        listProdPE.add(pPE);
        
        System.err.println("Llega hasta aquí: " + pPE.getName());
        counter++;
        System.err.println(counter);
    }
    
    em.close();
    entityManagerFactory.close();
 
    }

    public List<ProductPE> getListProdPE() {
        return listProdPE;
    }

    public void setListProdPE(List<ProductPE> listProdPE) {
        this.listProdPE = listProdPE;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
}
