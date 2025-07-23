package com.desafio.application.usecases;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.output.PedidoRepository;
import com.desafio.domain.ports.output.PedidoMessageSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoRepository repository;
    private final PedidoMessageSender messageSender;

    public CriarPedidoUseCaseImpl(PedidoRepository repository, PedidoMessageSender messageSender) {
        this.repository = repository;
        this.messageSender = messageSender;
    }

    @Override
    public UUID criarPedido(String clienteId, List<String> itens, int prioridade) {
        Pedido pedido = new Pedido(clienteId, itens, prioridade);
        repository.salvar(pedido);
        messageSender.enviarPedido(pedido);
        return pedido.getId();
    }
}
