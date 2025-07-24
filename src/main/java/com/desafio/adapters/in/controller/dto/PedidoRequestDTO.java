package com.desafio.adapters.in.controller.dto;

import java.util.List;

public class PedidoRequestDTO {
    public Integer clienteId;
    public List<ItemDTO> produtos;
    public Double valorTotal;
    public Integer prioridade = 1; // opcional

    public static class ItemDTO {
        public Integer produtoId;
        public Integer quantidade;
    }
}