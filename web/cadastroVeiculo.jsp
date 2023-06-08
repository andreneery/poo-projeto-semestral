<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONObject" %>

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
    <form onsubmit="addVeiculo(event)">
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
        // Função para obter o histórico de veículos
        function getHistorico() {
            const historicoStr = sessionStorage.getItem("historicoVeiculos");
            if (historicoStr) {
                return JSON.parse(historicoStr);
            } else {
                return [];
            }
        }

        // Função para salvar o histórico de veículos
        function saveHistorico(historico) {
            const historicoStr = JSON.stringify(historico);
            sessionStorage.setItem("historicoVeiculos", historicoStr);
        }

        // Função para adicionar o veículo
        function addVeiculo(event) {
            event.preventDefault();
            
            const modelo = document.getElementById('modelo').value;
            const marca = document.getElementById('marca').value;
            const cor = document.getElementById('cor').value;
            const placa = document.getElementById('placa').value;
            const renavam = document.getElementById('renavam').value;
            const ano = document.getElementById('ano').value;
            const preco = document.getElementById('preco').value;
            
            // Verifica se todos os campos estão preenchidos
            if (!modelo || !marca || !cor || !placa || !renavam || !ano || !preco) {
                alert("Por favor, preencha todos os campos");
                return;
            }
            
            const veiculo = {
                modelo: modelo,
                marca: marca,
                cor: cor,
                placa: placa,
                renavam: renavam,
                ano: ano,
                preco: preco
            };
            
            // Envia o veículo para o histórico
            const historico = getHistorico();
            historico.push(veiculo);
            saveHistorico(historico);
            
            // Limpa os campos do formulário
            document.getElementById('modelo').value = '';
            document.getElementById('marca').value = '';
            document.getElementById('cor').value = '';
            document.getElementById('placa').value = '';
            document.getElementById('renavam').value = '';
            document.getElementById('ano').value = '';
            document.getElementById('preco').value = '';
            
            // Exibe uma mensagem de sucesso
            alert("Veículo cadastrado com sucesso!");
        }
        
        // Verifica se há uma mensagem de sucesso na URL
        var urlParams = new URLSearchParams(window.location.search);
        var success = urlParams.get('success');
        if (success) {
            // Exibe um pop-up com a mensagem de sucesso
            alert(decodeURIComponent(success));
        }
    </script>
</body>
</html>

<% } %>
