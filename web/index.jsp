<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            background-image: url('imagem/logo do vetor 2.jpg');
            background-size: 80%;
            background-repeat: no-repeat;
            background-position: top;
            font-family: Arial, sans-serif;
        }
        
        /* Defina o tamanho da página para ocupar apenas metade */
        html, body {
            height: 100%;
            overflow: hidden;
        }
        
        /* Defina o tamanho do conteúdo para ocupar a outra metade */
        .content {
            height: 70%;
            overflow-y: auto;
        }
        
        .container {
            width: 300px;
            margin: 1 auto;
            padding: 2px;
            background-color: rgba(0, 0, 0, 0.8);
            border-radius: 40px;
        }
        
        .container h2 {
            text-align: center;
            color: #fff;
        }
        
        .container input[type="text"],
        .container input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 7px;
        }
        
        .container input[type="submit"] {
            width: 100%;
            padding: 8px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            border-radius: 6px;
        }
        
        .container input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        .container .forgot-password {
            text-align: center;
            margin-top: 12px;
        }
        
        .container a {
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Tela de Login</h2>
        <form method="post" action="login">
        <input type="text" id="username" name="username" required><br>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Entrar">
    </form>
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
