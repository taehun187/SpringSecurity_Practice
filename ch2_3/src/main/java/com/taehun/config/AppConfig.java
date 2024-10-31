package com.taehun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.taehun.security.CustomAuthenticationProvider;

public class AppConfig {
	
	private final CustomAuthenticationProvider authenticationProvider;
	
	public AppConfig(CustomAuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}
	
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.httpBasic(Customizer.withDefaults());
		
		http.authenticationProvider(authenticationProvider);
		
		http.authorizeHttpRequests(
				c -> c.anyRequest().authenticated()
		);
	
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		// UserDetails
		var user = User.withUsername("john") // id 
				.password("12345")
				.authorities("read")
				.build();
		
		return new InMemoryUserDetailsManager(user); // 테스트 용도
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // 테스트 용도 
	}
	
	
	
	
	
}
