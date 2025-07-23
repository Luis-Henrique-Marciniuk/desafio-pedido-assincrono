package com.desafio.adapters.in.controller;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedido;
    private final ConsultarPedidoUseCase consultarPedido;

    public PedidoController(CriarPedidoUseCase criarPedido, ConsultarPedidoUseCase consultarPedido) {
        this.criarPedido = criarPedido;
        this.consultarPedido = consultarPedido;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> criarPedido(@RequestBody Map<String, Object> request) {
        String clienteId = (String) request.get("clienteId");
        List<String> itens = (List<String>) request.get("itens");
        int prioridade = (int) request.getOrDefault("prioridade", 1);

        UUID id = criarPedido.criarPedido(clienteId, itens, prioridade);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> consultarPedido(@PathVariable UUID id) {
        Pedido pedido = consultarPedido.consultarPedido(id);
        return ResponseEntity.ok(pedido);
    }
}
