package com.tapthis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.tapthis.model.Beer;
import com.tapthis.service.BeerService;

@Controller
@RequestMapping("/home")
public class BeerController {

	final BeerService service;
	
	@Autowired
	public BeerController(BeerService service) {
		this.service = service;
	}
	
	@RequestMapping("")
	public View home() {
		return new InternalResourceView("html/home.html");
	}
	
	@RequestMapping("{beerName}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> dynamicLookup(@PathVariable String beerName) {
		Beer beer = this.service.lookupBeer(beerName);
		Map<String, Object> response = new HashMap<>();
		response.put("success", beer);
		return ResponseEntity.ok(response);
	}
}
