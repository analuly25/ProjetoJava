package br.agencia.model;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private int clienteId;
    private int pacoteId;
    private LocalDate dataPedido;

    public Pedido() {}

    public Pedido(int clienteId, int pacoteId, LocalDate dataPedido) {
        this.clienteId = clienteId;
        this.pacoteId = pacoteId;
        this.dataPedido = dataPedido;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getPacoteId() { return pacoteId; }
    public void setPacoteId(int pacoteId) { this.pacoteId = pacoteId; }

    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
}

