<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <style>
        body {
            background-color: #333;
            color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #444;
            border-radius: 10px;
            box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            border-radius: 4px;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #fff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1>Cadastro de Usuário</h1>
    <form action="./cadastroUsuario" method="post">
        <label for="nome">Nome Completo:</label>
        <input type="text" id="nome" name="nome" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required><br><br>
        
        <input type="submit" value="Cadastrar">
    </form>
    <a href="index.jsp">Voltar</a>
    <script>
      // Verifica se há uma mensagem na URL
      var urlParams = new URLSearchParams(window.location.search);
      var success = urlParams.get('success');
      if (success === 'true') {
        // Exibe um pop-up com a mensagem de sucesso
        alert("Seu cadastro foi realizado com sucesso.");
        // Redireciona para a página de cadastro
        window.location.href = "index.jsp";
      }
    </script>
</body>
</html>
