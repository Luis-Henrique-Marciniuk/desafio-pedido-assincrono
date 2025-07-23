package com.desafio.application.usecases;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import com.desafio.domain.ports.output.PedidoRepository;

import java.util.UUID;

public class ConsultarPedidoUseCaseImpl implements ConsultarPedidoUseCase {

    private final PedidoRepository repository;

    public ConsultarPedidoUseCaseImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido consultarPedido(UUID id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}
