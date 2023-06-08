package crud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "salvarVeiculo", urlPatterns = {"/salvarVeiculo"})
public class salvarVeiculo extends HttpServlet {
    
    private List<JSONObject> listaVeiculos; // Lista para armazenar os veículos cadastrados
    
    @Override
    public void init() throws ServletException {
        super.init();
        listaVeiculos = new ArrayList<>();
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

        // Criar um objeto JSON com os dados do veículo
        JSONObject veiculoJson = new JSONObject();
        veiculoJson.put("modelo", modelo);
        veiculoJson.put("marca", marca);
        veiculoJson.put("cor", cor);
        veiculoJson.put("placa", placa);
        veiculoJson.put("renavam", renavam);
        veiculoJson.put("ano", ano);
        veiculoJson.put("preco", preco);
        
        // Adicionar o objeto JSON à lista de veículos
        listaVeiculos.add(veiculoJson);
        
        // Armazenar a lista de veículos no atributo de solicitação
        request.setAttribute("veiculos", listaVeiculos);
        
        String sucessCreate = "Dados armazenados com sucesso.";
        String encodedMessage = java.net.URLEncoder.encode(sucessCreate, "UTF-8");
        response.sendRedirect(request.getContextPath() + "/cadastroVeiculo.jsp?success=" + encodedMessage);
    }
}
