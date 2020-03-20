/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import entity.Invoice;
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
@Named(value = "feedInvoiceList")
@SessionScoped
public class FeedInvoiceList implements Serializable{

private List<Invoice> listInv = new ArrayList<>();

    public FeedInvoiceList() {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Invoice> query = em.createNamedQuery("Invoice.findAll", Invoice.class);
        this.listInv = query.getResultList();

        em.close();
        entityManagerFactory.close();
    }

    public List<Invoice> getListInv() {
        return listInv;
    }

    public void setListInv(List<Invoice> listInv) {
        this.listInv = listInv;
    }
    
}
