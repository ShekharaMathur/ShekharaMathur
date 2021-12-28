package com.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.app.user.dto.UserDto;
import com.app.user.service.UserService;
import com.app.user.utils.SimplePage;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto savedUsedDto = this.userService.save(userDto);

		// return new ResponseEntity<UserDto>(savedUsedDto, HttpStatus.CREATED);
		return new ResponseEntity<>(savedUsedDto, HttpStatus.CREATED);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") Long id) {

		return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);

	}

	@DeleteMapping("/users/{id}")
	public void deleteByID(@PathVariable(value = "id") Long id) {

		this.userService.deleteById(id);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateById(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserDto userDto) {

		UserDto updatedUserdto = this.userService.updateById(id, userDto);
		return new ResponseEntity<>(updatedUserdto, HttpStatus.ACCEPTED);

	}

	@GetMapping("/users")
	public ResponseEntity<SimplePage<UserDto>> getAllRoles(
			@SortDefault(sort = "id") @PageableDefault(size = 2) final Pageable pageable) {

		return ResponseEntity.ok(this.userService.getAll(pageable));

	}
}
