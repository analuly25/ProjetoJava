package br.agencia.model;

public class PacoteViagem {
    private int id;
    private String nome;
    private String destino;
    private String descricao;
    private int duracaoDias;
    private double preco;
    private String tipo;

    public PacoteViagem(String nome, String destino, String descricao, int duracaoDias, double preco, String tipo) {
        this.nome = nome;
        this.destino = destino;
        this.descricao = descricao;
        this.duracaoDias = duracaoDias;
        this.preco = preco;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDestino() { return destino; }
    public String getDescricao() { return descricao; }
    public int getDuracaoDias() { return duracaoDias; }
    public double getPreco() { return preco; }
    public String getTipo() { return tipo; }
}

