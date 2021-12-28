package com.app.user.service;

import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Pageable;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
//import com.app.user.exception.UserAlreadyExistsException;
import com.app.user.utils.SimplePage;

public interface UserService {

	UserDto save(UserDto userDto);

	UserDto findById(Long id);

	void deleteById(Long id);

	UserDto updateById(Long id, UserDto userDto);

	SimplePage<UserDto> getAll(final Pageable pageable);

}
