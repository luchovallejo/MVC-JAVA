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
  <h1>Buscar Grupo Investigacion </h1>
  <hr/>
  <form action="/ejemplosesion/usuario?accion=buscartredir&buscar" method="post">
    <table>
      <tr>
        <th style="text-align: right">ID Lider:</th>
        <td><input type="text" name="id"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="Buscar"></td>
        <td><input type="reset" name="Limpiar"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Nombre Proyecto:</th>
        <%--<td style="text-align: left"><%= (alguien != null) ? alguien.getNombreProyecto() : "" %></td>--%>
      </tr>
      <tr>
        <th style="text-align: right">ID Del Investigador:</th>
        <%-- <td style="text-align: left"><%= (alguien != null) ? alguien.getIdInvestigador() : "" %></td>--%>
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
