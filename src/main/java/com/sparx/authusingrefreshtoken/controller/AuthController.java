package com.sparx.authusingrefreshtoken.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.authusingrefreshtoken.Model.JwtRequest;
import com.sparx.authusingrefreshtoken.Model.JwtResponse;
import com.sparx.authusingrefreshtoken.Model.RefreshTokenRequest;
import com.sparx.authusingrefreshtoken.Service.RefreshTokenService;
import com.sparx.authusingrefreshtoken.Service.UserService;
import com.sparx.authusingrefreshtoken.entities.RefreshToken;
import com.sparx.authusingrefreshtoken.entities.User;
import com.sparx.authusingrefreshtoken.helper.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	    @Autowired
        private UserService userService;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;
        @Autowired
        private RefreshTokenService refreshTokenService;
	    @Autowired
	    private JwtHelper helper;

	    private Logger logger = LoggerFactory.getLogger(AuthController.class);


	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);
	        RefreshToken refreshToken=refreshTokenService.createRefreshToken(userDetails.getUsername());
//	        JwtResponse response = JwtResponse.builder()
//	                .jwtToken(token)
//	                .username(userDetails.getUsername()).build();
	        
	        JwtResponse response=new JwtResponse();
	        response.setJwtToken(token);
	        response.setUsername(userDetails.getUsername());
	        response.setRefreshToken(refreshToken.getRefreshToken());
	     
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
       @PostMapping("/create-user")
	   public User createUser(@RequestBody User user) {
    	   return  userService.createUser(user);
    	 
	   }
      @PostMapping("/refresh")
      public JwtResponse refreshToken(@RequestBody RefreshTokenRequest request){
    	  RefreshToken refreshToken=refreshTokenService.verifyRefreshToken(request.getRefreshToken());
    	  User user=refreshToken.getUser();
    	 String token= helper.generateToken(user);
    	 JwtResponse response=new JwtResponse();
    	 response.setJwtToken(token);
    	 response.setUsername(user.getUsername());
    	 response.setRefreshToken(refreshToken.getRefreshToken());
    	 return response;
      }
}
