package com.lwl.contactbook.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

public interface ContactRepo extends MongoRepository<Contact, Integer> {

	@Query("SELECT new com.lwl.contactbook.dto.ContactWithAddressDTO(c.cid, c.name, c.email, c.mobile, a.city, a.state) FROM Contact c JOIN c.address a")
	List<ContactWithAddressDTO> getAllContacts();

	@Query("SELECT new com.lwl.contactbook.dto.ContactWithAddressDTO(c.cid, c.name, c.email, c.mobile, a.city, a.state) FROM Contact c JOIN c.address a WHERE a.city=?1")
	List<ContactWithAddressDTO> search(String str);
	

//	@Query()
//	String findByMobile(String mobile);

}
