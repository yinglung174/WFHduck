package com.wfhduck.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController{
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to wfhduck";
	}
	
	
}