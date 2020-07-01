package com.lwl.contactbook.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, Long>{

}
