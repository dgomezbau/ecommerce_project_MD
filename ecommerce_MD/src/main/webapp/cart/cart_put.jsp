<%--
    Document   : cart_put.jsp
    Created on : 17-feb-2020, 17:59:09
    Author     : david
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entity.Product"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>

<jsp:useBean id="carrito" class="beans.Carrito" scope="session" />

<%@include file="/etc/header.jsp" %>

<%@page import="java.util.Enumeration"%>


<%  Enumeration enu = request.getParameterNames();
    String name = "";
    String value = "";

    while (enu.hasMoreElements()) {

        name = (String) (enu.nextElement());
        long prodId = 0;
        String description = "";
        double price = 0;
        int quantity = 0;

        if (!name.equals("b1")) {
            value = request.getParameter(name);

            //out.println(name + " " + value);

            try {
                prodId = Long.parseLong(name);
            } catch (Exception e) {
                prodId = 0;
            }
            try {
                quantity = Integer.parseInt(value);
            } catch (Exception e) {
                quantity = 0;
            }
            if (quantity>0){

                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
                EntityManager em = entityManagerFactory.createEntityManager();

                Product prod = em.find(Product.class, prodId);
                em.close();
                entityManagerFactory.close();

                for(int i=0; i<quantity; i++){
                    carrito.addProduct(prod);
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