
<%--
    Document   : cart_put.jsp
    Created on : 17-feb-2020, 17:59:09
    Author     : david
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="business.Articulo"%>
<%@include file="/etc/header.jsp" %>

<%@include file="/etc/connection.jsp" %>
<jsp:useBean id="carrito" class="beans.Carrito" scope="session" /> 

<%@page import="java.util.Enumeration"%>


<%  carrito.vaciar();
%>

<!-- body content -->
<div>
    <h1 class="titulo">Shopping-cart empty</h1>
    <p class="texto">
        Shopping-cart has been clear up.
    </p>
</div>
<%@include file="/etc/foot.jsp" %>