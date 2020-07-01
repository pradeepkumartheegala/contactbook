package com.lwl.contactbook.auth;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
		@Autowired
		private  AuthenticationManager authenticationManager;
		
		@Autowired
		private AppUserDetailsService userDetailsService;
		
		@Autowired
		private JwtTokenUtil jwtTokenUtil;
		
		@SuppressWarnings("rawtypes")
		@PostMapping("/login")
		public ResponseEntity login(@RequestBody LoginUser loginUser) throws Exception {
		
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
				);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Incorrect username or password", e);
			}

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(loginUser.getUsername());
			
			    final String token = jwtTokenUtil.generateToken(userDetails);
			    Map<Object, Object> model = new HashMap<>();
				model.put("token", token);
				return ResponseEntity.ok(model);
			
		}
}
