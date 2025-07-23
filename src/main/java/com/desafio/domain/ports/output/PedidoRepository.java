package com.desafio.domain.ports.output;

import com.desafio.domain.model.Pedido;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository {
    void salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);
    void atualizarStatus(UUID id, boolean processado);
}
