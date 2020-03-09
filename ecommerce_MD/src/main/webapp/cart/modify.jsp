<%-- 
    Document   : SimpleJsp.html
    Created on : 11-feb-2020, 16:32:58
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@page import="entity.Product"%>
<%@page import="java.util.Map"%>

<jsp:useBean id="carrito" class="beans.Cart" scope="session" />  


<%@include file="/etc/header.jsp" %>

<!-- body content -->
<div>
    <h1 class="titulo">Modify shopping-cart</h1>


        <div class="texto">
            <table border="1">
                <tr>
                    <td> Name </td>
                    <td> Description </td>
                    <td> Price </td>
                    <td> Quantity </td>
                    <td>  </td>

                </tr>

                <tr>

                    <td> <%="Name Hardcoded"%> </td>
                    <td> <%="Description Hardcoded"%> </td>
                    <td> <%="Price Hardcoded"%> </td>
                    <td> <%="Quantity Hardcoded"%> </td>
                    <%//Can we use de product id to the name or id or parameter to the submit in order to identify the line in the table user is clicking??%>
                    <form name="f1" action="delete.jsp" >
                        <td> <input id="" type="submit" name="b1" value="Remove" > </td>
                    </form>
                    
                </tr>
            </table>

        </div>



</div>
<%@include file="/etc/foot.jsp" %>
