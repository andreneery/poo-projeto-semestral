package crud;

import bancoDeDados.SQLiteConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "salvarVeiculo", urlPatterns = {"/salvarVeiculo"})
public class salvarVeiculo extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        SQLiteConnectionManager.createTable();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obter os valores dos campos do formulário
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String cor = request.getParameter("cor");
        String placa = request.getParameter("placa");
        String renavam = request.getParameter("renavam");
        String anoString = request.getParameter("ano");
        String precoString = request.getParameter("preco");
        
        // Verificar se os campos obrigatórios estão preenchidos
        if (modelo == null || marca == null || cor == null || placa == null || renavam == null || anoString == null || precoString == null ||
                modelo.isEmpty() || marca.isEmpty() || cor.isEmpty() || placa.isEmpty() || renavam.isEmpty() || anoString.isEmpty() || precoString.isEmpty()) {
            // Retornar um erro para o cliente se algum campo estiver em branco
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        int ano = 0;
        double preco = 0.0;

        // Verificar se os valores de ano e preco são números válidos
        try {
            ano = Integer.parseInt(anoString);
            preco = Double.parseDouble(precoString);
        } catch (NumberFormatException e) {
            // Tratar a exceção se os valores não forem números válidos
            e.printStackTrace();
            // Retornar um erro para o cliente
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // lógica para salvar os dados do veículo no banco de dados
        try (Connection conn = SQLiteConnectionManager.getConnection()) {
            String sql = "INSERT INTO veiculo (modelo, marca, cor, placa, renavam, ano, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, modelo);
                stmt.setString(2, marca);
                stmt.setString(3, cor);
                stmt.setString(4, placa);
                stmt.setString(5, renavam);
                stmt.setInt(6, ano);
                stmt.setDouble(7, preco);
                stmt.executeUpdate();
                System.out.println("Veículo cadastrado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        
        // Criar um objeto JSON com os dados do veículo
        JSONObject veiculoJson = new JSONObject();
        veiculoJson.put("modelo", modelo);
        veiculoJson.put("marca", marca);
        veiculoJson.put("cor", cor);
        veiculoJson.put("placa", placa);
        veiculoJson.put("renavam", renavam);
        veiculoJson.put("ano", ano);
        veiculoJson.put("preco", preco);
        
        // Definir o tipo de conteúdo da resposta como JSON
        response.setContentType("application/json");
        
//        // Escrever o JSON na resposta
        response.getWriter().write(veiculoJson.toString());

        String sucessCreate = "Dados armazenado com sucesso.";
        String encodedMessage = java.net.URLEncoder.encode(sucessCreate, "UTF-8");
        response.sendRedirect(request.getContextPath() + "/cadastroVeiculo.jsp?sucess=" + encodedMessage);
    }
}
