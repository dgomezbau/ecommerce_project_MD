
<%@include file="/etc/connection.jsp" %>
<%@include file="/etc/header.jsp" %>

<!-- body content -->

<div>
    <h1 class="titulo">List of items  to select and buy</h1>
  
      
   
 <form action="/cart/cart_put.jsp">
      <div class="texto">
<table border="1">
<tr>
<td> Descripción </td>
<td> Precio </td>
<td> Cantidad </td>

</tr>

<%
query="select id,descripcion, precio from articulos";
rs=stmt.executeQuery(query);

while (rs.next()){

%>
<tr>
   
<td> <%=rs.getString(2)%> </td>
<td> <%=rs.getDouble(3)%> </td>
<td> <input type="text" name="<%=rs.getInt(1)%>"> </td>
</tr>
<%}
%>
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