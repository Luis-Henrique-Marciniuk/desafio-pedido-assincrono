package com.desafio.application.usecases;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import com.desafio.domain.ports.output.PedidoRepository;
import com.desafio.exceptions.PedidoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ConsultarPedidoUseCaseImpl implements ConsultarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public ConsultarPedidoUseCaseImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido consultarPedido(UUID id) {
        log.info("Consultando pedido pelo id: {}", id);
        Pedido pedido = pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> {
                    log.warn("Pedido não encontrado: {}", id);
                    return new PedidoNotFoundException(id);
                });
        log.info("Pedido encontrado: {}", pedido);
        return pedido;
    }
}
