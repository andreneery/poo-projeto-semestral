package model;

public class Usuarios {
    private int id;
    private String nomeCompleto;
    private String email;
    private String username;
    private String senha;
    
    public static String getCreateStatement() {
        return "CREATE TABLE IF NOT EXISTS usuarios ("
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "    nomeCompleto VARCHAR(50) NOT NULL,"
                + "    email VARCHAR(50) NOT NULL,"
                + "    username VARCHAR(50) NOT NULL UNIQUE,"
                + "    senha VARCHAR(50) NOT NULL"
                + ");";
    }


    public Usuarios(String nomeCompleto, String email, String username, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.username = username;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
