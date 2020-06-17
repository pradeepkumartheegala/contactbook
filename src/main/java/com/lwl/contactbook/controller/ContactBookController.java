package com.lwl.contactbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.contactbook.dto.AddressDTO;
import com.lwl.contactbook.dto.ContactDTO;
import com.lwl.contactbook.dto.ContactWithAddressDTO;
import com.lwl.contactbook.service.ContactBookServiceImpl;

@RestController
@RequestMapping("api/v1/cbook/")
public class ContactBookController {

	@Autowired
	ContactBookServiceImpl contactBookServiceImpl;

	@GetMapping("all")
	public List<ContactWithAddressDTO> getAll() {
		return contactBookServiceImpl.getAllContacts();
	}

	@GetMapping("address/{aid}")
	public AddressDTO getAddress(@PathVariable("aid") int aid) {
		return contactBookServiceImpl.getAddress(aid);
	}

	@GetMapping("contact/{cid}")
	public ContactDTO getContact(@PathVariable("cid") int cid) {
		return contactBookServiceImpl.getContact(cid);
	}

	@PostMapping("newaddress")
	public AddressDTO addAddress(@RequestBody AddressDTO addressDTO) {
		return contactBookServiceImpl.addAddress(addressDTO);
	}

	@PostMapping("newcontact")
	public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
		return contactBookServiceImpl.addContact(contactDTO);
	}

	@PutMapping("updateaddress")
	public AddressDTO updateAddress(AddressDTO addressDTO) {
		return contactBookServiceImpl.updateAddress(addressDTO);
	}

	@PutMapping("updatecontact")
	public ContactDTO updateContact(ContactDTO contactDTO) {
		return contactBookServiceImpl.updateContact(contactDTO);
	}

	@DeleteMapping("delete/{cid}")
	public Boolean deleteContact(@PathVariable("cid") int cid) {
		return contactBookServiceImpl.deleteContact(cid);
	}
}
