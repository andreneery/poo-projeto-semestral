package login;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bancoDeDados.UsuariosDAO;
import model.Usuarios;

@WebServlet(name = "loginUser", urlPatterns = {"/login"})
public class loginUser extends HttpServlet {

    private UsuariosDAO usuariosDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        usuariosDAO = new UsuariosDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Verificar as credenciais do usuário no banco de dados
            Usuarios usuario = usuariosDAO.obterUsuario(username);

            if (usuario != null && usuario.getSenha().equals(password)) {
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
        } catch (Exception e) {
            // Tratar qualquer exceção que ocorra ao verificar as credenciais do usuário
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=Erro%20no%20servidor");
        }
    }
}
