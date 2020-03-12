<%-- 
    Document   : addOne
    Created on : 12-Mar-2020, 20:02:10
    Author     : Daniel Gomez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entity.Product"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<jsp:useBean id="cart" class="beans.Cart" scope="session" />

<%@page import="java.util.Enumeration"%>


<%  Enumeration enu = request.getParameterNames();

    long productId = Long.parseLong((String)enu.nextElement());
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();

    Product productAdd = em.find(Product.class, productId);
    em.close();
    entityManagerFactory.close();
                
    cart.addProduct(productAdd);
    
    response.sendRedirect("modify.jsp");
    
    %>