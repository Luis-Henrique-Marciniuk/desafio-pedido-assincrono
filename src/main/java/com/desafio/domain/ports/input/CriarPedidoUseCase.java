package com.desafio.domain.ports.input;

import java.util.List;
import java.util.UUID;

public interface CriarPedidoUseCase {
    UUID criarPedido(String clienteId, List<String> itens, int prioridade);
}
