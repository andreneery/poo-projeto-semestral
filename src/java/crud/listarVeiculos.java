package crud;

import bancoDeDados.SQLiteConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "listarVeiculos", urlPatterns = {"/listarVeiculos"})
public class listarVeiculos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = SQLiteConnectionManager.getConnection()) {
            String sql = "SELECT * FROM veiculo";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    JSONArray veiculosJson = new JSONArray();
                    while (rs.next()) {
                        String modelo = rs.getString("modelo");
                        String marca = rs.getString("marca");
                        String cor = rs.getString("cor");
                        String placa = rs.getString("placa");
                        String renavam = rs.getString("renavam");
                        int ano = rs.getInt("ano");
                        double preco = rs.getDouble("preco");

                        JSONObject veiculoJson = new JSONObject();
                        veiculoJson.put("modelo", modelo);
                        veiculoJson.put("marca", marca);
                        veiculoJson.put("cor", cor);
                        veiculoJson.put("placa", placa);
                        veiculoJson.put("renavam", renavam);
                        veiculoJson.put("ano", ano);
                        veiculoJson.put("preco", preco);

                        veiculosJson.put(veiculoJson);
                    }

                    request.setAttribute("veiculos", veiculosJson.toString());
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