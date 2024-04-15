<%-- 
    Document   : listar
    Created on : 6/04/2024, 6:57:24 p. m.
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTAR</title>
    </head>
    <body>
        <center>
  <h1>Todos los investigadores agregados aqui</h1>
  <%-- if (mensaje != null) {
     out.print(mensaje);
  } else { --%>
    <table border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Telefono</th>
        </tr>
      </thead>
      <tbody>
        <%-- int contador = 0;
          for (Usuario alguien : listado) {
             contador++;
        %>
        <tr>
          <td><%= contador %></td>
          <td><%= alguien.getId() %></td>
          <td><%= alguien.getNombre() %></td>
          <td><%= alguien.getApellido() %></td>
          <td><%= alguien.getTelefono() %></td>
        </tr>
        <% } --%>
      </tbody>
    </table>
  <%-- } --%>
  <hr>
  <a href="../investigador/index.jsp"><<:: VOLVER AL MENU</a>
</center>
    </body>
</html>
