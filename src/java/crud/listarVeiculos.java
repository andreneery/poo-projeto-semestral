package crud;

import bancoDeDados.VeiculoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Veiculo;

@WebServlet(name = "listarVeiculos", urlPatterns = {"/listarVeiculos"})
public class listarVeiculos extends HttpServlet {
    
    private VeiculoDAO veiculoDAO; // Objeto para acessar o banco de dados
    
    @Override
    public void init() throws ServletException {
        super.init();
        veiculoDAO = new VeiculoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
            request.setAttribute("veiculos", veiculos);
            request.getRequestDispatcher("listarCadastro.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
