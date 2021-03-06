package com.app.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.user.domain.User;
import com.app.user.domain.UserPreference;
import com.app.user.dto.UserDto;
import com.app.user.dto.UserPreferenceDto;
import com.app.user.exception.EntityNotFoundException;
import com.app.user.exception.UserAlreadyPresentException;
import com.app.user.repository.UserPreferenceRepository;
//import com.app.user.exception.ResourceNotFoundException;
//import com.app.user.exception.UserAlreadyExistsException;
import com.app.user.repository.UserRepository;
import com.app.user.service.UserService;
import com.app.user.utils.SimplePage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPreferenceRepository userPreferenceRepository;

	@Override
	public UserDto save(UserDto userDto) {

		User user = this.dtoToDomain(userDto);

		User isEmailExists = this.userRepository.findByEmail(user.getEmail());
		User isPhoneExists = this.userRepository.findByPhone(user.getPhone());

		if (isEmailExists != null || isPhoneExists != null) {
			throw new UserAlreadyPresentException("Email or Mobile number already exits");
		}
		User savedUser = this.userRepository.save(user);

		return new UserDto(savedUser);

	}

	private User dtoToDomain(UserDto userDto) {

		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;

	}

	@Override
	public UserDto findById(Long id) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource not found for id " + id));

		UserPreference userPreference = this.userPreferenceRepository.findByUserId(id);

		UserDto retUserDto = new UserDto();

		if (userPreference != null) {
			UserPreferenceDto userPreferences = new UserPreferenceDto(userPreference);
			retUserDto.setUserPreferenceDto(userPreferences);
		}

		// Todo Call Constructor with fields
		retUserDto.setId(user.getId());
		retUserDto.setName(user.getName());
		retUserDto.setEmail(user.getEmail());
		retUserDto.setPhone(user.getPhone());
		retUserDto.setCity(user.getCity());

		return retUserDto;
	}

	@Override
	public void deleteById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource not found for id " + id));

		this.userRepository.deleteById(user.getId());

	}

	@Override
	public UserDto updateById(Long id, UserDto userDto) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Resource not found for id " + id));

		user.setName(userDto.getName() == null ? user.getName() : userDto.getName());
		user.setEmail(userDto.getEmail() == null ? user.getEmail() : userDto.getEmail());
		user.setPhone(userDto.getPhone() == null ? user.getPhone() : userDto.getPhone());
		user.setCity(userDto.getCity() == null ? user.getCity() : userDto.getCity());

		User updatedUser = this.userRepository.save(user);
		return new UserDto(updatedUser);

	}

	@Override
	public SimplePage<UserDto> getAll(Pageable pageable) {
		final Page<User> page = userRepository.findAll(pageable);
		return new SimplePage<>(page.getContent().stream().map(user -> new UserDto(user)).collect(Collectors.toList()),
				page.getTotalElements(), pageable);
	}

	@Override
	public List<UserDto> findAllUsers(Integer pageNo, Integer pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<User> pagedResult = userRepository.findAll(paging);

		List<User> ulist = new ArrayList<User>();
		if (pagedResult != null && pagedResult.hasContent()) {
			ulist = pagedResult.getContent();
		}

		return ulist.stream().map(user -> new UserDto(user)).collect(Collectors.toList());

	}

}
