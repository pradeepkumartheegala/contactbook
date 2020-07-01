package com.lwl.contactbook.web;

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
@RequestMapping("/api/v1/cbook/")
public class ContactBookController {

	@Autowired
	ContactBookServiceImpl contactBookServiceImpl;

	@GetMapping("all")
	public List<ContactWithAddressDTO> getAll() {
		return contactBookServiceImpl.getAllContacts();
	}
	@GetMapping("search/{city}")
	public List<ContactWithAddressDTO> search(@PathVariable("city") String city) {
		return contactBookServiceImpl.search(city);
	}
	
	@GetMapping("searchbycity/{city}")
	public List<AddressDTO> searchbycity(@PathVariable("city") String city) {
		return contactBookServiceImpl.searchByCity(city);
	}

	@GetMapping("address/{aid}")
	public AddressDTO getAddress(@PathVariable("aid") int aid) {
		return contactBookServiceImpl.getAddress(aid);
	}

	@GetMapping("contact/{cid}")
	public ContactDTO getContact(@PathVariable("cid") int cid) {
		return contactBookServiceImpl.getContact(cid);
	}

	@PostMapping("newaddress/{cid}")
	public AddressDTO addAddress(@RequestBody AddressDTO addressDTO, @PathVariable("cid") int cid) {
		return contactBookServiceImpl.addAddress(addressDTO, cid);
	}

	@PostMapping("newcontact")
	public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
		return contactBookServiceImpl.addContact(contactDTO);
	}

	@PutMapping("updateaddress")
	public AddressDTO updateAddress(@RequestBody AddressDTO addressDTO) {
		return contactBookServiceImpl.updateAddress(addressDTO);
	}

	@PutMapping("updatecontact")
	public ContactDTO updateContact(@RequestBody ContactDTO contactDTO) {
		return contactBookServiceImpl.updateContact(contactDTO);
	}

	@DeleteMapping("removecontact/{cid}")
	public void deleteContact(@PathVariable("cid") int cid) {
		contactBookServiceImpl.deleteContact(cid);
	}

	@DeleteMapping("removeaddress/{aid}")
	public void deleteAddress(@PathVariable("aid") int aid) {
		contactBookServiceImpl.deleteAddress(aid);
	}
}