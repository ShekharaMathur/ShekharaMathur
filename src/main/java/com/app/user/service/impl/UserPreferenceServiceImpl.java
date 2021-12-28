package com.app.user.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.user.domain.User;
import com.app.user.domain.UserPreference;
import com.app.user.dto.UserDto;
import com.app.user.dto.UserPreferenceDto;
import com.app.user.exception.EntityNotFoundException;
import com.app.user.exception.UserAlreadyPresentException;
import com.app.user.repository.UserPreferenceRepository;
import com.app.user.service.UserPreferenceService;
import com.app.user.service.UserService;
import com.app.user.utils.SimplePage;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

	@Autowired
	private UserPreferenceRepository userPreferenceRepository;

	@Override
	public UserPreferenceDto create(UserPreferenceDto userPreferenceDto, Long id) {

		UserPreference isExists = this.userPreferenceRepository.findByUserId(id);

		if (isExists != null) {
			throw new UserAlreadyPresentException("User Preference Already set for this id");
		}

		UserPreference userPreference = this.dtoToDomain(userPreferenceDto);

		User user = new User();
		user.setId(id);
		userPreference.setUser(user);

		UserPreference savedUserPreference = this.userPreferenceRepository.save(userPreference);

		return new UserPreferenceDto(savedUserPreference);

	}

	private UserPreference dtoToDomain(UserPreferenceDto userPreferenceDto) {

		UserPreference userPreference = new UserPreference();
		BeanUtils.copyProperties(userPreferenceDto, userPreference);
		return userPreference;

	}

	@Override
	public UserPreferenceDto getUserPrefById(Long id) {

		UserPreference isExists = this.userPreferenceRepository.findByUserId(id);

		if (isExists == null) {
			throw new EntityNotFoundException("User doesnot have any preference for this id");
		}

		return new UserPreferenceDto(isExists);

	}

	@Override
	public UserPreferenceDto updateById(Long id, UserPreferenceDto userPreferenceDto) {

		UserPreference userPref = this.userPreferenceRepository.findByUserId(id);

		if (userPref == null) {
			throw new EntityNotFoundException("User doesnot have any preference for this id");
		}

		userPref.setNickName(userPreferenceDto.getNickName());
		userPref.setDateOfBirth(userPreferenceDto.getDateOfBirth());
		userPref.setGender(userPreferenceDto.getGender());
		userPref.setInterests(userPreferenceDto.getInterests());

		UserPreference savedUserPreference = this.userPreferenceRepository.save(userPref);

		return new UserPreferenceDto(savedUserPreference);
	}

}
