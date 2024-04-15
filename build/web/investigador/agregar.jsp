<%-- 
    Document   : agregar
    Created on : 6/04/2024, 6:03:57 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String rutaRaiz = request.getServletContext().getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AGREGAR</title>
    </head>
    <body>
        <center>
  <h1>Agregar Investigador </h1>
  <hr/>
  <form action="<%= rutaRaiz %>/cinvestigador" method="post">
    <table>
      <tr>
        <th style="text-align: right">ID:</th>
        <td><input type="text" name="id"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Nombre:</th>
        <td><input type="text" name="nombre"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Apellido:</th>
        <td><input type="text" name="apellido"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Telefono:</th>
        <td><input type="text" name="telefono"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="GUARDAR"></td>
        <td><input type="reset" name="LIMPIAR"/></td>
      </tr>
    </table>
  </form>
  <p style="color:#FF0000;">
    <hr/>
    <!-- (mensaje != null && mensaje.isEmpty())?mensaje: -->
  </p>
</center>

    </body>
</html>
