package com.megamedios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megamedios.request.LoginRequest;
import com.megamedios.response.AuthResponse;
import com.megamedios.security.jwt.JwtUtils;
import com.megamedios.service.UserService;
import com.megamedios.entities.User;
import com.megamedios.exception.UserException;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getClave()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtTokenForUser(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String role = userDetails.getAuthorities().toString();
		
		AuthResponse authResponse = new AuthResponse(userDetails.getUsername(), jwt, role);
		
		return ResponseEntity.ok(authResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		try {
			userService.saveUser(user);
			return ResponseEntity.ok("Usuario Registrado!");
		}catch(UserException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}
}
