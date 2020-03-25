/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import entity.Invoice;
import entity.Product;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "productEdit")
@SessionScoped
public class ProductEdit implements Serializable{

    
    public ProductEdit() {
        
    }
    
    public void updateProduct(Product product){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Invoice>query = em.createNamedQuery("Product.UpdateAdmin", Invoice.class);
        query.setParameter(1, product.getProdName());
        query.setParameter(2, product.getProdDescription());
        query.setParameter(3, product.getPrice());
        query.setParameter(4, new Date());
        query.setParameter(5, product.getProdId());
        //query.setParameter("date_settled", new Date());
        //query.setParameter("invoice_id", this.invoice.getInvoiceId());
        query.executeUpdate();
        em.getTransaction().commit();
        
        em.close();
        entityManagerFactory.close();
        
    }
    
    private void redirect(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(ProductEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
