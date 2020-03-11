package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Order Entity - maps to ORDERS table
 */
@Entity(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(name = "CUST_ID")
    private long custId;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private double totPrice;

    @Column(name = "OREDER_DESC")
    private String orderDesc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE")
    private Date orderDt;

    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "order", targetEntity = Invoice.class)
    private Invoice invoice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID", updatable = false, insertable = false)
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_DETAIL",
            joinColumns
            = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "PROD_ID", referencedColumnName = "PROD_ID")
    )
    private List<Product> productList;

    //@Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("orderId : " + orderId);
        sb.append("   custId : " + custId);
        sb.append("   totPrice : " + totPrice);
        sb.append("   orderDesc : " + orderDesc);
        sb.append("   orderDt : " + orderDt);
        sb.append("   invoice : " + invoice);
        sb.append("   products : " + productList);
        return sb.toString();
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Date getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
