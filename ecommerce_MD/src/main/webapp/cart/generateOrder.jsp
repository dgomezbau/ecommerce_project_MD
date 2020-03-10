<%-- 
    Document   : generateOrder
    Created on : 09-Mar-2020, 17:42:32
    Author     : Daniel Gomez
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="entity.Product"%>
<%@page import="entity.Customer"%>
<%@page import="java.util.Map"%>


<jsp:useBean id="cart" class="beans.Cart" scope="session" />
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@include file="/etc/header.jsp"%>
<!DOCTYPE html>
<div>
    <h1 class="titulo">Modify shopping-cart</h1>

    <% //Add a product to the cart for test pourposses
        //Whats the problem with the ctrl bean name?????
        //Generate here the Order instance or after user payment???
        ctrl.setIdUser(100);
        long cusID = ctrl.getIdUser();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        Customer customer = em.find(Customer.class, cusID);
        em.close();
        entityManagerFactory.close();
        
    %>

    <div class="texto">
        <table border="1">
            <tr>
                <td> Customer Information </td>
            </tr>
            <tr>
                <td> Name </td>
                <td> Address </td>
                <td> Email </td>
            </tr>
            <tr>
                <td> <%=customer.getFirstName() +" "+ customer.getLastName()%> </td>
                <td> <%=customer.getStreet() +" "+ customer.getAppt() +" "+ customer.getCity() +" "+ customer.getZipCode()%> </td>
                <td> <%=customer.getEmail()%> </td>
            </tr>
        </table>
    </div>


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
                for (Product p : prodMap.keySet()) {
                    prod = p;
                    totalPrice = totalPrice + (Double.parseDouble(prod.getPrice()) * prodMap.get(prod));
            %>
            <tr>

                <td><%=prod.getProdName()%></td>
                <td><%=prod.getProdDescription()%></td>
                <td><%=prod.getPrice()%> €</td>
                <td><%=prodMap.get(prod)%></td>
            <form name="f1" action="delete.jsp" >
                <td> <input id="" type="submit" name="<%=prod.getProdId()%>" value="Remove" > </td>
            </form>
            <%}%>
            </tr>

            <tr>
                <td>Total: <%=totalPrice%> €</td> 
            </tr>

        </table>

        <div style="width:500px;">
            <form name="f1" action="/ecommerce_MD/index.html" style="float: left;" >
                <input id="" type="submit" name="b1" value="Cancel" >
            </form>
            <form name="f1" action="invoices.jsp" style="float: left;">
                <input id="" type="submit" name="b1" value="Pay" >
            </form>
        </div>
    </div>
</div>
