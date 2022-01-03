package com.app.user.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class SampleDataConfig {
	
	@Bean
	Faker faker() {
		return new Faker();
	}
}
