
<%@include file="/etc/header.jsp" %>

<!-- body content -->

<div>
    <h1 class="titulo">CATALOG</h1>
  
    <%
        //JPA CODE TO RETRIEVE PRODUCTS
    %>      
   
 <form action="/cart/cart_put.jsp">
      <div class="texto">
<table border="1">
<tr>
<td> Descripción </td>
<td> Precio </td>
<td> Cantidad </td>

</tr>

<tr>
   
<td> <%="Nada"%> </td>
<td> <%="Nada"%> </td>
<td> <input type="text" name="<%="Nada"%>"> </td>
</tr>

<tr>
   
<td colspan="3">
<input type="submit" name="b1" >
</td>
</tr>
</table>

</div>

</form>

   
</div>

<%@include file="../etc/foot.jsp" %>