package com.desafio.adapters.in.controller;

import com.desafio.application.usecases.CriarPedidoUseCaseImpl;
import com.desafio.domain.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCaseImpl pedidoService;

    public PedidoController(CriarPedidoUseCaseImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> criarPedido(@RequestBody Map<String, Object> body) {
        String clienteId = (String) body.get("clienteId");
        List<String> itens = (List<String>) body.get("itens");
        int prioridade = (int) body.getOrDefault("prioridade", 1);

        UUID id = pedidoService.criarPedido(clienteId, itens, prioridade);
        return ResponseEntity.ok(Map.of("id", id));
    }
}
