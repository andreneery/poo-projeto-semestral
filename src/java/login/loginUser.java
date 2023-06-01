/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package login;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author onboarding
 */
@WebServlet(name = "loginUser", urlPatterns = {"/login"})
public class loginUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Verificar as credenciais do usuário (por exemplo, consultar um banco de dados)

        if (username.equals("admin") && password.equals("senha")) {
            // Autenticação bem-sucedida
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        } else {
            // Credenciais inválidas
            String errorMessage = "Login inválido. Verifique suas credenciais.";
            String encodedMessage = java.net.URLEncoder.encode(errorMessage, "UTF-8");
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=" + encodedMessage);
        }
    }
}
