package com.desafio.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Pedido {
    private UUID id;
    @NotBlank(message = "clienteId não pode ser vazio")
    private String clienteId;

    @NotEmpty(message = "Lista de itens não pode ser vazia")
    private List<String> itens;
    private StatusPedido status;

    @Min(value = 1, message = "Prioridade deve ser no mínimo 1")
    private int prioridade;

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
