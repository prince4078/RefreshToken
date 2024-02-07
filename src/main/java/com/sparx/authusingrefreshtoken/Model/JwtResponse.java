package com.sparx.authusingrefreshtoken.Model;

import lombok.Builder;

@Builder
public class JwtResponse {
	private String jwtToken;
	private String username;
	private String refreshToken;
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String jwtToken, String username, String refreshToken) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
		this.refreshToken = refreshToken;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
