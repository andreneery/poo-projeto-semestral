package crud;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "salvarVeiculo", urlPatterns = {"/salvarVeiculo"})
public class salvarVeiculo extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obter os valores dos campos do formulário
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String cor = request.getParameter("cor");
        String placa = request.getParameter("placa");
        String renavam = request.getParameter("renavam");
        String ano = request.getParameter("ano");
        String preco = request.getParameter("preco");
        
        // Aqui você pode fazer a lógica para salvar os dados do veículo no banco de dados
        
        // Redirecionar para uma página de sucesso ou exibir uma mensagem informando que o veículo foi cadastrado com sucesso
        response.sendRedirect(request.getContextPath() + "/cadastroSucesso.jsp");
    }
}
