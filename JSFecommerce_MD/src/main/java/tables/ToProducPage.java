/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "toProducPage")
@RequestScoped
public class ToProducPage implements Serializable{

    /**
     * Creates a new instance of ToProducPage
     */
    public ToProducPage() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String id = request.getParameter("id");
        long idl;
        try {
            idl = Long.parseLong(id);
        } catch (Exception e) {
            idl = 0;
        }
        if(idl==2000){
            try{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(ec.getRequestContextPath() + "/productPage2.jsf");
        }catch(Exception e){
            
        }
            
        }
        
    }
    
}
