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

public class AppConfig {
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		spring boot starter security의 Basic 인증에 관한 디폴트 기능을 수정없이
//		그대로 사용하겠다는 의미
		http.httpBasic(Customizer.withDefaults());
		
//		parameter:
//		Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>
//					.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequestsCustomizer
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
