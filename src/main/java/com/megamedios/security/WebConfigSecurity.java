package com.megamedios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.megamedios.security.jwt.JwtAuthEntryPoint;
import com.megamedios.security.jwt.JwtAuthTokenFilter;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled=true)
public class WebConfigSecurity {
	
	private final UserDetailsService userDetailService;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	JwtAuthTokenFilter authenticationTokenFilter() {
		return new JwtAuthTokenFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		var authProvider = new DaoAuthenticationProvider();
    	authProvider.setUserDetailsService(userDetailService);
    	authProvider.setPasswordEncoder(passwordEncoder());
    	return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    	.csrf(csrf -> csrf.disable())
    	.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
    	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    	.authorizeHttpRequests(auth -> auth
    			.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
    			.requestMatchers("/","/auth/**","/static/**","/assets/**","index.html").permitAll()
    			.anyRequest().authenticated());
    			
    	http.authenticationProvider(authenticationProvider());
    	http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
}
