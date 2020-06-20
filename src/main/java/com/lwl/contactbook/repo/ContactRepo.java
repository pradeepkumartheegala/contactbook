package com.lwl.contactbook.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

public interface ContactRepo extends CrudRepository<Contact, Integer> {

	@Query("SELECT new com.lwl.contactbook.dto.ContactWithAddressDTO(c.cid, c.name, c.email, c.mobile, a.city, a.state) FROM Contact c JOIN c.address a")
	List<ContactWithAddressDTO> getAllContacts();

	@Query("SELECT new com.lwl.contactbook.dto.ContactWithAddressDTO(c.cid, c.name, c.email, c.mobile, a.city, a.state) FROM Contact c JOIN c.address a WHERE a.city=?1")
	List<ContactWithAddressDTO> search(String str);
	

//	@Query()
//	String findByMobile(String mobile);

}
