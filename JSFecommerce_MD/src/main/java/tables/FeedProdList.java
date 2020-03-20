/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import peentity.ProductPE;
import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "feedProdList")
@SessionScoped
public class FeedProdList implements Serializable {

    private List<Product> listProd = new ArrayList<>();

    public FeedProdList() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class); //We use the namedQuery in employee instaed of yhe controller
        this.listProd = query.getResultList();

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
