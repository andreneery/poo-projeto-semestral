package crud;

import bancoDeDados.SQLiteConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "excluirVeiculo", urlPatterns = {"/excluirVeiculo"})
public class excluirVeiculo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String placaCarro = request.getParameter("placa");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try (Connection conn = SQLiteConnectionManager.getConnection()) {
                String sql = "DELETE FROM veiculo WHERE placa = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, placaCarro);
                    int rowsDeleted = stmt.executeUpdate();
                    // Verificar se a tabela foi excluída com sucesso
                    if (rowsDeleted > 0) {
                        String mensagem = "Dados excluídos com sucesso";
                        String encodedMessage = java.net.URLEncoder.encode(mensagem, "UTF-8");
                        response.sendRedirect(request.getContextPath() + "/listarCadastro.jsp?mensagem=" + encodedMessage);
                    } else {
                        String mensagem = "A tabela não foi encontrada";
                        String encodedMessage = java.net.URLEncoder.encode(mensagem, "UTF-8");
                        response.sendRedirect(request.getContextPath() + "/listarCadastro.jsp?mensagem=" + encodedMessage);
                    }
                } catch (SQLException e) {
                    out.println("Ocorreu um erro ao excluir a tabela: " + e.getMessage());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
