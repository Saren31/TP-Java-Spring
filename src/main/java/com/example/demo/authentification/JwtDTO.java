package com.example.demo.authentification;

public class JwtDTO {

    private final String username;
    private final String password;
	
    public JwtDTO(String username, String password) {
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