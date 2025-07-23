package com.desafio.adapters.out.repository;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.model.StatusPedido;
import com.desafio.domain.ports.output.PedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPedidoRepository implements PedidoRepository {

    private final Map<UUID, Pedido> pedidos = new ConcurrentHashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public void atualizarStatus(UUID id, boolean processado) {
        Pedido pedido = pedidos.get(id);
        if (pedido != null && processado) {
            pedido.marcarProcessado();
        }
    }
}
