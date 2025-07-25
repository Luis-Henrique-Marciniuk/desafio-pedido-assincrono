package com.desafio.usecases;

import com.desafio.adapters.out.repository.InMemoryPedidoRepository;
import com.desafio.domain.model.Pedido;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryPedidoRepositoryTest {

    private final InMemoryPedidoRepository repository = new InMemoryPedidoRepository();

    @Test
    void deveSalvarEBuscarPedido() {
        Pedido pedido = new Pedido("cliente1", List.of("item1", "item2"), 1);
        repository.salvar(pedido);

        Optional<Pedido> encontrado = repository.buscarPorId(pedido.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getClienteId()).isEqualTo("cliente1");
    }

    @Test
    void deveAtualizarStatusParaProcessado() {
        Pedido pedido = new Pedido("cliente1", List.of("item1"), 1);
        repository.salvar(pedido);

        repository.atualizarStatus(pedido.getId(), true);

        Optional<Pedido> encontrado = repository.buscarPorId(pedido.getId());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getStatus()).isEqualTo(com.desafio.domain.model.StatusPedido.PROCESSADO);
    }
}
