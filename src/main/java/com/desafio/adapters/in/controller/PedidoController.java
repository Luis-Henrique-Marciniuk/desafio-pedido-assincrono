package com.desafio.adapters.in.controller;

import com.desafio.adapters.in.controller.dto.PedidoRequestDTO;
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
    public ResponseEntity<Map<String, Object>> criarPedido(@RequestBody PedidoRequestDTO request) {
        // Use os campos do DTO normalmente
        UUID id = criarPedido.criarPedido(
                String.valueOf(request.clienteId),
                // Converta produtos para o formato esperado pelo seu caso de uso
                Collections.emptyList(), // ajuste conforme sua lógica
                request.prioridade
        );
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> consultarPedido(@PathVariable("id") UUID id) {
        Pedido pedido = consultarPedido.consultarPedido(id);
        return ResponseEntity.ok(pedido);
    }
}
