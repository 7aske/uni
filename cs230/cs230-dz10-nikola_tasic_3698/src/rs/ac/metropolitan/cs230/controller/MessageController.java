package rs.ac.metropolitan.cs230.controller;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import model.PorukaModel;

@Named(value = "messageController")
@RequestScoped
public class MessageController {

    @Resource(mappedName = "jms/messageQueue")
    private Topic messageQueue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Inject
    PorukaModel porukaModel;

    public MessageController() {
    }

    private void sendJMSMessageToMessageQueue(String messageData) {
        context.createProducer().send(messageQueue, messageData);
    }

    public String sendMessage() {
        System.out.println(porukaModel.getPoruka());
        sendJMSMessageToMessageQueue(porukaModel.getPoruka());
        return "potvrda";
    }
    

    
   

}
