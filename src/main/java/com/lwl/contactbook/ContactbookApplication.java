package com.lwl.contactbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ContactbookApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {

		SpringApplication.run(ContactbookApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName());
	}

}
