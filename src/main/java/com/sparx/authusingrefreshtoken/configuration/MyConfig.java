package com.sparx.authusingrefreshtoken.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MyConfig {
	
//     @Bean
//	 public UserDetailsService userDetailService() {
//		 UserDetails user1=User.withUsername("prince").password(passwordEncoder().encode("prince")).roles("ADMIN").build();
//		 UserDetails user2=User.withUsername("prakash").password(passwordEncoder().encode("prakash")).roles("USER").build();
//		 return new InMemoryUserDetailsManager(user1,user2);
//	 }
	 @Bean
     public PasswordEncoder passwordEncoder() {
    	 return new BCryptPasswordEncoder();
     }
     
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
}
