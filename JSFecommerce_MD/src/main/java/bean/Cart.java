/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Order;
import entity.Product;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import peentity.ProductPE;

@Named(value = "cart")
@SessionScoped

public class Cart implements Serializable{

    private Map<ProductPE, Integer> productsAndQuantity = new HashMap();
    
    private Order order;
    
    private int amount = 1;
    
    public static ProductPE currentProductPE = null;
    
    
    
    /*public Product sacar(Product p) {
        int n = products.indexOf(p);
        return (n < 0) ? null : (Product) products.get(n);
    }

    public Product sacar(int c) {
        Product p = new Product();
        p.setProdId(c);
        return (Product) products.get(c);

    }*/
    public Map<ProductPE, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Set<ProductPE> listProducts(){
        return productsAndQuantity.keySet();
    }
    
    public Collection<Integer> listQuantity(){
        return productsAndQuantity.values();
    }

    public Product removeProductOne(Product prod) {
        long prodId = prod.getProdId();
        if (productsAndQuantity.isEmpty()) {
            return null;
        } else {
            for (ProductPE p : productsAndQuantity.keySet()) {
                if (p.getId() == prodId) {
                    if (productsAndQuantity.get(p) == 1) {
                        productsAndQuantity.remove(p);
                    } else {
                        productsAndQuantity.replace(p, productsAndQuantity.get(p) - 1);
                    }
                }
            }
            return prod;
        }
    }

    public ProductPE removeProductAll(ProductPE prod) {
        long prodId = prod.getId();
        if (productsAndQuantity.isEmpty()) {
            return null;
        } else {
            for (ProductPE p : productsAndQuantity.keySet()) {
                if (p.getId() == prodId) {
                    productsAndQuantity.remove(p);
                }
            }
            return prod;
        }
    }

    public void addProduct(ProductPE pPE) {
        long prodId = pPE.getId();
        if (productsAndQuantity.isEmpty()) {
            productsAndQuantity.put(pPE, amount);
        } else {
            for (ProductPE p : productsAndQuantity.keySet()) {
                if (p.getId() == prodId) {
                    productsAndQuantity.replace(p, productsAndQuantity.get(p) + 1);
                } else {
                    productsAndQuantity.put(pPE, amount);
                }
            }
        }

    }
    
    public ProductPE feedProdDetail() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String id = request.getParameter("id");
        long idl;
        try {
            idl = Long.parseLong(id);
        } catch (Exception e) {
            idl = 0;
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Product selectedProduct = em.find(Product.class, idl);
        //TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class); //We use the namedQuery in employee instaed of yhe controller
        //List<Product> listProd = query.getResultList();

        //Parse entity Products to ProductPE
        currentProductPE.setId(selectedProduct.getProdId());
        currentProductPE.setName(selectedProduct.getProdName());
        currentProductPE.setDescription(selectedProduct.getProdDescription());
        currentProductPE.setPrice(selectedProduct.getPrice());
        currentProductPE.setUpdatedTime(selectedProduct.getUpdatedTime());
        currentProductPE.setOrderList(selectedProduct.getOrderList());

        em.close();
        entityManagerFactory.close();
        
        return currentProductPE;
    }
    
    public void redirectToProdPage(){
        
            try{
                if(currentProductPE.getId()==2000){
                    FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/catalogue/productPage");
                }
            
            }catch(Exception e){
                
            }
        
    }

    public void clearCart() {
        productsAndQuantity.clear();
    }

    public int productsCount() {
        return productsAndQuantity.size();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static ProductPE getCurrentProductPE() {
        return currentProductPE;
    }

    public static void setCurrentProductPE(ProductPE currentProductPE) {
        Cart.currentProductPE = currentProductPE;
    }
    
    
}
