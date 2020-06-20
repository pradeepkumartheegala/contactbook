package com.lwl.contactbook.dao;

import java.util.List;

import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

public interface ContactBookDao {

	public List<ContactWithAddressDTO> getAllContacts();

	public List<ContactWithAddressDTO> search(String str);

	public Contact addContact(Contact contact);

	public boolean deleteContact(int cid);

	public Contact updateContact(Contact contact);

	public Contact getContact(int cid);

	public Address addAddress(Address address, int cid);

	public List<Address> searchByCity(String str);

	public boolean deleteAddress(int aid);

	public Address updateAddress(Address adddress);

	public Address getAddress(int aid);

	public String findByMobile(String mobile);
}
