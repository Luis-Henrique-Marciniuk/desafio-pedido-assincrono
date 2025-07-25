package com.desafio.domain.model;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Pedido {
    private final UUID id;
    private final String clienteId;
    private final List<String> itens;
    private StatusPedido status;
    private final int prioridade;

    public Pedido(String clienteId, List<String> itens, int prioridade) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.itens = itens;
        this.status = StatusPedido.PENDENTE;
        this.prioridade = prioridade;
    }

    public void marcarProcessado() {
        this.status = StatusPedido.PROCESSADO;
    }

}
