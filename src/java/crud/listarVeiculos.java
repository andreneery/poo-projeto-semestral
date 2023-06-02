package crud;

import bancoDeDados.SQLiteConnectionManager;
import model.Veiculo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "listarVeiculos", urlPatterns = {"/listarVeiculos"})
public class listarVeiculos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = SQLiteConnectionManager.getConnection()) {
            String sql = "SELECT * FROM veiculo";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    List<Veiculo> veiculos = new ArrayList<>();
                    while (rs.next()) {
                        String modelo = rs.getString("modelo");
                        String marca = rs.getString("marca");
                        String cor = rs.getString("cor");
                        String placa = rs.getString("placa");
                        String renavam = rs.getString("renavam");
                        int ano = rs.getInt("ano");
                        double preco = rs.getDouble("preco");

                        Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, renavam, ano, preco);
                        veiculos.add(veiculo);
                    }

                    request.setAttribute("veiculos", veiculos);
                    request.getRequestDispatcher("listarCadastro").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
