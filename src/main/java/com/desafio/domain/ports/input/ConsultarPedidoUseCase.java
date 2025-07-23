package com.desafio.domain.ports.input;

import com.desafio.domain.model.Pedido;
import java.util.UUID;

public interface ConsultarPedidoUseCase {
    Pedido consultarPedido(UUID id);
}
