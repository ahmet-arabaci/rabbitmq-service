package com.ahmetarabaci.rabbitmqservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.ahmetarabaci.rabbitmqservice.model.UserDto;

@Service
public class RabbitMQConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
	private static final String STR_QUEUE = "queueString";
	private static final String JSON_QUEUE = "queueJSON";
	
	@RabbitListener(queues = {STR_QUEUE})
	public void receiveStringMessage(String message) {
		LOGGER.info(String.format("receiveStringMessage | '%s' string message has been received!", message));
	}
	
	@RabbitListener(queues = {JSON_QUEUE})
	public void receiveJSONMessage(UserDto message) {
		LOGGER.info(String.format("receiveJSONMessage | '%s' JSON message has been received!", message));
	}
	
}

