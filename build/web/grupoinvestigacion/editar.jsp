<%-- 
    Document   : editar
    Created on : 6/04/2024, 6:46:04 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDITAR</title>
    </head>
    <body>
        <center>
  <h1>Editar Grupo Investigacion</h1>
  <hr/>
  <form action="/Procesos/grupoinvestigacion?accion-buscartredir=modificar" method="post">
    <table>
      <tr>
        <th style="text-align: right">ID Lider</th>
        <td><input type="text" name="id"/></td>
      </tr>
      <tr>
        <th><input type="submit" value="Penar*"></th>
        <td><input type="reset" name="Limp"/></td>
      </tr>
    </table>
  </form>
  <hr/>
   <%--<% if (alguien != null) { %> --%>
    <form action="/Procesos/grupoinvestigacion?accion=modificar" method="post">
      <table>
        <tr>
          <th style="text-align: right">ID Lider:</th>
          <%--<td><input type="text" name="id" value="<%= (alguien != null) ? alguien.getId() : "" %>" readonly="readonly"/></td>--%>
        </tr>
        <tr>
          <th style="text-align: right">Nombre Proyecto:</th>
        <%--  <td><input type="text" name="nombre" value="<%= (alguien != null) ? alguien.getNombre() : "" %>"/></td> --%>
        </tr>
        <tr>
          <th style="text-align: right">ID Del Investigador:</th>
        <%--  <td><input type="text" name="apellido" value="<%= (alguien != null) ? alguien.getApellido() : "" %>"/></td> --%>
        </tr>
        <tr>
          <th><input type="submit" value="Modificar"></th>
          <td><input type="reset" name="Limpias"/></td>
        </tr>
      </table>
    </form>
  <%-- } --%>
  <hr/>
  <p style="color:#FF0000;">
    <%-- <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %> --%>
  </p>
  <%--<% request.getSession().setAttribute("usuario.buscar", null); %>--%>
</center>

    </body>
</html>
