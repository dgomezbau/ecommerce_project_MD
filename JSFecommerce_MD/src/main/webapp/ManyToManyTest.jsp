<%-- 
    Document   : many-to-one
    Created on : 28-feb-2020, 21:47:40
    Author     : david
--%>

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="entity.Order"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    long id = 111;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
    EntityManager em = entityManagerFactory.createEntityManager();

    Order order = em.find(Order.class, id);
    List<Product> lista = order.getProductList();
    em.close();
    entityManagerFactory.close();
    // System.out.println("Order  : " + order.getProductList());

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> (Many-to-many) Showing all items of a given order (e.g. <%=id%>)</h1>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>DateTime</th>
            </tr>
            <%        for (Product product : lista) {

            %> 
            <tr>
                <td><%=product.getProdName()%></td>
                <td><%=product.getProdDescription()%></td>
                <td><%=product.getUpdatedTime()%></td>
            </tr>
            <%}%> 

        </table>

    </body>
</html>
