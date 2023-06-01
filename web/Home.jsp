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
    <title>Home</title>
</head>
<body>
    <h1>Bem-vindo, <%= username %>!</h1>
    <!-- Conteúdo da página segura -->
    
    <a href="logoutServlet">Logout</a>

</body>
</html>
<%
    }
%>
