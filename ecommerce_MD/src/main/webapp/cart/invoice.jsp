<%-- 
    Document   : SimpleJsp.html
    Created on : 11-feb-2020, 16:32:58
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@include file="/etc/header.jsp" %>
<%@include file="/etc/connection.jsp" %>
<%@page import="java.util.*,java.text.*"%>
<%@page import="business.Articulo"%>
<jsp:useBean id="carrito" class="beans.Carrito" scope="session" />  

<%--Función que genera un número aleatorio, que se utilizará como identificador de cada factura y línea de factura.
--%>
<%! 

String hoy(){
  java.util.Date fecha = new java.util.Date();
  java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyyMMdd");
  String cadenaFecha = formato.format(fecha);
 return cadenaFecha;
}
%>

<%
// valor máximo de id 
query="select max(id) from facturas";
rs=stmt.executeQuery(query);
int numfac=0;
if (rs.next()){
  numfac=rs.getInt(1);
}

// Sentencia SQL para insertar en las facturas los datos de id, fecha, cliente y estado del pedido.
query= "INSERT INTO facturas (id,fecha,cliente,estado) "+
      " VALUES ("+(++numfac)+",'"+hoy()+"','"+ctrl.getIdUser()+"','E')";
//println(query)
stmt.executeUpdate(query);


Articulo ar=null;
for (int i=0;i<carrito.cuantos();i++){
        ar=(Articulo)carrito.sacar(i);
      
      // Sentencia SQL para insertar en cada línea de la factura el id, la factura
        // a la que pertenece, el código de artículo, item, percio y su stock.
        query="INSERT INTO lineasfactura (factura,articulo,cantidad,precio)"+
          " VALUES ("+numfac+","+ ar.getCodigo()+
              ", '"+ar.getCantidad()+"',"+ar.getPrecio()+");";

      stmt.executeUpdate(query);
      // out.println(query);

}

// Tras generar la factura, se vacía el carrito.
carrito.vaciar();


%>

<!-- body content -->
<div>
    <h1 class="titulo">Generating INVOICE</h1>
   
    <h3> Invocie number <%=numfac%> has been generated </h3>

</div>
<%@include file="/etc/foot.jsp" %>
