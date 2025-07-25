package com.desafio.adapters.in.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PedidoRequest(
        @NotBlank(message = "clienteId não pode ser vazio") String clienteId,
        @NotEmpty(message = "Lista de itens não pode ser vazia") List<String> itens,
        @Min(value = 1, message = "Prioridade deve ser no mínimo 1") int prioridade
) {}
