package crud;

import bancoDeDados.SQLiteConnectionManager;
import bancoDeDados.VeiculoDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "excluirVeiculo", urlPatterns = {"/excluirVeiculo"})
public class excluirVeiculo extends HttpServlet {
    
    private VeiculoDAO veiculoDAO; 
    
    @Override
    public void init() throws ServletException {
        super.init();
        veiculoDAO = new VeiculoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String placaCarro = request.getParameter("placa");
            veiculoDAO.delete(placaCarro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Redirecionar para a página de listagem com uma mensagem de sucesso
        String redirectUrl = "listarCadastro.jsp?success=true";
        response.sendRedirect(redirectUrl);
        
    }
}
