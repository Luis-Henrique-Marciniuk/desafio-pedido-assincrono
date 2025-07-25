package com.desafio.adapters.in.controller.dto;

import com.desafio.domain.model.StatusPedido;

import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        String clienteId,
        List<String> itens,
        StatusPedido status,
        int prioridade
) {}
