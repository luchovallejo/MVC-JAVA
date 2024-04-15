<%-- 
    Document   : eliminar
    Created on : 6/04/2024, 6:46:23 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELIMINAR</title>
    </head>
    <body>
        <center>
  <h1>Eliminar Investigador</h1>
  <hr/>
  <form action="/Procesos/grupoinvestigacion?accion-buscar&redir=borrar" method="post">
    <table>
      <tr>
        <th style="text-align: right">ID:</th>
        <td><input type="text" name="id"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="Buscar"></td>
        <td><input type="reset" name="Limpiar"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Nombre:</th>
      <%--  <td style="text-align: left"><%= (alguien != null) ? alguien.getNombre() : "" %></td> --%>
      </tr>
      <tr>
        <th style="text-align: right">Apellido:</th>
       <%-- <td style="text-align: left"><%= (alguien != null) ? alguien.getApellido() : "" %></td> --%>
      </tr>
      <tr>
        <th style="text-align: right">Telefono:</th>
       <%-- <td style="text-align: left"><%= (alguien != null) ? alguien.getTelefono() : "" %></td> --%>
      </tr>
    </table>
  </form>
  <hr/>
    <%-- <% if (alguien != null) { %> 
    <form action="/Procesos/grupoinvestigacion?accion=borrar" method="post">
      <%-- <input type="hidden" name="id" value="<%= alguien.getId()%>"> 
      <table>
        <tr><td><input type="submit" value="Eliminar"/></td></tr>
      </table>
    </form>
  <%-- } %>
  <p style="color:#FF0000;"> --%>
   <%-- <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %> --%>
  </p>
 <%-- <% request.getSession().setAttribute("grupoinvestigacion.buscar", null); %> --%>
</center>
    </body>
</html>
