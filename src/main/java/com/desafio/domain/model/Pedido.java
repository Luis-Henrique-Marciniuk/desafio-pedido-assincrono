package com.desafio.domain.model;

import java.util.List;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private String clienteId;
    private List<String> itens;
    private StatusPedido status;
    private int prioridade;

    public Pedido(String clienteId, List<String> itens, int prioridade) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.itens = itens;
        this.status = StatusPedido.PENDENTE;
        this.prioridade = prioridade;
    }

    public UUID getId() { return id; }
    public String getClienteId() { return clienteId; }
    public List<String> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public int getPrioridade() { return prioridade; }

    public void marcarProcessado() {
        this.status = StatusPedido.PROCESSADO;
    }
}
