/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import entity.Customer;
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
@Named(value = "feedCustomerList")
@SessionScoped
public class FeedCustomerList implements Serializable{

    private List<Customer> customerList = new ArrayList<>();
    
    public FeedCustomerList() {
        
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        this.customerList = query.getResultList();
        
        em.close();
        entityManagerFactory.close();
        
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    
}
