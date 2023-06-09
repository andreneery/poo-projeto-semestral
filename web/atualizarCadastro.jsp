<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="bancoDeDados.VeiculoDAO" %>

<%String placaCarro = request.getParameter("placa");%>

<%
   VeiculoDAO veiculoDAO = new VeiculoDAO();
   List<Veiculo> veiculos = veiculoDAO.listarVeiculoSelecionado(placaCarro);
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
        
        <form action="./atualizarVeiculo?placa=<%= placaCarro %>" method="post">
            <% for (Veiculo veiculo : veiculos) { %>
            <label for="modelo">Modelo:</label>
            <input type="text" id="modelo" value="<%= veiculo.getModelo() %>" name="modelo" required><br><br>
            
            <label for="marca">Marca:</label>
            <input type="text" id="marca" value="<%= veiculo.getMarca() %>" name="marca" required><br><br>

            <label for="cor">Cor:</label>
            <input type="text" id="cor" value="<%= veiculo.getCor() %>" name="cor" required><br><br>

            <label for="placa">Placa:</label>
            <input type="text" id="placa" value="<%= veiculo.getPlaca() %>" name="placa" required><br><br>

            <label for="renavam">Renavam:</label>
            <input type="text" id="renavam" value="<%= veiculo.getRenavam() %>" name="renavam" required><br><br>

            <label for="ano">Ano:</label>
            <input type="text" id="ano" value="<%= veiculo.getAno() %>" name="ano" required><br><br>

            <label for="preco">Preço:</label>
            <input type="text" id="preco" value="<%= veiculo.getPreco() %>" name="preco" required><br><br>

            <input type="submit" value="Atualizar">
            <% } %>
        </form>
        
    </body>
</html>
