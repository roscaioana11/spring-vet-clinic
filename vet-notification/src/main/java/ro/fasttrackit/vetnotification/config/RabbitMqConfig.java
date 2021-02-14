package ro.fasttrackit.vetnotification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue consultationQueue(){
        return new Queue("consultations2");
    }

    @Bean
    public Queue diagnosisQueue(){
        return new Queue("diagnosis");
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("vetclinic");
    }

    @Bean
    public Binding binding1(DirectExchange direct,
                             Queue consultationQueue) {
        return BindingBuilder.bind(consultationQueue)
                .to(direct)
                .with("consultation");
    }

    @Bean
    public Binding binding2(DirectExchange direct,
                             Queue diagnosisQueue) {
        return BindingBuilder.bind(diagnosisQueue)
                .to(direct)
                .with("diagnosis");
    }
}
