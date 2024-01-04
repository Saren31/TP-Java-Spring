package com.example.demo.authentification;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class JwtDTO {

    private final String username;
    private final String password;
	
    public JwtDTO(String username, String password) {
    	System.out.println("jwt_username:" + username);
    	System.out.println("jwt_password:" + password);
    	this.username = username;
    	this.password = password;
    }
    
    public String getPassword() {
		return this.password;
	}
    
    public String getUsername() {
		return this.username;
	}
}