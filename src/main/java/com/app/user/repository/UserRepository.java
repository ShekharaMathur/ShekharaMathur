package com.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.user.domain.User;
//import com.app.user.dto.UserDto;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);

	User findByPhone(String phone);
	

}
