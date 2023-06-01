<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <form action="salvarVeiculo" method="post">
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
</body>
</html>
<% } %>