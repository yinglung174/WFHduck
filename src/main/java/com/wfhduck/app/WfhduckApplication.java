package com.wfhduck.app;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wfhduck.app","com.wfhduck.app.model","com.wfhduck.app.controller","com.wfhduck.app.service","com.wfhduck.app.repository"})
@EntityScan({"com.wfhduck.app","com.wfhduck.app.model","com.wfhduck.app.controller","com.wfhduck.app.service","com.wfhduck.app.repository"})
public class WfhduckApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WfhduckApplication.class, args);
	}

}
