package com.desafio.exceptions;

import java.util.UUID;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(UUID id) {
        super("Pedido não encontrado com id: " + id);
    }
}
