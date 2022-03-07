package com.wfhduck.app.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getLogin() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/index", String.class);
        assertThat(response.getBody()).contains("Login");
    }
    
    @Test
    public void getInterface() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/loginProcess?username=anthony&password=anthony", String.class);
        assertThat(response.getBody()).contains("Anthony");
    }
    
    @Test
    public void getLoginFail() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/loginFail", String.class);
        assertThat(response.getBody()).contains("Please Retry!");
    }
    
    @Test
    public void getRegistration() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/registration", String.class);
        assertThat(response.getBody()).contains("Create an account?");
    }
    
    @Test
    public void getUpdateCustomerProfile() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/updateCustomerProfile?username=anthony", String.class);
        assertThat(response.getBody()).contains("anthony");
    }
    
    @Test
    public void getReportProblem() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/reportProblem?username=anthony", String.class);
        assertThat(response.getBody()).contains("Technical Request");
    }
}