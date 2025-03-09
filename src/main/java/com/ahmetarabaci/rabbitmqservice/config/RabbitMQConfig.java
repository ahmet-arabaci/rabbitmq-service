package com.ahmetarabaci.rabbitmqservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private static final String EXCHANGE = "exchange1";
	private static final String STR_QUEUE = "queueString";
	private static final String JSON_QUEUE = "queueJSON";
	private static final String ROUTING_KEY_FOR_STR_QUEUE = "routeString_queueString";
	private static final String ROUTING_KEY_FOR_JSON_QUEUE = "routeJSON_queueJSON";

	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange(EXCHANGE);
	}

	// Queue instance for string messages.
	@Bean
	public Queue getStringQueue() {
		return new Queue(STR_QUEUE);
	}

	// Queue instance for JSON messages.
	@Bean
	public Queue getJSONQueue() {
		return new Queue(JSON_QUEUE);
	}

	// Binding (Routing key) instance for string messages.
	@Bean
	public Binding getBindingForStringQueue() {
		return BindingBuilder.bind(getStringQueue()).to(getExchange()).with(ROUTING_KEY_FOR_STR_QUEUE);
	}

	// Binding (Routing key) instance for JSON messages.
	@Bean
	public Binding getBindingForJSONQueue() {
		return BindingBuilder.bind(getJSONQueue()).to(getExchange()).with(ROUTING_KEY_FOR_JSON_QUEUE);
	}

	// RabbitMQ connection factory.
	@Bean
	public ConnectionFactory getConnFactory() {
		CachingConnectionFactory connFactory = new CachingConnectionFactory();
		connFactory.setHost("localhost");
		connFactory.setPort(5672);
		connFactory.setUsername("guest");
		connFactory.setPassword("guest");
		return connFactory;
	}

	@Bean
	public MessageConverter getJSONMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	@Qualifier("customRabbitTemplate")
	public AmqpTemplate getCustomAmpTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(getConnFactory());
		rabbitTemplate.setMessageConverter(getJSONMessageConverter());
		return rabbitTemplate;
	}
}
