/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel Gomez
 */

@Named(value = "fud")
@SessionScoped
public class FindUserData implements Serializable{
    
    UserInfo userPar;

    public FindUserData() {
        ArrayList<String> lst = findData();
        String name1=lst.get(0);
        String name2=lst.get(1);
        this.userPar=new UserInfo(name1, name2);
    }
    
    
    
    private ArrayList<String> findData(){
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();

    Customer user = em.find(Customer.class, 110L);
    em.close();
    entityManagerFactory.close();
    
    String name = user.getFirstName();
    String name2 = user.getLastName();
    
    ArrayList<String> list = new ArrayList<>();
    list.add(name);
    list.add(name2);
    
    return list;
    }

    public UserInfo getUserPar() {
        return userPar;
    }

    public void setUserPar(UserInfo userPar) {
        this.userPar = userPar;
    }
   
}
