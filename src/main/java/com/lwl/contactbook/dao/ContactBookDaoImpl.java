package com.lwl.contactbook.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;
import com.lwl.contactbook.exceptions.ContactNotFoundException;
import com.lwl.contactbook.repo.AddressRepo;
import com.lwl.contactbook.repo.ContactRepo;

@Repository
public class ContactBookDaoImpl implements ContactBookDao {

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private AddressRepo addressRepo;

	private Logger log = LoggerFactory.getLogger(ContactBookDaoImpl.class);

	@Override
	public List<ContactWithAddressDTO> getAllContacts() {

		List<ContactWithAddressDTO> contactWithAddressDTO = contactRepo.getAllContacts();
		if (contactWithAddressDTO == null || contactWithAddressDTO.size() == 0) {
			log.info("No table data found add before getting list");
		}
		return contactWithAddressDTO;
	}

	@Override
	public List<ContactWithAddressDTO> search(String str) {
		List<ContactWithAddressDTO> contactWithAddressDTO = contactRepo.getAllContacts();
		if (contactWithAddressDTO == null || contactWithAddressDTO.size() == 0) {
			log.info("Entered city: {} not there", str);
		}
		return contactWithAddressDTO;
	}

	@Override
	public Contact addContact(Contact contact) {
		contact = contactRepo.save(contact);
		log.info("Contact is added with id :{}", contact.getCid());
		return contact;
	}

	@Override
	public boolean deleteContact(int cid) {
		contactRepo.deleteById(cid);
		log.info("Contact is deleted with id :{}", cid);
		return true;
	}

	@Override
	public Contact updateContact(Contact contact) {
		contact = contactRepo.save(contact);
		log.info("Contact is Updated with id :{}", contact.getCid());
		return contact;
	}

	@Override
	public Contact getContact(int cid) {
		Optional<Contact> obj = contactRepo.findById(cid);
		System.out.println("print");
		System.out.println();
		if (obj.isPresent()) {
			System.out.println("print "+obj);
			return obj.get();
		}
		throw new ContactNotFoundException("Contact id is not found...");

	}

	@Override
	public Address addAddress(Address address, int cid) {
		Optional<Contact> obj = contactRepo.findById(cid);
		Contact contact = Optional.ofNullable(obj).flatMap(e -> e)
				.orElseThrow(() -> new IllegalArgumentException("Contact id is not found"));
		address = addressRepo.save(address);
		log.info("Contact address is added with id :{}", address.getAid());
		contact.setAddress(address);
		contactRepo.save(contact);
		return address;
	}

	@Override
	public List<Address> searchByCity(String str) {
		return addressRepo.findByCity(str);
	}

	@Override
	public boolean deleteAddress(int aid) {
		addressRepo.deleteById(aid);
		log.info("Contact is deleted with id :{}", aid);
		return true;
	}

	@Override
	public Address updateAddress(Address address) {
		address = addressRepo.save(address);
		log.info("Contact is Updated with id :{}", address.getAid());
		return address;
	}

	@Override
	public Address getAddress(int aid) {
		Optional<Address> obj = addressRepo.findById(aid);
		Address address = Optional.ofNullable(obj).flatMap(e -> e)
				.orElseThrow(() -> new IllegalArgumentException("Address id is not found"));
		return address;
	}

	@Override
	public String findByMobile(String mobile) {
//		return contactRepo.findByMobile(mobile);
		return null;
	}

}