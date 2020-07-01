package com.lwl.contactbook.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lwl.contactbook.auth.AppUser;
import com.lwl.contactbook.auth.AppUserRepo;
import com.lwl.contactbook.auth.Role;
import com.lwl.contactbook.auth.RoleRepo;
import com.lwl.contactbook.repo.AddressRepo;
import com.lwl.contactbook.repo.ContactRepo;

@Component
public class CleanDBService {

	private static final Logger log = LoggerFactory.getLogger(CleanDBService.class);
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private ContactRepo contactRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedDelayString = "PT30M")
	public void ClearDB() {
		addressRepo.deleteAll();
		contactRepo.deleteAll();
		roleRepo.deleteAll();
		appUserRepo.deleteAll();
		
		Role role = new Role();
		role.setId(12345l);
		role.setRole("user");
		roleRepo.save(role);

		AppUser appUser = new AppUser();
		appUser.setId(23444l);
		appUser.setUsername("user");
		appUser.setPassword(passwordEncoder.encode("user123"));
		appUser.addRole(role);
		appUserRepo.save(appUser);
		log.info("Database is cleaned at {}", dateFormat.format(new Date()));
		
		
	}

	
}
