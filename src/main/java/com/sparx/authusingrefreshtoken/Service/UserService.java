package com.sparx.authusingrefreshtoken.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparx.authusingrefreshtoken.Repository.UserRepository;
import com.sparx.authusingrefreshtoken.entities.User;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	 @Autowired
	private UserRepository userRepository;
	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}
    
}
