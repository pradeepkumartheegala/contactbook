package com.lwl.contactbook;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ContactbookApplication implements CommandLineRunner {

//	@Autowired
//	private RoleRepo roleRepo;
//	@Autowired
//	private AppUserRepo appUserRepo;
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	

	public static void main(String[] args) {
		SpringApplication.run(ContactbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		roleRepo.deleteAll();
//		appUserRepo.deleteAll();
//		
//		Role role = new Role();
//		role.setRole("user");
//		roleRepo.save(role);
//
//		AppUser appUser = new AppUser();
//		appUser.setUsername("user");
//		appUser.setPassword(passwordEncoder.encode("user123"));
//		appUser.addRole(role);
//		appUserRepo.save(appUser);
	}

}
