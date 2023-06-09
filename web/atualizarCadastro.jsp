<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="bancoDeDados.VeiculoDAO" %>

<%String placaCarro = request.getParameter("placa");%>

<%
   VeiculoDAO veiculoDAO = new VeiculoDAO();
   List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
        if (veiculos != null) {
            request.setAttribute("veiculos", veiculos);
}
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Veículo</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <% for (Veiculo veiculo : veiculos) { %>
        
        <form action="./atualizarVeiculo?placa=<%= veiculo.getPlaca() %>" method="post">
            <label for="modelo">Modelo:</label>
            <input type="text" id="modelo" value="<%= veiculo.getModelo() %>" name="modelo" required><br><br>

            <input type="submit" value="Atualizar">
        </form>
        
        <% } %>
        
    </body>
</html>
