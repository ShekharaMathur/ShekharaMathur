package com.app.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import com.app.user.dto.UserPreferenceDto;
import com.app.user.exception.EntityNotFoundException;
import com.app.user.service.UserPreferenceService;
import com.app.user.service.UserService;
import com.app.user.utils.SimplePage;

@RestController
@RequestMapping("/api/v1")
public class UserPreferenceController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserPreferenceService userPreferenceService;

	@PostMapping("/users/{id}/preferences")
	public ResponseEntity<UserPreferenceDto> createUserPreference(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserPreferenceDto userPreferenceDto) {

		UserDto isExists = this.userService.findById(id);
		if (isExists == null) {
			throw new EntityNotFoundException("User is not with us" + id);
		}
		UserPreferenceDto saveUserPreferenceDto = this.userPreferenceService.create(userPreferenceDto, id);

		return new ResponseEntity<UserPreferenceDto>(saveUserPreferenceDto, HttpStatus.CREATED);

	}

	@GetMapping("/users/{id}/get-preferences")
	public ResponseEntity<UserPreferenceDto> getUserPrefById(@Valid @PathVariable(value = "id") Long id) {
		UserDto isExists = this.userService.findById(id);
		if (isExists == null) {
			throw new EntityNotFoundException("User is not with us" + id);
		}

		return new ResponseEntity<>(this.userPreferenceService.getUserPrefById(id), HttpStatus.OK);

	}

	@PutMapping("/users/{id}/update-preferences")
	public ResponseEntity<UserPreferenceDto> updateUserPreference(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserPreferenceDto userPreferenceDto) {

		UserDto isExists = this.userService.findById(id);
		if (isExists == null) {
			throw new EntityNotFoundException("User is not with us" + id);
		}
		UserPreferenceDto updatedPreferenceDto = this.userPreferenceService.updateById(id, userPreferenceDto);
		return new ResponseEntity<>(updatedPreferenceDto, HttpStatus.ACCEPTED);

	}
}
