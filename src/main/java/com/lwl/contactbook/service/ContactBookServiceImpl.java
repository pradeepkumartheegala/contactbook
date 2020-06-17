package com.lwl.contactbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.lwl.contactbook.dao.ContactBookDao;
import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.AddressDTO;
import com.lwl.contactbook.dto.ContactDTO;
import com.lwl.contactbook.dto.ContactWithAddressDTO;
import com.lwl.contactbook.service.exceptions.ContactAlreadyExistsException;
import com.lwl.contactbook.service.exceptions.ContactNotFoundException;

@Service

public class ContactBookServiceImpl implements ContactBookService {

	private Logger log = LoggerFactory.getLogger(ContactBookServiceImpl.class);

	@Autowired
	private ContactBookDao contactDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ContactWithAddressDTO> getAllContacts() {
		List<ContactWithAddressDTO> contactWithAddressDTO=contactDao.getAllContacts();
		if(contactWithAddressDTO==null||contactWithAddressDTO.size()==0) {
			log.info("Contact list is empty or size");
			throw new ContactNotFoundException("Contacts not yet added");
		}
		return contactWithAddressDTO;
	}

	@Override
	public List<ContactWithAddressDTO> search(String str) {
		Assert.notNull(str, "Search string can't be null");
		return contactDao.search(str);
	}

	@Override
	public ContactDTO addContact(ContactDTO contactDto) {
		Assert.notNull(contactDto, "Contact object can't be null");
		Assert.notNull(contactDto.getName(), "Contact name can't be null or empty");
		Assert.notNull(contactDto.getMobile(), "Contact mobile can't be null or empty");

		String name = contactDao.findByMobile(contactDto.getMobile());
		if (name != null) {
			throw new ContactAlreadyExistsException(
					String.format("Contact with mobile: %s and user name: %s", contactDto.getMobile(), name));
		}

		Contact contact = modelMapper.map(contactDto, Contact.class);
		contact = contactDao.addContact(contact);
		if (contact != null) {
			log.info("Contact is added with id:{}", contact.getCid());
			contactDto = modelMapper.map(contact, ContactDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return contactDto;
	}

	@Override
	public boolean deleteContact(int cid) {
		Assert.notNull(cid, "Cid can't be null");
		Contact contact = contactDao.getContact(cid);
		if (contact == null) {
			throw new ContactNotFoundException(String.format("Contact is not found for the given id: %s", cid));
		}
		boolean delete = contactDao.deleteContact(cid);
		log.info("Contact with id {} is deleted sucessfully", cid);
		return delete;
	}

	@Override
	public ContactDTO updateContact(ContactDTO contactDto) {
		Assert.notNull(contactDto, "Contact object can't be null");
		Assert.notNull(contactDto.getName(), "Contact name can't be null or empty");
		Assert.notNull(contactDto.getMobile(), "Contact mobile can't be null or empty");
		Contact contact = modelMapper.map(contactDto, Contact.class);
		contact = contactDao.updateContact(contact);
		if (contact != null) {
			log.info("Contact is added with id:{}", contact.getCid());
			contactDto = modelMapper.map(contact, ContactDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return contactDto;
	}

	@Override
	public ContactDTO getContact(int cid) {
		Assert.notNull(cid, "Cid can't be null");
		Contact contact = contactDao.getContact(cid);
		ContactDTO contactDto = null;
		if (contact != null) {
			log.info("Contact is added with id:{}", contact.getCid());
			contactDto = modelMapper.map(contact, ContactDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return contactDto;
	}

	@Override
	public AddressDTO addAddress(AddressDTO addressDto) {
		Assert.notNull(addressDto, "Contact object can't be null");
		Assert.notNull(addressDto.getCity(), "City can't be null or empty");
		Assert.notNull(addressDto.getState(), "State can't be null or empty");
		Address address = modelMapper.map(addressDto, Address.class);
		address = contactDao.addAddress(address);
		if (address != null) {
			log.info("Address is added with id:{}", address.getCid());
			addressDto = modelMapper.map(address, AddressDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return addressDto;
	}

	@Override
	public List<AddressDTO> searchByCity(String str) {
		Assert.notNull(str, "Search string can't be null");
		List<Address> address = contactDao.searchByCity(str);

		List<AddressDTO> addressDTOs = address.stream().map(adr -> modelMapper.map(adr, AddressDTO.class))
				.collect(Collectors.toList());
		return addressDTOs;
	}

	@Override
	public boolean deleteAddress(int aid) {
		Assert.notNull(aid, "Cid can't be null");
		return contactDao.deleteAddress(aid);
	}

	@Override
	public AddressDTO updateAddress(AddressDTO addressDto) {
		Assert.notNull(addressDto, "Contact object can't be null");
		Assert.notNull(addressDto.getCity(), "City can't be null or empty");
		Assert.notNull(addressDto.getState(), "State can't be null or empty");
		Address address = modelMapper.map(addressDto, Address.class);
		address = contactDao.updateAddress(address);
		if (address != null) {
			log.info("Address is added with id:{}", address.getCid());
			addressDto = modelMapper.map(address, AddressDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return addressDto;
	}

	@Override
	public AddressDTO getAddress(int aid) {
		Assert.notNull(aid, "Aid can't be null");
		Address address = contactDao.getAddress(aid);
		AddressDTO addressDto = null;
		if (address != null) {
			log.info("Contact is added with id:{}", address.getAid());
			addressDto = modelMapper.map(address, AddressDTO.class);
		} else {
			log.error("When user trying to add contact, It Couldn't be add");
		}
		return addressDto;
	}

}
