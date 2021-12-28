package com.app.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.app.user.dto.UserDto;
import com.app.user.dto.UserPreferenceDto;
import com.app.user.utils.SimplePage;

public interface UserPreferenceService {

	UserPreferenceDto create(UserPreferenceDto userPreferenceDto, Long id);

	UserPreferenceDto getUserPrefById(Long id);

	UserPreferenceDto updateById(Long id, UserPreferenceDto userDto);

}
