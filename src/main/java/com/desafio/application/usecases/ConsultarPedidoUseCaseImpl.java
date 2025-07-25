package com.desafio.application.usecases;

import com.desafio.exceptions.PedidoNotFoundException;
import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import com.desafio.domain.ports.output.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsultarPedidoUseCaseImpl implements ConsultarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public ConsultarPedidoUseCaseImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido consultarPedido(UUID id) {
        return pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }
}
