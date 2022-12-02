package com.shaping.code.admin.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityAuth extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {

		/*
		 * it is hashing function to convert plain text into cipher text it implements
		 * PasswordEncoder
		 */
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.csrf: because of sometimes create/post required given 403 error you have disable it
		 */

		http.csrf().disable();

		/*
		 * permit all request or we can say not required to authenticate
		 */

		http.authorizeHttpRequests().anyRequest().permitAll();

	}

}
