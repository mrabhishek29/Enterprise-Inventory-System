 package com.abhishek.enterprise_inventory_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception
	{
		security.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**",
								"/products/**",
								"/orders/**",
								"/swagger-ui/**",
								"/v3/api-docs/**")
						.permitAll()
						.anyRequest()
						.authenticated());
		
		return security.build();
	}
}