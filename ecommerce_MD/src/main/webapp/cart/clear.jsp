<%--
    Document   : cart_put.jsp
    Created on : 17-feb-2020, 17:59:09
    Author     : Daniel Gómez
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/etc/header.jsp" %>

<jsp:useBean id="carrito" class="beans.Carrito" scope="session" /> 

<%  carrito.clearCart();
%>

<!-- body content -->
<div>
    <h1 class="titulo">Shopping-cart empty</h1>
    <p class="texto">
        Shopping-cart has been clear up.
    </p>
</div>
<%@include file="/etc/foot.jsp" %>