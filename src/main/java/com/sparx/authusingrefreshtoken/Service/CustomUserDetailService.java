package com.sparx.authusingrefreshtoken.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sparx.authusingrefreshtoken.Repository.UserRepository;
import com.sparx.authusingrefreshtoken.entities.User;
@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// logic to fetch data from the database 
		User  user1=userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("user not found exception"));
		
		 return user1;
	}

}
