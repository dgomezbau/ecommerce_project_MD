<%-- 
    Document   : SimpleJsp.html
    Created on : 11-feb-2020, 16:32:58
    Author     : david
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@page import="entity.Product"%>
<%@page import="java.util.Map"%>

<jsp:useBean id="cart" class="beans.Cart" scope="session" />  


<%@include file="/etc/header.jsp" %>

<!-- body content -->
<div>
    <h1 class="titulo">Modify shopping-cart</h1>
    
    <% //Add a product to the cart for test pourposses
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Product product_sample = em.find(Product.class, 2030L);
        em.close();
        entityManagerFactory.close();
                
        cart.addProduct(product_sample);
        %>


    <div class="texto">
        <table border="1">
            
            <tr>
                <td> Name </td>
                <td> Description </td>
                <td> Price </td>
                <td> Quantity </td>
                <td>  </td>

            </tr>
               <%  Map<Product, Integer> prodMap = cart.getProducts();
            Product prod = null;
            double totalPrice = 0;
            for (Product p: prodMap.keySet()) {
               prod = p;
               totalPrice = totalPrice + (Double.parseDouble(prod.getPrice())*prodMap.get(prod));
            %>
            <tr>

                <td><%=prod.getProdName()%></td>
                <td><%=prod.getProdDescription()%></td>
                <td><%=prod.getPrice()%> €</td>
                <td><%=prodMap.get(prod)%></td>
                
                <td>
            <form name="f1" action="addOne.jsp" style="float: left;" >
                <input id="" type="submit" name="<%=prod.getProdId()%>" value=" + " >
            </form>
            <form name="f1" action="deleteOne.jsp" style="float: left;" >
                <input id="" type="submit" name="<%=prod.getProdId()%>" value=" - " >
            </form>
            <form name="f1" action="delete.jsp" style="float: left;" >
                 <input id="" type="submit" name="<%=prod.getProdId()%>" value="Remove all" > </td>
            </form>
            <%}%>
            </tr>
            
            <tr>
               <td>Total: <%=totalPrice%> €</td> 
            </tr>

        </table>

        <div style="width:500px;">
            <form name="f1" action="clear.jsp" style="float: left;" >
                <input id="" type="submit" name="b1" value="Clear" >
            </form>
            <form name="f1" action="generateOrder.jsp" style="float: left;">
                <input id="" type="submit" name="b1" value="Order" >
            </form>
            <form name="f1" action="/ecommerce_MD/index.html" >
                <input id="" type="submit" name="b1" value="Keep buying" >
            </form>
        </div>
    </div>
</div>
<%@include file="/etc/foot.jsp" %>
