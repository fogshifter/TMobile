package com.tmobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public MessageService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage() {
        jmsTemplate.send(session -> session.createTextMessage("updated"));
    }


}
