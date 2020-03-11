<%-- 
    Document   : createOrderAndInvoice
    Created on : 11-Mar-2020, 19:51:43
    Author     : Daniel Gomez
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="entity.Product"%>
<%@page import="entity.Customer"%>
<%@page import="entity.Order"%>
<%@page import="entity.Invoice"%>
<%@page import="java.util.Map"%>
<%@page import="JPAControllers.OrderJpaController"%>
<%@page import="JPAControllers.InvoiceJpaController"%>
<%@page import="JPAControllers.CustomerJpaController"%>

<jsp:useBean id="cart" class="beans.Cart" scope="session" />

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@include file="/etc/header.jsp"%>

<%EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
   EntityManager em = entityManagerFactory.createEntityManager();
    
    //OrderJpaController ojc = new OrderJpaController(entityManagerFactory);
    Order ord = new Order();
    em.persist(ord);

    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
<%@include file="/etc/foot.jsp" %>