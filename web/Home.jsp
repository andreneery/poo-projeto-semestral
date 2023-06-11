<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Inicio do código para que  apagina seja acessada somente por autenticação-->
<% 
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("username") == null) {
        // Redireciona para a página de login caso a sessão não esteja ativa
        response.sendRedirect(request.getContextPath() + "/login");
    }
    else {
        String username = (String) currentSession.getAttribute("username");
%>
<!-- Fim do código -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
        <jsp:include page="navbar.jsp" />
    <h1>Bem-vindo, <%= username %>!</h1>

</body>
</html>
<%
    }
%>
