package com.desafio.adapters.in.kafka;

import com.desafio.domain.ports.output.PedidoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PedidoKafkaConsumer {

    private final PedidoRepository repository;

    public PedidoKafkaConsumer(PedidoRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "pedidos", groupId = "processador-pedidos")
    public void consumir(ConsumerRecord<String, String> record) {
        try {
            Thread.sleep(2000);
            UUID pedidoId = UUID.fromString(record.key());
            repository.atualizarStatus(pedidoId, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
