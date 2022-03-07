package com.wfhduck.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wfhduck.app.controller.HomeController;

@SpringBootTest
class WfhduckApplicationTests {

	@Autowired
	private HomeController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
