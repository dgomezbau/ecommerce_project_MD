/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import bean.Cart;
import bean.Control;
import entity.Product;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "navigateToProductPage")
@SessionScoped

public class NavigateToProductPage implements Serializable{

    private long prodId;
    private Product prod;
    private List<String> imgRef = new ArrayList();

    private String ROOT = "/catalogue/prodWeb.jsf";
    private String ROOT_IMG = "images/";

    public void productWeb(long prodID) {
        imgRef.clear();
        this.prodId = prodID;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.prod = em.find(Product.class, prodID);   
        em.close();
        entityManagerFactory.close();
        
        imgRef.add(this.ROOT_IMG + prodID + ".jpg");
        
        redirect(this.ROOT);
    }

    private void redirect(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(NavigateToProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public List<String> getImgRef() {
        return imgRef;
    }

    public void setImgRef(List<String> imgRef) {
        this.imgRef = imgRef;
    }

  

}
