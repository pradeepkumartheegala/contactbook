package com.lwl.contactbook.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lwl.contactbook.dao.ContactBookDaoImpl;
import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

@SpringBootTest
public class ContactBookDaoTest {

	@Autowired
	private ContactBookDaoImpl contactDao;

	@BeforeEach
	public void before() {
		contactDao.addContact(new Contact(1, "pradeep", "pradeep@gmail.com", "1234",null));
		contactDao.addContact(new Contact(2, "praveen", "praveen@gmail.com", "1234567890",null));

		contactDao.addAddress(new Address(1, "Hyd", "TS"), 1);
	}

	@Test
	public void getAllContacts() {
		List<ContactWithAddressDTO> actual = contactDao.getAllContacts();
		System.out.println(actual);
		assertEquals("pradeep", actual.get(0).getName());

	}

	@Test
	public void addContact() {
		Contact expected = new Contact(3, "prdp", "prdp@gmail.com", "123456789",null);
		Contact actual = contactDao.addContact(expected);
		assertEquals(expected.getCid(), actual.getCid());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void deleteContact() {
		boolean actual = contactDao.deleteContact(1);
		assertEquals(true, actual);
	}

	@Test
	public void updateContact() {
		Contact expected = new Contact(1, "praveen", "praveen@gmail.com", "1234567",null);
		Contact actual = contactDao.updateContact(expected);
		assertEquals(expected.getName(), actual.getName());

	}

	@Test
	public void getContact() {
		Contact actual = contactDao.getContact(1);
		assertEquals("praveen", actual.getName());
	}

	@Test
	public void addAddress() {
		Address expected = new Address(2, "Kmm", "TS");
		Address actual = contactDao.addAddress(expected,2);
		assertEquals(expected.getAid(), actual.getAid());
	}

	@Test
	public void deleteAddress() {
		boolean actual = contactDao.deleteAddress(1);
		assertEquals(true, actual);

	}

	@Test
	public void updateAddress() {
		Address expected = new Address(2, "Nlg", "TS");
		Address actual = contactDao.updateAddress(expected);
		assertEquals(expected.getAid(), actual.getAid());

	}

	@Test
	public void getAddress() {
		Address actual = contactDao.getAddress(1);
		assertEquals("Hyd", actual.getCity());

	}

	@Test
	public void search() {
		List<ContactWithAddressDTO> actual = contactDao.search("Hyd");
		assertEquals(1, actual.size());
	}

	@Test
	public void searchByCity() {
		List<Address> actual = contactDao.searchByCity("Hyd");
		assertEquals(1, actual.size());
	}

}
