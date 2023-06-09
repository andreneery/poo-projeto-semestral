package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

public class VeiculoDAO {
    private static final String INSERT_VEICULO_SQL = "INSERT INTO veiculo (modelo, marca, cor, placa, renavam, ano, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_VEICULOS_SQL = "SELECT * FROM veiculo";
    private static final String DELETE_VEICULO_SQL = "DELETE FROM veiculo WHERE placa = ?";

    public void salvarVeiculo(Veiculo veiculo) throws Exception {
        try (Connection connection = SQLiteConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEICULO_SQL)) {
            preparedStatement.setString(1, veiculo.getModelo());
            preparedStatement.setString(2, veiculo.getMarca());
            preparedStatement.setString(3, veiculo.getCor());
            preparedStatement.setString(4, veiculo.getPlaca());
            preparedStatement.setString(5, veiculo.getRenavam());
            preparedStatement.setInt(6, veiculo.getAno());
            preparedStatement.setDouble(7, veiculo.getPreco());

            preparedStatement.executeUpdate();

            System.out.println("Veículo salvo no banco de dados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> listarVeiculos() throws Exception {
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection connection = SQLiteConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEICULOS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
                String marca = resultSet.getString("marca");
                String cor = resultSet.getString("cor");
                String placa = resultSet.getString("placa");
                String renavam = resultSet.getString("renavam");
                int ano = resultSet.getInt("ano");
                double preco = resultSet.getDouble("preco");

                Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, renavam, ano, preco);
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
    
    public void delete(String placa) throws Exception {
        try (Connection connection = SQLiteConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEICULO_SQL)) {

            preparedStatement.setString(1, placa);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
