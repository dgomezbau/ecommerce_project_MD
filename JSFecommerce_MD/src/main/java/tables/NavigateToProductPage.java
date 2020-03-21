/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import bean.Cart;
import bean.Control;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "navigateToProductPage")
@RequestScoped

public class NavigateToProductPage {

    long prodId;

    private String ROOT = "/catalogue/prodWeb_";

    public void productWeb(long prodID) {
        this.prodId = prodID;
        String path = this.ROOT + prodID + ".xhtml";
        redirect(path);
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

}
