/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import entity.Invoice;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    }
    
    public void editInvoice(){
        
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
