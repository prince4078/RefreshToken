package com.sparx.authusingrefreshtoken.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    
	@GetMapping("/test")
	public String test() {
		return " message from the test method";
	}
	

	@GetMapping("/public")
	public String publicUser() {
		return " message from the public  method";
	}

	@GetMapping("/admin")
	public String adminUser() {
		return "<h1> message from the admin  method</h1>";
	}
}
