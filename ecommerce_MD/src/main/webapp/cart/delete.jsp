<%-- 
    Document   : delete
    Created on : 09-Mar-2020, 18:10:40
    Author     : Daniel Gomez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entity.Product"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<jsp:useBean id="cart" class="beans.Cart" scope="session" />

<%@include file="/etc/header.jsp" %>

<%@page import="java.util.Enumeration"%>


<%  Enumeration enu = request.getParameterNames();
    String name = "";
    String value = "";

    while (enu.hasMoreElements()) {
    
    %>
    <h2> <%=enu.nextElement()%> </h2>
        
    <% } %>