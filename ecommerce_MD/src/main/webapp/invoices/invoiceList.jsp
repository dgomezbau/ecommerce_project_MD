<%-- 
    Document   : invoiceList
    Created on : 12-Mar-2020, 20:12:51
    Author     : Daniel Gomez
--%>

<%@page import="entity.Invoice"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product"%>
<jsp:useBean id="cart" class="beans.Cart" scope="session" />
<%@page import="java.util.Map"%>
<%@include file="/etc/header.jsp" %>

<%

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("persis");
    EntityManager em = emf.createEntityManager();

    TypedQuery<Invoice> query = em.createNamedQuery("Invoice.findAll", Invoice.class);
    List<Invoice> invoiceList = query.getResultList();

%>
<div class="texto">
    <table border="1">

        <tr>
            <td> ID </td>
            <td> Order number </td>
            <td> Amount </td>
            <td> Raised </td
            <td> Settled </td>
            <td> Cancelled </td>
            <td>  </td>

        </tr>
        <%  for (Invoice inv : invoiceList) {
        %>
        <tr>

            <td><%=inv.getInvoiceId()%></td>
            <td><%=inv.getOrderId()%></td>
            <td><%=inv.getAmountDue()%> €</td>
            <td><%=inv.getOrderRaisedDt()%></td>
            <td><%=inv.getOrderSettledDt()%></td>
            <td><%=inv.getOrderCancelledDt()%></td>

            <td>
                <form name="f1" action="invoiceDetails.jsp" style="float: left;" >
                    <input id="" type="submit" name="<%=inv.getInvoiceId()%>" value="Details" >
                </form>
        <%}%>
        </tr>

    </table>
</div>
</div>
<%@include file="/etc/foot.jsp" %>
