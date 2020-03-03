
<%--
    Document   : SimpleJsp.html
    Created on : 11-feb-2020, 16:32:58
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@page import="business.Articulo"%>
<%@page import="java.util.Enumeration"%>

<jsp:useBean id="carrito" class="beans.Carrito" scope="session" /> 


<%@include file="/etc/header.jsp" %>

<!-- body content -->
<div>
    <h1 class="titulo">Content of shopping-cart</h1>
 
    <table border="1">
        <tr>
            <td> Description </td>
            <td> Price </td>
            <td> Quantity </td>
        </tr>


        <%  Articulo articulo = null;
           for (int i = 0; i < carrito.cuantos(); i++) {
               articulo = (Articulo) carrito.sacar(i);
       %>

        <tr>
            <td><%=articulo.getDescripcion()%></td>
            <td><%=articulo.getPrecio()%></td>
            <td><%=articulo.getCantidad()%></td>
        </tr>

        <%}%>
    </table>
</div>
<%@include file="/etc/foot.jsp" %>
