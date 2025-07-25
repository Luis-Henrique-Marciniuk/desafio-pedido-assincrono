package com.desafio.adapters.in.controller;

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
    public ResponseEntity<Map<String, Object>> criarPedido(@Valid @RequestBody Pedido request) {
        UUID id = criarPedido.criarPedido(
                String.valueOf(request.getClienteId()),
                Collections.emptyList(),
                request.getPrioridade()
        );
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> consultarPedido(@PathVariable("id") UUID id) {
        Pedido pedido = consultarPedido.consultarPedido(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
