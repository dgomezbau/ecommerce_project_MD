/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import entity.Product;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "feedProdDetail")
//@SessionScoped
@RequestScoped
public class FeedProdDetail implements Serializable {

    ProductPE ProdPE = new ProductPE();

    public FeedProdDetail() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String id = request.getParameter("id");
        long idl;
        try{idl = Long.parseLong(id);}catch(Exception e){idl=0;}

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Product selectedProduct = em.find(Product.class, idl);
        //TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class); //We use the namedQuery in employee instaed of yhe controller
        //List<Product> listProd = query.getResultList();

        //Parse entity Products to ProductPE
        ProdPE.setId(selectedProduct.getProdId());
        ProdPE.setName(selectedProduct.getProdName());
        ProdPE.setDescription(selectedProduct.getProdDescription());
        ProdPE.setPrice(selectedProduct.getPrice());
        ProdPE.setUpdatedTime(selectedProduct.getUpdatedTime());
        ProdPE.setOrderList(selectedProduct.getOrderList());

        em.close();
        entityManagerFactory.close();

    }

    public ProductPE getProdPE() {
        return ProdPE;
    }

    public void setProdPE(ProductPE ProdPE) {
        this.ProdPE = ProdPE;
    }

}
