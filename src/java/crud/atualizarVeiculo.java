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
        
        String placaCarro = request.getParameter("placa");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>" + placaCarro + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
