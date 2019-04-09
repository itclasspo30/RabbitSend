package com.rabbitapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cityspringboot.bean.City;
import com.rabbitapp.service.RabbitSender;


@Controller
public class RabbitWebController {

	@Autowired
	RabbitSender rabbitSender;
	
	@RequestMapping(value = { "/addCity" }, method = RequestMethod.GET)
    public String showAddCityPage(Model model) {
 
        City city = new City();
        model.addAttribute("city", city);
 
        return "addCity";
    }
    
    
    
    @RequestMapping(value = { "/addCity" }, method = RequestMethod.POST)
    public String saveCity(Model model, @ModelAttribute("city") City city) {
 
    	String name = city.getName();
        int population = city.getPopulation();
 
        if (name != null && name.length() > 0 && population >0) {
        	rabbitSender.send(city);
        }
        model.addAttribute("text", "Message sent to the RabbitMQ Successfully");
        return "addCity";
    }
}
