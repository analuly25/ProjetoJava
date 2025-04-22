package br.agencia.model;

public class ServicoAdicional  {
    private int id;
    private int pedidoId;
    private String descricao;
    private double preco;

    public ServicoAdicional() {}

    public ServicoAdicional(int pedidoId, String descricao, double preco) {
        this.pedidoId = pedidoId;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}