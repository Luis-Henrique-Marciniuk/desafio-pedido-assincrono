package com.desafio.adapters.out.kafka;

import com.desafio.domain.model.Pedido;
import com.desafio.domain.ports.output.PedidoMessageSender;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaProducer implements PedidoMessageSender {

    private final KafkaTemplate<String, Pedido> kafkaTemplate;

    public PedidoKafkaProducer(KafkaTemplate<String, Pedido> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarPedido(Pedido pedido) {
        kafkaTemplate.send("pedidos", pedido.getId().toString(), pedido);
    }
}
