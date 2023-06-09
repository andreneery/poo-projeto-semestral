package bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuarios;

public class UsuariosDAO {
    private static final String INSERT_USUARIO_SQL = "INSERT INTO usuarios (nomeCompleto, email, username, senha) VALUES (?, ?, ?, ?)";

    public void salvarUsuario(Usuarios usuario) throws Exception {
        try (
            Connection connection = SQLiteConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, usuario.getNomeCompleto());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getUsername());
            preparedStatement.setString(4, usuario.getSenha());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    usuario.setId(id);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Usuarios obterUsuario(String username) throws Exception {
        Usuarios usuario = null;
        String query = "SELECT * FROM usuarios WHERE username = ?";

        try (Connection connection = SQLiteConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nomeCompleto = resultSet.getString("nomeCompleto");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");

                    usuario = new Usuarios(nomeCompleto, email, username, senha);
                    usuario.setId(id);
                }
            }
        }

        return usuario;
    }
}
