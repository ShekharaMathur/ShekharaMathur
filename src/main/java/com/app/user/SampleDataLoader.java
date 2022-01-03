package com.app.user;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.app.user.domain.User;
import com.app.user.domain.UserPreference;
import com.app.user.repository.UserPreferenceRepository;
import com.app.user.repository.UserRepository;
import com.github.javafaker.Faker;
import java.util.stream.Collectors;

@Component
public class SampleDataLoader implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPreferenceRepository userPreferenceRepository;

	@Autowired
	private Faker faker;

	@Override
	public void run(String... args) throws Exception {

		List<User> user = IntStream
				.rangeClosed(1, 250).mapToObj(i -> new User(faker.name().firstName(),
						faker.internet().safeEmailAddress(), faker.phoneNumber().cellPhone(), faker.address().city()))
				.collect(Collectors.toList());

		userRepository.saveAll(user);
	}

	@Bean
	CommandLineRunner run(UserPreferenceRepository userPreferenceRepository) {
		return args -> IntStream.rangeClosed(1, 250).forEach(i -> {
			User user = new User();
			user.setId((long) i);
			UserPreference userPreference = new UserPreference();
			userPreference.setUser(user);
			userPreference.setNickName(faker.funnyName().name());
			userPreference.setDateOfBirth(faker.date().birthday());
			userPreference.setGender(faker.superhero().prefix());
			userPreference.setInterests(faker.harryPotter().house());
			userPreferenceRepository.save(userPreference);
		});
	}

}
