package crud;

import bancoDeDados.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "atualizarVeiculo", urlPatterns = {"/atualizarVeiculo"})
public class atualizarVeiculo extends HttpServlet {
    
    private VeiculoDAO veiculoDAO; 
    
    @Override
    public void init() throws ServletException {
        super.init();
        veiculoDAO = new VeiculoDAO();
    };
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");
        
        try {
            //String placaCarro = request.getParameter("placa");
            veiculoDAO.atualizarVeiculo(placa, modelo);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Redirecionar para a página de listagem com uma mensagem de sucesso
        String redirectUrl = "listarCadastro.jsp?mesage=true";
        response.sendRedirect(redirectUrl);
        
    }
    
    
}
