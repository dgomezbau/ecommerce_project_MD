
<%--
    Document   : control
    Created on : 17-feb-2020, 17:00:38
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<jsp:useBean id="ctrl" class="beans.Control" scope="session" />
<%
  if(ctrl.getIdUser()<0){
  response.sendRedirect("/home/error.jsp?mensa=Access denied!");
  }
%>