package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/*
 * INVOICE Entity - maps to ORDER_INVOICE table
 */
@Entity(name = "ORDER_INVOICE")

@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT e FROM ORDER_INVOICE e"),
    @NamedQuery(name = "Invoice.updateInvoiceDate", query = "UPDATE ORDER_INVOICE e SET e.orderSettledDt = :date_settled, e.orderCancelledDt = :date_cancelled WHERE e.invoiceId = :invoice_id"),
    @NamedQuery(name = "Invoice.InvoiceSettle", query = "UPDATE ORDER_INVOICE e SET e.orderSettledDt = :date_settled WHERE e.invoiceId = :invoice_id"),
    @NamedQuery(name = "Invoice.updateInvoiceCancel", query = "UPDATE ORDER_INVOICE e SET e.orderCancelledDt = :date_cancelled WHERE e.invoiceId = :invoice_id")}
)
@NamedNativeQueries({
    @NamedNativeQuery(name = "Invoice.InvoiceSettle2", query = "UPDATE ORDER_INVOICE SET date_settled = ? WHERE invoice_id = ?"),
    @NamedNativeQuery(name = "Invoice.InvoiceCancel", query = "UPDATE ORDER_INVOICE SET date_cancelled = ? WHERE invoice_id = ?")
})

public class Invoice implements Serializable {

    @Id //signifies the primary key
    @Column(name = "INVOICE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long invoiceId;

    @Column(name = "ORDER_ID", updatable = false, insertable = false)
    private long orderId;

    @Column(name = "AMOUNT_DUE", precision = 2)
    private double amountDue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_RAISED")
    private Date orderRaisedDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_SETTLED")
    private Date orderSettledDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CANCELLED")
    private Date orderCancelledDt;

    //@Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("orderId : " + orderId);
        sb.append("   invoiceId : " + invoiceId);
        sb.append("   amtDue : " + amountDue);
        sb.append("   orderRaisedDt : " + orderRaisedDt);
        sb.append("   orderSettledDt : " + orderSettledDt);
        sb.append("   orderCancelledDt : " + orderCancelledDt);
        sb.append("   updatedTime : " + updatedTime);
        return sb.toString();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getOrderRaisedDt() {
        return orderRaisedDt;
    }

    public void setOrderRaisedDt(Date orderRaisedDt) {
        this.orderRaisedDt = orderRaisedDt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getOrderCancelledDt() {
        return orderCancelledDt;
    }

    public void setOrderCancelledDt(Date orderCancelledDt) {
        this.orderCancelledDt = orderCancelledDt;
    }

    public Date getOrderSettledDt() {
        return orderSettledDt;
    }

    public void setOrderSettledDt(Date orderSettledDt) {
        this.orderSettledDt = orderSettledDt;
    }
}
