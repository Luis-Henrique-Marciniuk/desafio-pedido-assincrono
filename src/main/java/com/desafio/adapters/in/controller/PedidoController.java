package com.desafio.adapters.in.controller;

import com.desafio.adapters.in.controller.dto.PedidoRequest;
import com.desafio.adapters.in.controller.dto.PedidoResponse;
import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
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
        log.info("Recebido pedido de criação: clienteId={}, itens={}, prioridade={}",
                request.clienteId(), request.itens(), request.prioridade());

        UUID id = criarPedido.criarPedido(
                request.clienteId(),
                request.itens(),
                request.prioridade()
        );
        log.info("Pedido criado com sucesso: id={}", id);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> consultarPedido(@PathVariable UUID id) {
        log.info("Consulta de pedido pelo id: {}", id);
        Pedido pedido = consultarPedido.consultarPedido(id);
        PedidoResponse response = new PedidoResponse(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getItens(),
                pedido.getStatus(),
                pedido.getPrioridade()
        );
        log.info("Pedido encontrado e retornado: id={}", pedido.getId());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        log.warn("Erro de validação nos dados da requisição: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
