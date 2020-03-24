/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import bean.Control;
import entity.Invoice;
import entity.Order;
import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
public class FeedInvoiceList implements Serializable {

    private List<Invoice> listInv = new ArrayList<>();

    @Inject
    private Control ctrl;
    
    
    public List<Invoice> feedInvList() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Invoice> query = em.createNamedQuery("Invoice.findAll", Invoice.class);
        List<Invoice> listAllInv = new ArrayList<>();

        listAllInv = query.getResultList();

        for (Invoice i : listAllInv) {
            Order ord = em.find(Order.class, i.getOrderId());
            if (ctrl.getCustom().getCustId() == ord.getCustId()) {
                this.listInv.add(i);
            }
        }

        em.close();
        entityManagerFactory.close();
        
        return listInv;
    }

    public List<Invoice> getListInv() {
        return listInv;
    }

    public void setListInv(List<Invoice> listInv) {
        this.listInv = listInv;
    }

    public Control getCtrl() {
        return ctrl;
    }

    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }

}
