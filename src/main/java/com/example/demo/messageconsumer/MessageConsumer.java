package com.example.demo.messageconsumer;
import com.example.demo.bookmodel.Books;
import com.example.demo.rabbitconfiguration.MessageConfiguration;
import com.example.demo.usermodel.Users;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//to consume the message from the queue
@Component
public class MessageConsumer{
        @RabbitListener(queues = MessageConfiguration.QUEUE1)
        public void consumeMessageFromQueueBook (Books book)
        {
            System.out.println("Message received from the book queue\n " + book);
        }
    @RabbitListener(queues = MessageConfiguration.QUEUE2)
    public void consumeMessageFromQueueUser(Users user)
    {
        System.out.println("Message received from the user queue\n "+user);
    }
}
