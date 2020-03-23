/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Customer;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named(value = "customerInfo")
@SessionScoped

public class CustomerInfo implements Serializable{

    Customer customer = null;
    long id;
    
    public CustomerInfo() {
        
    }
    
    public void loadCustomer(long id){
        this.id = id;
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.customer = em.find(Customer.class, id);
        
        em.close();
        entityManagerFactory.close();
        
        redirect("../admin/invoicePerCustomerList.jsf");
        
    }
    
    private void redirect(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
