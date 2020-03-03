
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
                        <td width="80%"><h1 class="center">eCommerce simple demostration with JSP</h1></td>
                        <td ><h4 class="center">User:<br><%= ctrl.getNameUser()%></h4></td>

                    </tr>
                </table>
            </div>
            <ul>
               
                <li class="dropdown">
                    <a  class='active'
                        href="/home/index2.jsp" target="_blank">Home</a>
 
                   
                </li>
                <li class="dropdown">
                    <a    href="/buy/index.jsp" class="dropbtn">Buy</a>
                    <div class="dropdown-content">
                        <a href="/buy/index.jsp">List items and buy</a>

                    </div>
                </li>
                <li class="dropdown">
                    <a  href="/cart/index.jsp" class="dropbtn">Shopping cart</a>
                    <div class="dropdown-content">
                        <a href="/cart/index.jsp">List </a>
                        <a href="/cart/modify.jsp">Modify </a>

                        <a href="/cart/invoice.jsp">Pass to invoice </a>
                        <hr/>
                        <a href="/cart/clear.jsp">clear out </a>

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
                        <a href="ManyToManyTest.jsp">Test</a>
                                           </div>
                </li>

                <li style="float:right"><a  href="#about">About</a></li>
            </ul>
        </header>