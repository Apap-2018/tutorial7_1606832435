package com.apap.tutorial7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;

@RestController
@RequestMapping("/airport")
public class AirportController {
	
	@GetMapping()
	public Object listOfAirport(@RequestParam("term") String term) throws Exception {
		String path = Setting.airportUrl + "&term=" + term + "&country=ID";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Object listAirport = restTemplate.getForObject(path, Object.class);
		
		return listAirport;
	}
}
