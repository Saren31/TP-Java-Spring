package com.example.demo.authentification;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class OncePerRequestFilterTP extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceTP userDetailsService;
	
	@Autowired
	private TokenValidator tokenValidator;
	
	@Autowired
	private AuthenticationEntryPointTP authenticationEntryPoint;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String nom = null;
		
		System.out.println(requestTokenHeader);
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
		}
		
		
		
		if (tokenValidator.validateJwtToken(jwtToken)) {
			
			nom = tokenValidator.getUserNameFromJwtToken(jwtToken);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(nom);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
		
		}
		else {
			authenticationEntryPoint.commence(request, response, null);
		}
		}catch (Exception e) {
			logger.error("Problème JWT: " + e);
        }

		
		filterChain.doFilter(request, response);
		
	}

}
