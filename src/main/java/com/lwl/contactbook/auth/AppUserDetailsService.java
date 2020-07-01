package com.lwl.contactbook.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	private Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);
	
	@Autowired
	private AppUserRepo appUserRepo;
	

	@Autowired
	public AppUserDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.debug("user trying to login:{}",email);
		AppUser user = appUserRepo.findByUsername(email);
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}

	private UserDetails buildUserForAuthentication(AppUser user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(),user.getPassword(), authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> roles) {
		Set<GrantedAuthority> r = new HashSet<>();
		roles.forEach(role -> r.add(new SimpleGrantedAuthority(role.getRole())));
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(r);
		log.debug("User has total authorities is :{}",grantedAuthorities.size());
		return grantedAuthorities;
	}

}