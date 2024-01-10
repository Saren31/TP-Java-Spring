package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentification.JwtDTO;
import com.example.demo.authentification.TokenGenerator;
import com.example.demo.authentification.UserDetailsServiceTP;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceTP userDetailsService;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody JwtDTO authenticationRequest) throws Exception {
		Authentication authentification = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
		@SuppressWarnings("unused")
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = tokenGenerator.generateJwtToken(authentification);
		return ResponseEntity.ok(token);
	}
	
	
}
