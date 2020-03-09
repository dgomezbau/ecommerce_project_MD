
<%--
    Document   : newjsp
    Created on : 10-feb-2020, 16:07:00
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@include file="/etc/control.jsp" %>
<!DOCTYPE html>
<!--
-->
<html>
    <head>
        <title>Home</title>
        <link rel="stylesheet" href="/css/myCSS.css" type="text/css">
    </head>
    <body>
        <header>
            <div >
                <table  style='background-color: #ddddff;width:100%'>
                    <tr>
                        <td style='background-color: white'><img src="/images/logo.png" width="80" alt="logo servlet"/></td>
                        <td width="80%"><h1 class="center">Games eCommerce</h1></td>
                        <td ><h4 class="center">User:<br><%= ctrl.getNameUser()%></h4></td>

                    </tr>
                </table>
            </div>
            <ul>
               
                <li class="dropdown">
                    <a  class='active'
                        href="/ecommerce_MD/home/index.jsp" target="_blank">Home</a>
 
                   
                </li>
                <li class="dropdown">
                    <a    href="/ecommerce_MD/catalog/productList.jsp" class="dropbtn">Buy</a>
                    <div class="dropdown-content">
                        <a href="/ecommerce_MD/catalog/productList.jsp">List items and buy</a>

                    </div>
                </li>
                <li class="dropdown">
                    <a  href="/ecommerce_MD/cart/index.jsp" class="dropbtn">Shopping cart</a>
                    <div class="dropdown-content">
                        <a href="/ecommerce_MD/cart/index.jsp">List </a>
                        <a href="/ecommerce_MD/cart/modify.jsp">Modify </a>

                        <a href="/ecommerce_MD/cart/invoice.jsp">Pass to invoice </a>
                        <hr/>
                        <a href="/ecommerce_MD/cart/clear.jsp">clear out </a>

                    </div>
                </li>
                <li class="dropdown">
                    <a href="" class="dropbtn">My invoices</a>
                    <div class="dropdown-content">
                        <a href="">List</a>
                                           </div>
                </li>
                
                <li class="dropdown">
                    <a href="" class="dropbtn">Query</a>
                    <div class="dropdown-content">
                        <a href="/ecommerce_MD/ManyToManyTest.jsp">Test</a>
                                           </div>
                </li>

                <li style="float:right"><a  href="#about">About</a></li>
            </ul>
        </header>