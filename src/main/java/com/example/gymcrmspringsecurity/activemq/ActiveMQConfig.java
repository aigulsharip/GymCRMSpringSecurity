package com.example.gymcrmspringsecurity.activemq;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {
    @Bean
    public JmsListenerContainerFactory warehouseFactory(ConnectionFactory factory, DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory containerFactory =  new DefaultJmsListenerContainerFactory();
        configurer.configure(containerFactory, factory);

        return containerFactory;
    }

    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("artemis","artemis","tcp://localhost:61616");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

}
