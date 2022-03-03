package com.wfhduck.app.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wfhduck.app.service.CustomerService;

@Controller
public class HomeController{
	
	@GetMapping("/index")
    public String index(){
        return "index";
    }
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/loginProcess")
	public String loginProcess(HttpServletRequest request, Model model) throws SQLException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		model.addAttribute("username",username);
		model.addAttribute("password",password);
		String fullName = customerService.findCustomerFullName(username, password);
		model.addAttribute("fullName",fullName);
		if (fullName == null) {
			return "loginFail";
		}
		model.addAttribute("fullName",fullName);
        return "interface";
    }
	
	@GetMapping("/loginFail")
	public String loginFail(HttpServletRequest request, Model model){
        return "loginFail";
    }
	
	@GetMapping("/registration")
    public String registration(){
        return "registration";
    }
	
	@GetMapping("/updateCustomerProfile")
    public String updateCustomerProfile(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		model.addAttribute("username",username);
        return "updateCustomerProfile";
    }
	
	@GetMapping("/reportProblem")
    public String reportProblem(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		model.addAttribute("username",username);
        return "reportProblem";
    }
	
	
}