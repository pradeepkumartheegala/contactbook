package com.lwl.contactbook.service;

import java.util.List;

import com.lwl.contactbook.dto.AddressDTO;
import com.lwl.contactbook.dto.ContactDTO;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

public interface ContactBookService {

	public List<ContactWithAddressDTO> getAllContacts();

	public List<ContactWithAddressDTO> search(String str);

	public ContactDTO addContact(ContactDTO contactDto);

	public boolean deleteContact(int cid);

	public ContactDTO updateContact(ContactDTO contact);

	public ContactDTO getContact(int cid);

	public AddressDTO addAddress(AddressDTO address, int cid);

	public List<AddressDTO> searchByCity(String str);

	public boolean deleteAddress(int aid);

	public AddressDTO updateAddress(AddressDTO adddress);

	public AddressDTO getAddress(int aid);
}