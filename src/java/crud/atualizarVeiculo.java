package crud;

import bancoDeDados.VeiculoDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Veiculo;

@WebServlet(name = "atualizarVeiculo", urlPatterns = {"/atualizarVeiculo"})
public class atualizarVeiculo extends HttpServlet {
    
    private VeiculoDAO veiculoDAO; 
    
    @Override
    public void init() throws ServletException {
        super.init();
        veiculoDAO = new VeiculoDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String placaCarro = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String cor = request.getParameter("cor");
        String renavam = request.getParameter("renavam");
        String anoStr = request.getParameter("ano");
        String precoStr = request.getParameter("preco");

        int ano = Integer.parseInt(anoStr);
        double preco = Double.parseDouble(precoStr);

        Veiculo veiculo = new Veiculo(modelo, marca, cor, placaCarro, renavam, ano, preco);
        boolean atualizado = false;
        try {
            atualizado = veiculoDAO.atualizarVeiculo(veiculo);
        } catch (Exception ex) {
            Logger.getLogger(atualizarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (atualizado) {
            response.sendRedirect("listarCadastro.jsp");
        } else {
            response.sendRedirect("erro.jsp");
        }
    }
}
