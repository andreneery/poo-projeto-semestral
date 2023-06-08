<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>

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
    <h1>Histórico de Veículos</h1>
        <table id="historicoTable">
            <thead>
                <tr>
                    <th>Modelo</th>
                    <th>Marca</th>
                    <th>Cor</th>
                    <th>Placa</th>
                    <th>Renavam</th>
                    <th>Ano</th>
                    <th>Preço</th>
                </tr>
            </thead>
            <tbody>
                <script>
                    // Função para obter o histórico de veículos da sessionStorage
                    function getHistorico() {
                        const historicoStr = sessionStorage.getItem("historicoVeiculos");
                        if (historicoStr) {
                            return JSON.parse(historicoStr);
                        } else {
                            return [];
                        }
                    }

                    // Função para preencher a tabela com os dados do histórico de veículos
                    function preencherTabela() {
                        const historico = getHistorico();
                        const tabela = document.getElementById("historicoTable");

                        for (const veiculo of historico) {
                            const row = tabela.insertRow();

                            const modeloCell = row.insertCell();
                            modeloCell.textContent = veiculo.modelo;

                            const marcaCell = row.insertCell();
                            marcaCell.textContent = veiculo.marca;

                            const corCell = row.insertCell();
                            corCell.textContent = veiculo.cor;

                            const placaCell = row.insertCell();
                            placaCell.textContent = veiculo.placa;

                            const renavamCell = row.insertCell();
                            renavamCell.textContent = veiculo.renavam;

                            const anoCell = row.insertCell();
                            anoCell.textContent = veiculo.ano;

                            const precoCell = row.insertCell();
                            precoCell.textContent = veiculo.preco;
                        }
                    }

                    // Chame a função para preencher a tabela quando a página for carregada
                    window.addEventListener("DOMContentLoaded", preencherTabela);
                </script>
            </tbody>
        </table>
        
</body>
</html>
<% } %>
