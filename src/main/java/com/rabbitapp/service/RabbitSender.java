package com.rabbitapp.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.cityspringboot.bean.City;



@Service
public class RabbitSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	//@Value("")
	private String exchange = "myExchange";
	
	//@Value("")
	private String routingkey  = "myQueue";	
	
	public void send(City city) {
		rabbitTemplate.convertAndSend(exchange, routingkey, city);
		System.out.println("Send msg = " + city.toString());
	    
	}
}