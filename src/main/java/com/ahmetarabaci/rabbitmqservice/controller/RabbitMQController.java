package com.ahmetarabaci.rabbitmqservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ahmetarabaci.rabbitmqservice.model.UserDto;
import com.ahmetarabaci.rabbitmqservice.producer.RabbitMQProducer;

@RestController
public class RabbitMQController {

	private RabbitMQProducer service;
	
	@Autowired
	public RabbitMQController(RabbitMQProducer service) {
		this.service = service;
	}
	
	@GetMapping("/sendstringmessage/{message}")
	private String sendStringMessage(@PathVariable("message") String message) {
		return service.sendStringMessage(message);
	}
	
	@PostMapping("/sendjsonmessage")
	private String sendJSONMessage(@RequestBody UserDto message) {
		return service.sendJSONMessage(message);
	}
}

