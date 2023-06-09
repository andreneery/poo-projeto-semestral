package login;

import bancoDeDados.SQLiteConnectionManager;
import bancoDeDados.UsuariosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuarios;
import java.sql.*;

@WebServlet(name = "cadastroUsuarioServlet", urlPatterns = {"/cadastroUsuario"})
public class cadastroUsuario extends HttpServlet {

   private UsuariosDAO usuariosDAO;
   
   @Override
   public void init() throws ServletException {
       super.init();
       usuariosDAO = new UsuariosDAO();
   }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String senha = req.getParameter("senha");

        try {
            Connection c = SQLiteConnectionManager.getConnection();
            Statement s = c.createStatement();
            s.execute(Usuarios.getCreateStatement());

            usuariosDAO.salvarUsuario(new Usuarios(nome, email, username, senha));
        } catch (Exception e) {
//            resp.sendRedirect("erro.jsp"); // Redireciona para uma página de erro
        }
        
        // Criar um objeto Veiculo com os dados informados
        Usuarios usuarios = new Usuarios(nome, email, username, senha);
        usuarios.setNomeCompleto(nome);
        usuarios.setEmail(email);
        usuarios.setUsername(username);
        usuarios.setSenha(senha);

        try {
            // Salvar o usuario no banco de dados
            usuariosDAO.salvarUsuario(usuarios);
        } catch (Exception ex) {
            Logger.getLogger(cadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

//        // Redirecionar para a página de cadastro com uma mensagem de sucesso
//        String redirectUrl = "cadastroUsuario.jsp?success=true";
//        resp.sendRedirect(redirectUrl);
//    }
    }
}
