<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="image"></div>
            <div class="form">
                <h2>Faça seu Login</h2>
                <form method="post" action="login">
                <input type="text" id="username" name="username" placeholder="Username" required><br>
                <input type="password" id="password" name="password" placeholder="Senha" required><br>
                
                <button type="submit">Entrar</button>
                
                </form>
                <button class="buttonConsultor" type="button">
                    <a href="cadastroUsuario.jsp">Cadastrar Consultor</a>
                </button>
            </div>
    </div>
    
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
