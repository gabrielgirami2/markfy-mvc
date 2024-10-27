package com.markfy.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue cadastroProdutoQueue() {
        return new Queue("markfy-cadastro-produto", true);
    }

    @Bean
    public Queue compraQueue() {
        return new Queue("markfy-compra", true);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


    @Bean
    public ApplicationRunner init(RabbitAdmin rabbitAdmin) {
        return args -> {
            rabbitAdmin.declareQueue(cadastroProdutoQueue());
            rabbitAdmin.declareQueue(compraQueue());
        };
    }
}