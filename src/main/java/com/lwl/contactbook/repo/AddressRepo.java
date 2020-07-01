package com.lwl.contactbook.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lwl.contactbook.domain.Address;

public interface AddressRepo extends MongoRepository<Address, Integer> {
	
	List<Address> findByCity(String city);

}
