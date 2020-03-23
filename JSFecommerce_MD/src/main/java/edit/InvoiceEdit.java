/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import bean.Control;
import entity.Customer;
import entity.Invoice;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "invoiceEdit")
@RequestScoped
public class InvoiceEdit implements Serializable{

    Invoice invoice;
 
    private long invId;
    private Date invRaised;
    private Date invSettled;
    private Date invCancelled;
    
    
    public void obtainCurrentInvoice(Invoice inv){
        this.invoice = inv;
        this.invRaised = inv.getOrderRaisedDt();
        this.invSettled = inv.getOrderSettledDt();
        this.invCancelled = inv.getOrderCancelledDt();
        
        redirect("../admin/invoiceEditor.jsf");
    }
    
    public void editInvoice(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Invoice> query = em.createNamedQuery("Invoice.updateInvoiceDate", Invoice.class);
        query.setParameter("DATE_SETTLED", this.invSettled);
        query.setParameter("DATE_CANCELLED", this.invCancelled);
        query.setParameter("INVOICE_ID", this.invoice.getInvoiceId());
        query.executeUpdate();
        
        redirect("../admin/ivoicePerCustomerList.jsf");
        
    }
    
    private void redirect(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            Logger.getLogger(InvoiceEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getInvId() {
        return invId;
    }

    public void setInvId(long invId) {
        this.invId = invId;
    }

    public Date getInvRaised() {
        return invRaised;
    }

    public void setInvRaised(Date invRaised) {
        this.invRaised = invRaised;
    }

    public Date getInvSettled() {
        return invSettled;
    }

    public void setInvSettled(Date invSettled) {
        this.invSettled = invSettled;
    }

    public Date getInvCancelled() {
        return invCancelled;
    }

    public void setInvCancelled(Date invCancelled) {
        this.invCancelled = invCancelled;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
}
