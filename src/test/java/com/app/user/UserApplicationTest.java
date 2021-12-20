package com.app.user;


import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import com.app.user.dto.UserDto;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserApplicationTest {
	
	public final String reqUrl = "/api/v1/users";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	//@SuppressWarnings("unused")
	public String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	@Order(1)
	public void testPostUser() {
		UserDto user = new UserDto();
		user.setName("junit105");
		user.setEmail("junit104@gmail.com");
		user.setPhone("1234567105");
		user.setCity("testworld");
		
		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
		System.out.println(postResponse.getBody());
		Assert.assertEquals(201, postResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(2)
	public void testPostBadUser() {
		UserDto user = new UserDto();
		user.setName("junit105");
		user.setEmail("junit105@gmail.com");
		user.setPhone("123456715");
		user.setCity("testworld");
		
		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
		Assert.assertEquals(400, postResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(3)
	public void testPostBlankPost() {
		UserDto user = new UserDto();
		user.setName("");
		user.setEmail("");
		user.setPhone("");
		user.setCity("");
		
		ResponseEntity<UserDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/users", user, UserDto.class);
		Assert.assertEquals(400, postResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(4)
	public void testGetUserById() {
		
		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/4", UserDto.class);
		Assert.assertEquals(200, getResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(5)
	public void testGetUseNotFoundById() {
		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/9991", UserDto.class);
		Assert.assertEquals(404, getResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(6)
	public void testUpdateUserById() {
		
		UserDto userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/4", UserDto.class);
		userDto.setName("junitNew");
		userDto.setEmail("junitnew@gmail.com");
		userDto.setPhone("9742591664");
		
		restTemplate.put(getRootUrl() + "/api/v1/users/2", userDto);
		ResponseEntity<UserDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/api/v1/users/2", UserDto.class);
		Assert.assertEquals(200, getResponse.getStatusCodeValue());
		
	}
	
	@Test
	@Order(7)
	public void testDeleteById() {
		
		UserDto userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/2", UserDto.class);
		Assert.assertNotNull(userDto);
		
		restTemplate.delete(getRootUrl() + "/api/v1/users/2");
		
		try {
			userDto = restTemplate.getForObject(getRootUrl() + "/api/v1/users/2", UserDto.class);
			
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}

