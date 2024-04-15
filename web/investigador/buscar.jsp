<%-- 
    Document   : buscar
    Created on : 6/04/2024, 6:04:10 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BUSCAR</title>
    </head>
    <body>
        <center>
  <h1>Buscar Investigador </h1>
  <hr/>
  <form action="/proceso/investigador?accion=buscartredir&buscar" method="post">
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
        <%--<td style="text-align: left"><%= (alguien != null) ? alguien.getNombre() : "" %></td>--%>
      </tr>
      <tr>
        <th style="text-align: right">Apellido:</th>
        <%-- <td style="text-align: left"><%= (alguien != null) ? alguien.getApellido() : "" %></td>--%>
      </tr>
      <tr>
        <th style="text-align: right">Telefono:</th>
         <%--<td style="text-align: left"><%= (alguien != null) ? alguien.getTelefono() : "" %></td>--%>
      </tr>

    </table>
  </form>
  <hr/>
  <p style="color:#FF0000;">
    <!-- (mensaje != null && mensaje.isEmpty()) ? mensaje : "" -->
  </p>
  <% request.getSession().setAttribute("investigador.buscar", null); %>
</center>

    </body>
</html>
