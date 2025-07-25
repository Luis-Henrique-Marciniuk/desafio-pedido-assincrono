package com.desafio.adapters.in.controller;

import com.desafio.adapters.in.controller.dto.PedidoRequest;
import com.desafio.adapters.in.controller.dto.PedidoResponse;
import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import jakarta.validation.Valid;
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
    public ResponseEntity<Map<String, Object>> criarPedido(@Valid @RequestBody PedidoRequest request) {
        UUID id = criarPedido.criarPedido(
                request.clienteId(),
                request.itens(),
                request.prioridade()
        );
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> consultarPedido(@PathVariable("id") UUID id) {
        Pedido pedido = consultarPedido.consultarPedido(id);

        PedidoResponse response = new PedidoResponse(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getItens(),
                pedido.getStatus(),
                pedido.getPrioridade()
        );

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
