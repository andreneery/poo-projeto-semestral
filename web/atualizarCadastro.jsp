<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bancoDeDados.VeiculoDAO" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("username") == null) {
        // Redireciona para a página de login caso a sessão não esteja ativa
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    } else {
        String username = (String) currentSession.getAttribute("username");

        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            String placaCarro = request.getParameter("placa"); // Obtenha o valor da placa do parâmetro "placa" em vez de "placaCarro"
            List<Veiculo> veiculos = veiculoDAO.listarVeiculoSelecionado(placaCarro);
            if (veiculos != null) {
                request.setAttribute("veiculos", veiculos);
            }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/cadastroVeiculo.css">
        <link rel="stylesheet" type="text/css" href="css/global.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
        <title>Atualizar Veículo</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <h1>Atualizar Veículo</h1>

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
            <input type="text" id="preco" value="<%= veiculo.getPreco() %>" name="preco" onkeypress="$(this).mask('######0.00', {reverse: true});" required><br><br>

            <input type="submit" value="Atualizar">
            <% } %>
        </form>

    </body>
</html>

<%
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
%>
