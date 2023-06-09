<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Veiculo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>
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
                <th>Ações</th>
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
                    <td><%= veiculo.getPreco() %></td>
                    <td>
                        <form action="excluirVeiculo?placa=<%= veiculo.getPlaca() %>" method="post">
                            <button type="submit">Deletar</button>
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
    </script>
    
</body>
</html>
<% } %>
