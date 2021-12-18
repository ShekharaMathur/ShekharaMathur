package com.app.user.dto;

import org.springframework.beans.BeanUtils;

import com.app.user.domain.User;

public class UserDto {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String city;
	
	
	public UserDto() {
		
	}
	
	
	
	public UserDto(Long id, String name, String email, String phone, String city) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
    }

}
