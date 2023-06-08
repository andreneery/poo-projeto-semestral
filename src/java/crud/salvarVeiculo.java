package crud;

import bancoDeDados.SQLiteConnectionManager;
import bancoDeDados.VeiculoDAO;
import java.sql.Connection;
import java.sql.Statement;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Veiculo;

@WebServlet(name = "salvarVeiculo", urlPatterns = {"/salvarVeiculo"})
public class salvarVeiculo extends HttpServlet {

    private VeiculoDAO veiculoDAO; // Objeto para acessar o banco de dados

    @Override
    public void init() throws ServletException {
        super.init();
        veiculoDAO = new VeiculoDAO();
    }

    @Override
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

        try {
            Connection c = (Connection) SQLiteConnectionManager.getConnection();
            Statement s = c.createStatement();
            s.execute(Veiculo.getCreateStatement());

            VeiculoDAO vDAO = new VeiculoDAO();
            vDAO.salvarVeiculo(new Veiculo(modelo, marca, cor, placa, renavam, 0, 0));

        } catch (Exception e) {

        }

        System.out.println(String.format("-->%s\n-->%s\n-->%s\n-->%s\n-->%s", modelo, marca, cor, placa, renavam));

        // Verificar se os campos obrigatórios estão preenchidos
        if (modelo == null || marca == null || cor == null || placa == null || renavam == null || anoString == null || precoString == null
                || modelo.isEmpty() || marca.isEmpty() || cor.isEmpty() || placa.isEmpty() || renavam.isEmpty() || anoString.isEmpty() || precoString.isEmpty()) {
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

        // Criar um objeto Veiculo com os dados informados
        Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, renavam, ano, preco);
        veiculo.setModelo(modelo);
        veiculo.setMarca(marca);
        veiculo.setCor(cor);
        veiculo.setPlaca(placa);
        veiculo.setRenavam(renavam);
        veiculo.setAno(ano);
        veiculo.setPreco(preco);

        try {
            // Salvar o veículo no banco de dados
            veiculoDAO.salvarVeiculo(veiculo);
        } catch (Exception ex) {
            Logger.getLogger(salvarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Redirecionar para a página de cadastro com uma mensagem de sucesso
        String redirectUrl = "cadastroVeiculo.jsp?success";
        response.sendRedirect(redirectUrl);
    }
}
