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

<%  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();
    
    Order order = cart.getOrder();
    
    em.getTransaction().begin();
    em.persist(order);
    em.getTransaction().commit();
    
    em.close();
    entityManagerFactory.close();
    
    cart.clearCart();
    response.sendRedirect("../home/index.jsp");

    %>