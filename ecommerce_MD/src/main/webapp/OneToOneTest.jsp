<%-- 
    Document   : many-to-one
    Created on : 28-feb-2020, 21:47:40
    Author     : david
--%>

<%@page import="entity.Invoice"%>
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="entity.Order"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    long id = 111;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");

    EntityManager em = entityManagerFactory.createEntityManager();
    Order order = em.find(Order.class, id); //one to one
    List<Product> lista = order.getProductList(); //one to many
    Invoice invoice = order.getInvoice(); //many to one. We can obtain the invoice and then, use the invoice metods for obtain parameters
    em.close();
    entityManagerFactory.close();


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> All relationship from an oder number ==111 Invoice data corresponding to the order number= <%=id%></h1>
        <table border="2">
            <tr>
                <th>Data</th>
                <th>Value</th>

            </tr>
            <tr style="color: blue"><td colspan="2">DATA FROM THE INVOICE(one to one)</td></tr>
            <tr><td>Invoice id</td><td><%= invoice.getInvoiceId()%></td></tr>
            <tr><td>Amount</td><td> <%=invoice.getAmountDue()%></td></tr>
            <tr><td>Raised Date</td><td><%=invoice.getOrderRaisedDt()%></td></tr>
            <tr><td>Cancelled Date</td><td><%=invoice.getOrderCancelledDt()%></td></tr>
            <tr><td>Settled Date</td><td> <%=invoice.getOrderSettledDt()%></td></tr>
            <tr><td>Updated Date</td><td> <%=invoice.getUpdatedTime()%></td></tr>

            <tr style="color: blue"><td colspan="2">DATA FROM THE ORDER(no relation)</td></tr>
            <tr><td>Order id</td><td><%= invoice.getOrder().getOrderId()%></td></tr>
            <tr><td>Order Date</td><td><%= invoice.getOrder().getOrderDt()%></td></tr>

            <tr style="color: blue"><td colspan="2">DATA FROM THE CUSTOMER(many to one)</td></tr>
            <tr><td>Customer lastname id</td><td><%= invoice.getOrder().getCustomer().getLastName()%></td></tr>
            <tr><td>Customer emal</td><td><%= invoice.getOrder().getCustomer().getEmail()%></td></tr>
            
            
        </table>
        <div style="color: blue"><td colspan="2">DATA FROM THE ORDER DETAIL(one to many)<div>
        <table>
           
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>DateTime</th>
            </tr>
            <%        for (Product product : lista) {

            %> 
            <tr>
                <td><%=product.getProdName()%></td>
                <td><%=product.getProdDescription()%></td>
                <td><%=product.getUpdatedTime()%></td>
            </tr>
            <%}%> 

        </table>

    </body>
</html>
