package com.sparx.authusingrefreshtoken.Service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparx.authusingrefreshtoken.Repository.RefreshTokenRepository;
import com.sparx.authusingrefreshtoken.Repository.UserRepository;
import com.sparx.authusingrefreshtoken.entities.RefreshToken;
import com.sparx.authusingrefreshtoken.entities.User;

import lombok.Builder;

@Service

public class RefreshTokenService {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	private UserRepository userRepository;
	 
	private long refreshTokenExpiry=2*60*1000;
	
	public RefreshToken createRefreshToken(String userName) {
		
		User user=userRepository.findByEmail(userName).get();
		RefreshToken refreshToken =user.getRefreshToken();
		if(refreshToken==null) {
			refreshToken=new RefreshToken();
			
			refreshToken.setRefreshToken(UUID.randomUUID().toString());
			refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenExpiry));
			refreshToken.setUser(userRepository.findByEmail(userName).get());
			
		}else {
			refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenExpiry));
		}
		user.setRefreshToken(refreshToken);
		refreshTokenRepository.save(refreshToken);
		return refreshToken;
		
	}
	public RefreshToken verifyRefreshToken(String refreshToken) {
		RefreshToken refreshTokenObj=refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()->
		new RuntimeException ("refresh token does not exist"));
		if(refreshTokenObj.getExpiry().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenObj);
			throw new RuntimeException("token expired ");
		}
			return refreshTokenObj;
		
		
	}

}
