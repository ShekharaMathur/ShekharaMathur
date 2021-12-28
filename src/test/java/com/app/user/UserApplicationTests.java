//package com.app.user;
//
//
//import org.junit.Test;
//import org.junit.Assert;
//import org.junit.FixMethodOrder;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.HttpClientErrorException;
//
//import com.app.user.dto.UserDto;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class UserApplicationTests {
//	
//	public final String reqUrl = "/api/v1/users";
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
//	
//	@LocalServerPort
//	private int port;
//	
//	//@SuppressWarnings("unused")
//	public String getRootUrl() {
//		return "http://localhost:" + port;
//	}
//	
//	@Test
//	public void contextLoads() {
//	}
//	
//	@Test
//	public void testA() {
//		System.out.println("Post test with return status code is 201");
//		UserDto user = new UserDto();
//		user.setName("junit105");
//		user.setEmail("junit104@gmail.com");
//		user.setPhone("1234567105");
//		user.setCity("testworld");
//		
//		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
//		Assert.assertEquals(201, postResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testB() {
//		System.out.println("Post a resource to db: return status code is 400");
//		UserDto user = new UserDto();
//		user.setName("junit105");
//		user.setEmail("junit105@gmail.com");
//		user.setPhone("123456715");
//		user.setCity("testworld");
//		
//		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
//		Assert.assertEquals(400, postResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testC() {
//		System.out.println("Post test with return status code is 400");
//		UserDto user = new UserDto();
//		user.setName("");
//		user.setEmail("");
//		user.setPhone("");
//		user.setCity("");
//		
//		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
//		Assert.assertEquals(400, postResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testD() {
//		System.out.println("Get a single resource from db: return status code is 200");
//		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/1", UserDto.class);
//		Assert.assertEquals(200, getResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testE() {
//		System.out.println("Get a single resource not in db: return status code is 404");
//		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/9991", UserDto.class);
//		Assert.assertEquals(404, getResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testF() {
//		System.out.println("Update a single resource with id: return status code is 200");
//		UserDto userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/1", UserDto.class);
//		userDto.setName("junitNew");
//		userDto.setEmail("junitnew@gmail.com");
//		userDto.setPhone("9742591664");
//		
//		restTemplate.put(getRootUrl() + "/api/v1/users/1", userDto);
//		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/1", UserDto.class);
//		Assert.assertEquals(200, getResponse.getStatusCodeValue());
//		
//	}
//	
//	@Test
//	public void testG() {
//		
//		System.out.println("Delete a single resource in db with id: return status code is 404");
//		UserDto userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/1", UserDto.class);
//		Assert.assertNotNull(userDto);
//		
//		restTemplate.delete(getRootUrl() + "/api/v1/users/1");
//		
//		try {
//			userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/1", UserDto.class);
//			
//		} catch (final HttpClientErrorException e) {
//			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//		}
//		
//	}
//	
//}
//
