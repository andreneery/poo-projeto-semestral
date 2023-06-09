<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form method="post" action="login">
        <label for="username">Usuário:</label>
        <input type="text" id="username" name="username" required><br>
        
        <label for="password">Senha:</label>
        <input type="password" id="password" name="password" required><br>
        
        <input type="submit" value="Entrar">
    </form>
    
    <button type="button">
        <a href="cadastroUsuario.jsp">Cadastrar Consultor</a>
    </button>
    
    <script>
        // Verifica se há uma mensagem de erro na URL
        var urlParams = new URLSearchParams(window.location.search);
        var error = urlParams.get('error');
        if (error) {
            // Exibe um pop-up com a mensagem de erro
            alert(decodeURIComponent(error));
        }
    </script>
</body>
</html>
