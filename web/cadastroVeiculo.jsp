<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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
    <title>Cadastro de Veículo</title>
</head>
<body>
    <!-- menu -->
    <jsp:include page="navbar.jsp" />
    <!-- fim menu -->
    <h1>Cadastro de Veículo</h1>
    <form action="./salvarVeiculo" method="post">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo" required><br><br>
        
        <label for="marca">Marca:</label>
        <input type="text" id="marca" name="marca" required><br><br>
        
        <label for="cor">Cor:</label>
        <input type="text" id="cor" name="cor" required><br><br>
        
        <label for="placa">Placa:</label>
        <input type="text" id="placa" name="placa" required><br><br>
        
        <label for="renavam">Renavam:</label>
        <input type="text" id="renavam" name="renavam" required><br><br>
        
        <label for="ano">Ano:</label>
        <input type="text" id="ano" name="ano" required><br><br>
        
        <label for="preco">Preço:</label>
        <input type="text" id="preco" name="preco" required><br><br>
        
        <input type="submit" value="Cadastrar">
    </form>
    <script>
        // Verifica se há uma mensagem na URL
        var urlParams = new URLSearchParams(window.location.search);
        var success = urlParams.get('success');
        var placaError = urlParams.get('placaError');

        if (success === 'true') {
            // Exibe um pop-up com a mensagem de sucesso
            alert("Seu cadastro foi realizado com sucesso.");
            // Redireciona para a página de cadastro
            window.location.href = "cadastroVeiculo.jsp";
        }

        if (placaError === 'true') {
            // Exibe um pop-up com a mensagem de erro
            alert("A placa informada já está cadastrada.");
            // Remove o parâmetro da URL para evitar que o pop-up seja exibido novamente
            var url = new URL(window.location.href);
            url.searchParams.delete('placaError');
            window.history.replaceState({}, '', url);
        }
    </script>
</body>
</html>

<% } %>
