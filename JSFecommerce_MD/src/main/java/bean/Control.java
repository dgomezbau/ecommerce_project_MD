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
import javax.persistence.TypedQuery;

@Named(value = "control")
@SessionScoped

public class Control implements Serializable {

    public Control() {

    }

    private String userName;
    private String name;
    private long idUser;
    private String pass;
    private int level;
    private Customer custom = null;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustom() {
        return custom;
    }

    public void setCustom(Customer custom) {
        this.custom = custom;
    }

    private void obtainCustomerFromDB() {
        if (this.userName == null) {
            redirect("../errors/incorrectUser.jsf");
        } else {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
            EntityManager em = entityManagerFactory.createEntityManager();

            TypedQuery<Customer> query = em.createNamedQuery("Customer.findbyemail", Customer.class);
            query.setParameter("email", this.userName);
            try{
                this.custom = query.getSingleResult();
            }catch(Exception e){
                redirect("../errors/incorrectUser.jsf");
            }
            
            em.close();
            entityManagerFactory.close();
        }
    }

    private void redirect(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login() {

        obtainCustomerFromDB();

        if (this.custom == null || this.pass == null) {
            redirect("../errors/loginError.jsf");
        } else {
            if (!custom.getPass().equals(pass)) {
                redirect("../errors/loginError.jsf");
                this.custom=null;
            } else {
                this.idUser = custom.getCustId();
                this.name = custom.getFirstName() + " " + custom.getLastName();
                redirect("../home/homePage.jsf");
            }
        }
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        redirect("../home/homePage.jsf");
    }
}
