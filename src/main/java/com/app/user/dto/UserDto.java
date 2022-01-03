package com.app.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.app.user.domain.User;
import com.app.user.utils.UserView;
import com.fasterxml.jackson.annotation.JsonView;

public class UserDto {

	private Long id;

	@NotEmpty(message = "The full name is required and length is between 2 to 50 characters.")
	@Size(min = 2, max = 50)
	@JsonView(UserView.BaseView.class)
	private String name;

	@NotEmpty(message = "The email address is required.")
	@Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
	@JsonView(UserView.BaseView.class)
	private String email;

	@NotEmpty(message = "The Mobile num is required.")
	private String phone;

	@NotEmpty(message = "The City is required.")
	private String city;

	private UserPreferenceDto userPreference;
	
	public UserDto() {
		
	}

	public UserDto(Long id,
			@NotEmpty(message = "The full name is required and length is between 2 to 50 characters.") @Size(min = 2, max = 50) String name,
			@NotEmpty(message = "The email address is required.") @Email(message = "The email address is invalid.", flags = Flag.CASE_INSENSITIVE) String email,
			@NotEmpty(message = "The Mobile num is required.") String phone,
			@NotEmpty(message = "The City is required.") String city, UserPreferenceDto userPreference) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.userPreference = userPreference;
	}
	
	

	public UserPreferenceDto getUserPreferenceDto() {
		return userPreference;
	}

	public void setUserPreferenceDto(UserPreferenceDto userPreference) {
		this.userPreference = userPreference;
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
