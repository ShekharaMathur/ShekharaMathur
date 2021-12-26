package com.app.user.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.app.user.domain.User;
import com.app.user.domain.UserPreference;

public class UserPreferenceDto {

	@NotNull(message = "Nick name is required.")
	@Size(min = 2, max = 31)
	private String nickName;

	@NotNull(message = "The date of birth is required.")
	@Past(message = "The date of birth must be in the past.")
	private Date dateOfBirth;

	@NotEmpty(message = "The gender is required.")
	private String gender;

	@NotNull(message = "Nick name is required.")
	@Size(min = 4, max = 128)
	private String interests;

	public UserPreferenceDto() {

	}

	public UserPreferenceDto(@NotNull(message = "Nick name is required.") @Size(min = 2, max = 31) String nickName,
			@NotNull(message = "The date of birth is required.") @Past(message = "The date of birth must be in the past.") Date dateOfBirth,
			@NotEmpty(message = "The gender is required.") String gender,
			@NotNull(message = "Nick name is required.") @Size(min = 4, max = 128) String interests) {
		super();
		this.nickName = nickName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.interests = interests;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public UserPreferenceDto(UserPreference userPreference) {
		BeanUtils.copyProperties(userPreference, this);
	}

}
