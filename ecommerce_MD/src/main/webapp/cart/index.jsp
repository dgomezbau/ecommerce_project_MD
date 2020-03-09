
<%--
    Document   : SimpleJsp.html
    Created on : 11-feb-2020, 16:32:58
    Author     : Daniel Gómez
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import="entity.Product"%>
<jsp:useBean id="cart" class="beans.Cart" scope="session" />
<%@page import="java.util.Map"%>
<%@include file="/etc/header.jsp" %>

<!-- body content -->
<div>
    <h1 class="titulo">Content of shopping-cart</h1>
 
    <table border="1">
        <tr>
            <td> Name </td>
            <td> Description </td>
            <td> Price </td>
            <td> Quantity </td>
        </tr>


        <%  Map<Product, Integer> prodMap = cart.getProducts();
            //int quantity = 0;
            Product prod = null;
            
            for (Product p: prodMap.keySet()) {
               prod = p;
       %>

        <tr>
            <td><%=prod.getProdName()%></td>
            <td><%=prod.getProdDescription()%></td>
            <td><%=prod.getPrice()%></td>
            <td><%=prodMap.get(prod)%></td>
        </tr>

        <%}%>
    </table>
</div>
<%@include file="/etc/foot.jsp" %>
