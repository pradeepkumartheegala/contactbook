package com.lwl.contactbook.auth;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepo extends MongoRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
