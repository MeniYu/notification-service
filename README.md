#### AMQP is the protocol used to allow communication between many applications through a RabbitMQ server.

RabbitMQ is great for event-driven applications, which will act as the bridge to exchange information between
applications.

Let's say we are building a website application where the core back-end communicates with a notification service
responsible for emailing users.

When a user register, we must send a confirmation email with a confirmation code. The email sending should not block the
response to the client.

So that our Core will be the Publisher. and ouy notification-service will be our Consumer. and the RabbitMQ Server sits
in between the two services.

In the **event-driven architecture**, we use the term *producer* and *consume*r to designate the application that sends
the **message** into the queue and the one that reads the message from the queue.

Configuring the RabbitMQ in Project:

```
@Configuration
public class RabbitMQConfig {
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
```

Start the Application and see the Connection to RabbitMQ Server happens or what.