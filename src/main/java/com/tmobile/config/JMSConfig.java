package com.tmobile.config;
//
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JMSConfig {

    @Value("${tmobile.jmsUrl}")
    private String DEFAULT_BROKER_URL;// = "tcp://localhost:61616";

    @Value("${tmobile.topicName}")
    private String TMOBILE_TOPIC;// = "tmobile-topic";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
//        connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(TMOBILE_TOPIC);
        template.setPubSubDomain(true);
        return template;
    }

}