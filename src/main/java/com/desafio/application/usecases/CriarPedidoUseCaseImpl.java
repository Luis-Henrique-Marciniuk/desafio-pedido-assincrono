package com.desafio.application.usecases;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.output.PedidoRepository;
import com.desafio.domain.ports.output.PedidoMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
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
        log.info("Criando pedido para clienteId={} com {} itens", clienteId, itens.size());
        Pedido pedido = new Pedido(clienteId, itens, prioridade);
        repository.salvar(pedido);
        log.info("Pedido salvo no repositório: id={}", pedido.getId());
        messageSender.enviarPedido(pedido);
        log.info("Pedido enviado para fila Kafka: id={}", pedido.getId());
        return pedido.getId();
    }
}
