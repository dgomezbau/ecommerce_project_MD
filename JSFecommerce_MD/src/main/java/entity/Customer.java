package entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

/*
 * Customer Entity - maps to CUSTOMER table
 */
@Entity(name = "CUSTOMER")

@NamedQueries({
    @NamedQuery(name = "Customer.findbyemail", query = "SELECT e FROM CUSTOMER e WHERE e.email = :email")})
public class Customer implements Serializable {

    @Id //signifies the primary key
    @Column(name = "CUST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long custId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "STREET")
    private String street;

    @Column(name = "APPT")
    private String appt;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Column(name = "PASS", nullable = false)
    private String pass;

      
    //@Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_TIME")
    private java.util.Date updatedTime;



    @OneToMany(mappedBy = "customer", targetEntity = Order.class, fetch = FetchType.EAGER)
    private Collection<Order> orders;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("custId : " + custId);
        sb.append("   firstName : " + firstName);
        sb.append("   lastName : " + lastName);
        sb.append("   street : " + street);
        sb.append("   city : " + city);
        sb.append("   appt : " + appt);
        sb.append("   zipCode : " + zipCode);
        sb.append("   email : " + email);
        sb.append("   Orders  : " + orders);
        return sb.toString();
    }

    public String getAppt() {
        return appt;
    }

    public void setAppt(String appt) {
        this.appt = appt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public java.util.Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(java.util.Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
