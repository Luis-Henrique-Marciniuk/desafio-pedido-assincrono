package com.desafio.usecases;

import com.desafio.application.usecases.CriarPedidoUseCaseImpl;
import com.desafio.domain.ports.output.PedidoMessageSender;
import com.desafio.domain.ports.output.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoMessageSender messageSender;

    @InjectMocks
    private CriarPedidoUseCaseImpl useCase;

    @Test
    void deveCriarPedidoSalvarEEnviarMensagem() {
        String clienteId = "cliente1";
        List<String> itens = List.of("produto1", "produto2");
        int prioridade = 1;

        UUID id = useCase.criarPedido(clienteId, itens, prioridade);

        assertThat(id).isNotNull();

        Mockito.verify(pedidoRepository).salvar(Mockito.any());
        Mockito.verify(messageSender).enviarPedido(Mockito.any());
    }
}
