package com.desafio.usecases;

import com.desafio.adapters.in.controller.PedidoController;
import com.desafio.adapters.in.controller.dto.PedidoRequest;
import com.desafio.domain.model.Pedido;
import com.desafio.domain.model.StatusPedido;
import com.desafio.domain.ports.input.CriarPedidoUseCase;
import com.desafio.domain.ports.input.ConsultarPedidoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private ConsultarPedidoUseCase consultarPedidoUseCase;

    @InjectMocks
    private PedidoController pedidoController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void deveCriarPedidoComSucesso() throws Exception {
        PedidoRequest request = new PedidoRequest("cliente1", List.of("produto1"), 1);
        UUID id = UUID.randomUUID();

        when(criarPedidoUseCase.criarPedido(anyString(), anyList(), anyInt())).thenReturn(id);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));

        verify(criarPedidoUseCase, times(1)).criarPedido(anyString(), anyList(), anyInt());
    }

    @Test
    void deveConsultarPedidoComSucesso() throws Exception {
        UUID id = UUID.randomUUID();
        Pedido pedido = new Pedido("cliente1", List.of("produto1", "produto2"), 1);

        when(consultarPedidoUseCase.consultarPedido(id)).thenReturn(pedido);

        mockMvc.perform(get("/pedidos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pedido.getId().toString()))
                .andExpect(jsonPath("$.clienteId").value("cliente1"))
                .andExpect(jsonPath("$.status").value("PENDENTE"))
                .andExpect(jsonPath("$.prioridade").value(1));

        verify(consultarPedidoUseCase, times(1)).consultarPedido(id);
    }

    @Test
    void deveRetornar404QuandoPedidoNaoExistir() throws Exception {
        UUID id = UUID.randomUUID();

        when(consultarPedidoUseCase.consultarPedido(id)).thenThrow(new com.desafio.exceptions.PedidoNotFoundException(id));

        mockMvc.perform(get("/pedidos/" + id))
                .andExpect(status().isNotFound());

        verify(consultarPedidoUseCase, times(1)).consultarPedido(id);
    }
}
