package entity;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * PRODUCT Entity - maps to PRODUCT table
 */
@Entity(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT e FROM PRODUCT e")})

public class Product {
	@Id
	@Column(name = "PROD_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long prodId;
	
	@Column(name = "PROD_NAME", nullable = false,length = 50)
	private String prodName;
	
	@Column(name = "PROD_DESC", length = 200)
	private String prodDescription;
	
	@Column(name = "REGULAR_PRICE", precision = 2)
	private String price;
	
        @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_TIME")
	private Date updatedTime;
	
	@ManyToMany(mappedBy="productList",fetch=FetchType.EAGER)
	private List<Order> orderList;
	

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
        public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
    
	public String toString() {
       StringBuffer sb = new StringBuffer();
       sb.append("prodId : " + prodId);
       sb.append("   prodName : " + prodName);
       sb.append("   prodDescription : " + prodDescription);
       sb.append("   price : " + price);
       return sb.toString();
    }
	
	public long getProdId() {
		return prodId;
	}
	public void setProdId(long prodId) {
		this.prodId = prodId;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
