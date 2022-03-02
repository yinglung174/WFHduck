package com.wfhduck.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{
	
	@GetMapping("/index")
    public String index(){
        return "index";
    }
	
	@GetMapping("/main")
	public String main(){
        return "main";
    }
	
	
}