package com.app.user.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import com.app.user.exception.ResourceNotFoundException;
import com.app.user.exception.UserAlreadyExistsException;
import com.app.user.repository.UserRepository;
import com.app.user.service.UserService;
import com.app.user.utils.SimplePage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto save(UserDto userDto) {
		

		User user = this.dtoToDomain(userDto);
		User savedUser = null;
		try {
			savedUser = this.userRepository.save(user);
		}catch (Exception e) {
			throw new UserAlreadyExistsException("User already present in db");
		}
		return new UserDto(savedUser);
			
	}

	private User dtoToDomain(UserDto userDto) {
		
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
		
	}

	@Override
	public UserDto findById(Long id) {
		
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		return new UserDto(user);
	}

	@Override
	public void deleteById(Long id) {
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		this.userRepository.deleteById(user.getId());
		
	}

	@Override
	public UserDto updateById(Long id, UserDto userDto) {
		
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setCity(userDto.getCity());
		
		User updatedUser = this.userRepository.save(user);
		return new UserDto(updatedUser);
		
	}


	@Override
	public SimplePage<UserDto> getAll(Pageable pageable) {
		final Page<User> page = userRepository.findAll(pageable);
		return new SimplePage<>(page.getContent()
                .stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList()),
                page.getTotalElements(), pageable);
	}

}
