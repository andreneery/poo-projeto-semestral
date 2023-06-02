<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Veiculo" %>
<% 
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("username") == null) {
        // Redireciona para a página de login caso a sessão não esteja ativa
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
    else {
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
    <h1>Listagem de Veículos</h1>
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
            <% 
            List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
            if (veiculos != null && !veiculos.isEmpty()) {
                for (Veiculo veiculo : veiculos) {
            %>
            <tr>
                <td><%= veiculo.getModelo() %></td>
                <td><%= veiculo.getMarca() %></td>
                <td><%= veiculo.getCor() %></td>
                <td><%= veiculo.getPlaca() %></td>
                <td><%= veiculo.getRenavam() %></td>
                <td><%= veiculo.getAno() %></td>
                <td><%= veiculo.getPreco() %></td>
                <td>
                    <form action="excluirVeiculo" method="post">
                        <input type="hidden" name="placa" value="<%= veiculo.getPlaca() %>">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="8">Nenhum veículo cadastrado.</td>
            </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</body>
</html>
<% } %>
