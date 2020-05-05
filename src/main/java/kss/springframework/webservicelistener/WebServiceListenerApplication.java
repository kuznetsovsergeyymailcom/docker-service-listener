package kss.springframework.webservicelistener;

import kss.springframework.webservicelistener.listener.RabbitMqListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScans({"kss.springframework.webservicelistener.model", "kss.springframework.webservicelistener"})
@SpringBootApplication
@RequiredArgsConstructor
public class WebServiceListenerApplication {

    public final static String SFG_MESSAGE_LISTENER_QUEUE = "sfg-message-listener-queue";
    public final static String SFG_MESSAGE_WEB_QUEUE = "sfg-message-web-queue";

    @Bean
    Queue queue() {
        return new Queue(SFG_MESSAGE_LISTENER_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_LISTENER_QUEUE);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SFG_MESSAGE_LISTENER_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    //
    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMqListener rabbitMqListener) {
        return new MessageListenerAdapter(rabbitMqListener, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServiceListenerApplication.class, args);
    }

}
