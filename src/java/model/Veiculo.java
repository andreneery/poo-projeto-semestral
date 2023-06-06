package model;

public class Veiculo {
    private String modelo;
    private String marca;
    private String cor;
    private String placa;
    private String renavam;
    private int ano;
    private double preco;

    public Veiculo(String modelo, String marca, String cor, String placa, String renavam, int ano, double preco) {
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.placa = placa;
        this.renavam = renavam;
        this.ano = ano;
        this.preco = preco;
    }

    // Getters e Setters

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
