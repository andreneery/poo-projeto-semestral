<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bancoDeDados.VeiculoDAO" %>

<%
   VeiculoDAO veiculoDAO = new VeiculoDAO();
   List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
        if (veiculos != null) {
            request.setAttribute("veiculos", veiculos);
}
%>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("username") == null) {
        // Redireciona para a página de login caso a sessão não esteja ativa
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    } else {
        String username = (String) currentSession.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/listarCadastro.css">
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <title>Listagem de Veículos</title>
</head>
<body>
    <!-- menu -->
    <jsp:include page="navbar.jsp" />
    <!-- fim menu -->
    <h1>Histórico de Veículos</h1>
    <table>
        <thead>
            <tr>
                <th>Modelo</th>
                <th>Marca</th>
                <th>Cor</th>
                <th>Placa</th>
                <th>Renavam</th>
                <th>Ano</th>
                <th>Preço</th>
                <th>Excluir</th>
                <th>Editar</th>
            </tr>
        </thead>
        <tbody>
            <% for (Veiculo veiculo : veiculos) { %>
                <tr>
                    <td><%= veiculo.getModelo() %></td>
                    <td><%= veiculo.getMarca() %></td>
                    <td><%= veiculo.getCor() %></td>
                    <td><%= veiculo.getPlaca() %></td>
                    <td><%= veiculo.getRenavam() %></td>
                    <td><%= veiculo.getAno() %></td>
                    <td class="currency"><%= veiculo.getPreco() %></td>
                    <td>
                        <form action="excluirVeiculo?placa=<%= veiculo.getPlaca() %>" method="post">
                            <button class="buttonDelet" type="submit">Deletar</button>
                        </form>
                    </td>
                    <td>
                        <form action="atualizarCadastro.jsp?placa=<%= veiculo.getPlaca() %>" method="post">
                            <button class="buttonEdit" type="submit">Editar</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
        
    <script>
        // Verifica se há uma mensagem na URL
        var urlParams = new URLSearchParams(window.location.search);
        var success = urlParams.get('success');
        if (success === 'true') {
            // Exibe um pop-up com a mensagem de sucesso
            alert("Deletado com sucesso");
        }
        var mesage = urlParams.get('mesage');
        if (mesage === 'true') {
            // Exibe um pop-up com a mensagem de sucesso
            alert("Atualizado com sucesso");
        }
        
        //converter valores do preço para reais
        var currencyElements = document.getElementsByClassName("currency");
        for (var i = 0; i < currencyElements.length; i++) {
          var value = parseFloat(currencyElements[i].innerHTML);
          currencyElements[i].innerHTML = value.toLocaleString("pt-BR", {
            style: "currency",
            currency: "BRL",
          });
        }
    </script>
</body>
</html>
<% } %>
