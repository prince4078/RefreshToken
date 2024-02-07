package com.sparx.authusingrefreshtoken.entities;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="refresh_token")
public class RefreshToken {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tokenId;
	private String refreshToken;
	private Instant expiry;
	@OneToOne
	private User user;
	public RefreshToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RefreshToken(String refreshToken, Instant expiry, User user) {
		super();
		this.refreshToken = refreshToken;
		this.expiry = expiry;
		this.user = user;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Instant getExpiry() {
		return expiry;
	}
	public void setExpiry(Instant expiry) {
		this.expiry = expiry;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "RefreshToken [refreshToken=" + refreshToken + ", expiry=" + expiry + ", user=" + user + "]";
	}
	

}
