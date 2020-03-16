<%-- 
    Document   : generateOrder
    Created on : 09-Mar-2020, 17:42:32
    Author     : Daniel Gomez
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="entity.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="entity.Product"%>
<%@page import="entity.Customer"%>
<%@page import="entity.Order"%>
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

            </tr>
            <%  Map<Product, Integer> prodMap = cart.getProducts();
                List<Product> prodList = new ArrayList();
                Product prod = null;
                double totalPrice = 0;
                for (Product p : prodMap.keySet()) {
                    prodList.add(p);
                    prod = p;
                    totalPrice = totalPrice + (Double.parseDouble(prod.getPrice()) * prodMap.get(prod));
            %>
            <tr>

                <td><%=prod.getProdName()%></td>
                <td><%=prod.getProdDescription()%></td>
                <td><%=prod.getPrice()%> €</td>
                <td><%=prodMap.get(prod)%></td>
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
            <form name="f1" action="createOrderAndInvoiceInDB.jsp" style="float: left;">
                <input id="" type="submit" name="b1" value="Pay" >
            </form>
        </div>
    </div>
</div>
    

    <%! 

    String todayDate(){
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
        String StringDate = format.format(date);
        return StringDate;
    }
    %>
    
    <%Order order = new Order();
    Invoice invoice = new Invoice();
    
    order.setCustId(customer.getCustId());
    order.setTotPrice(totalPrice);
    order.setOrderDesc("Test Description");
    order.setOrderDt(new Date());
    order.setProductList(prodList);
    order.setUpdatedTime(new Date());
    
    invoice.setAmountDue(totalPrice);
    invoice.setOrder(order);
    invoice.setOrderRaisedDt(new Date());
    invoice.setUpdatedTime(new Date());
    
    order.setInvoice(invoice);
    
    cart.setOrder(order);
    
    em.close();
    entityManagerFactory.close();

    %>
<%@include file="/etc/foot.jsp" %>