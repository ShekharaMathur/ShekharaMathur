//package com.app.user;
//
//import java.net.http.HttpHeaders;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.Assert;
//
//import com.app.user.controller.UserController;
//import com.app.user.domain.User;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserApplicationTests {
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@LocalServerPort
//	private int port;
//
//	private String getRootUrl() {
//		return "http://localhost:" + port;
//	}
//	
//
//	@Test
//	void contextLoads() {
//	}
//	
//	@Test
//	public void testGetUserById() {
//		
//		//
//	}
//}
//
