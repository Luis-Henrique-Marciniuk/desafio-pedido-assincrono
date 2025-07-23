package com.desafio.domain.ports.output;

import com.desafio.domain.model.Pedido;

public interface PedidoMessageSender {
    void enviarPedido(Pedido pedido);
}
