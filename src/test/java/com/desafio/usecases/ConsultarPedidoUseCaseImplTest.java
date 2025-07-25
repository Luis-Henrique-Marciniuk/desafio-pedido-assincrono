package com.desafio.usecases;

import com.desafio.application.usecases.ConsultarPedidoUseCaseImpl;
import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.output.PedidoRepository;
import com.desafio.exceptions.PedidoNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConsultarPedidoUseCaseImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ConsultarPedidoUseCaseImpl useCase;

    @Test
    void deveRetornarPedidoQuandoExistir() {
        UUID id = UUID.randomUUID();
        Pedido pedido = new Pedido("cliente1", List.of("item1"), 1);

        Mockito.when(pedidoRepository.buscarPorId(id)).thenReturn(Optional.of(pedido));

        Pedido resultado = useCase.consultarPedido(id);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getClienteId()).isEqualTo("cliente1");
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoExistir() {
        UUID id = UUID.randomUUID();

        Mockito.when(pedidoRepository.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () -> useCase.consultarPedido(id));
    }
}
