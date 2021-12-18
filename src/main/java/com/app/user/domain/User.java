package com.app.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@Column(name = "email")
	@NotNull
	@Email(message = "Email should be valid")
	private String email;
	
	@Column(name = "phone")
	@NotNull
	@Size(min = 10, max = 10)
	private String phone;
	
	@Column(name = "city")
	@NotNull
	@Size(min = 3, max = 20)
	private String city;

	public User() {

	}

	public User(Long id, String name, String email, String phone, String city) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", city=" + city + "]";
	}

}
