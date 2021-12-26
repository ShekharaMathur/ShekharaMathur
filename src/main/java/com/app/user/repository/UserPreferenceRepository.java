package com.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.user.domain.UserPreference;
import com.app.user.dto.UserPreferenceDto;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

	UserPreference findByUserId(Long id);

}
