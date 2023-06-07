package bancoDeDados;

import java.sql.*;

public class SQLiteConnectionManager {
    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:concessionaria.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(URL);
    }
    
    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS veiculo (\n"
                + "    modelo VARCHAR(50) NOT NULL,\n"
                + "    marca VARCHAR(50) NOT NULL,\n"
                + "    cor VARCHAR(50) NOT NULL,\n"
                + "    placa VARCHAR(50) NOT NULL,\n"
                + "    renavam VARCHAR(50) NOT NULL,\n"
                + "    ano INT NOT NULL,\n"
                + "    preco DOUBLE NOT NULL\n"
                + ");";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Tabela veiculo criada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}