<%-- 
    Document   : agregar
    Created on : 6/04/2024, 6:03:57 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AGREGAR</title>
    </head>
    <body>
        <center>
  <h1>Agregar Grupo Investigacion </h1>
  <hr/>
  <form action="/proceso/grupoinvestigacion?accion agregar" method="post">
    <table>
      <tr>
        <th style="text-align: right">ID Lider:</th>
        <td><input type="text" name="id lider"/></td>
      </tr>
      <tr>
        <th style="text-align: right">Nombre Proyecto:</th>
        <td><input type="text" name="nombre proyecto"/></td>
      </tr>
      <tr>
        <th style="text-align: right">ID Del Investigador:</th>
        <td><input type="text" name="id del investigador"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="ENTRAR"></td>
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
