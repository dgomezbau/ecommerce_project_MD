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


<%  Enumeration enu = request.getParameterNames();
    String nombre = "";
    String valor = "";

    while (enu.hasMoreElements()) {

        nombre = (String) (enu.nextElement());
        int idArti = 0;
        String des = "";
        double pre = 0;
        int can = 0;

        if (!nombre.equals("b1")) {

            valor = request.getParameter(nombre);

            out.println(nombre + " " + valor);

            try {
                idArti = Integer.parseInt(nombre);

            } catch (Exception e) {

                idArti = 0;

            }

            try {
                can = Integer.parseInt(valor);

            } catch (Exception e) {

                can = 0;

            }
        if (can!=0){
            query = "SELECT id, descripcion, precio FROm articulos WHERE id =" + idArti;

            rs = stmt.executeQuery(query);

            if (rs.next()) {

                des = rs.getString(2);
                pre = rs.getDouble(3);
                Articulo articulo = new Articulo(idArti, des, can, pre);
                carrito.meter(articulo);

            } else {

            }
        }
      }

    }
%>

<!-- body content -->
<div>
    <h1 class="titulo">Shopping-cart updated</h1>
    <p class="texto">
        Shopping-cart has been updated.
    </p>
</div>
<%@include file="/etc/foot.jsp" %>