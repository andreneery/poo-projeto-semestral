<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
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
