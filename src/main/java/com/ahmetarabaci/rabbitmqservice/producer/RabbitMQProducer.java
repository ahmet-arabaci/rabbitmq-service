package com.ahmetarabaci.rabbitmqservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ahmetarabaci.rabbitmqservice.model.UserDto;

@Service
public class RabbitMQProducer {
	
	private static final String EXCHANGE = "exchange1";
	private static final String ROUTING_KEY_FOR_STR_QUEUE = "routeString_queueString";
	private static final String ROUTING_KEY_FOR_JSON_QUEUE = "routeJSON_queueJSON";
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	private final AmqpTemplate amqpTemplate;
	
	@Autowired
	public RabbitMQProducer(@Qualifier("customRabbitTemplate") AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}
	
	public String sendStringMessage(String message) {
		try {
			LOGGER.info(String.format("sendStringMessage | '%s' string message has been sent.", message));
			amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_FOR_STR_QUEUE, message);
			return "OK";
		} catch (Exception e) {
			LOGGER.error(String.format("sendStringMessage | Exception occurred "
					+ "while sending '%s' string message!", message), e);
			return "NOK";
		}
	}
	
	public String sendJSONMessage(UserDto message) {
		try {
			LOGGER.info(String.format("sendJSONMessage | '%s' JSON message has been sent.", message));
			amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_FOR_JSON_QUEUE, message);
			return "OK";
		} catch (Exception e) {
			LOGGER.error(String.format("sendJSONMessage | Exception occurred "
					+ "while sending '%s' JSON message!", message), e);
			return "NOK";
		}
	}
	
}


