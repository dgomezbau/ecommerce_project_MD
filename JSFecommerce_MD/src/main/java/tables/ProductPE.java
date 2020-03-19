package tables;

import entity.Order;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Daniel Gomez
 */
public class ProductPE{

    long id;
    String name;
    String price;
    String description;
    private Date updatedTime;
    private List<Order> orderList;

    public ProductPE(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public ProductPE() {

    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
