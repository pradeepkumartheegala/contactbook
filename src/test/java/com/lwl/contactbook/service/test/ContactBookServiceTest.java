package com.lwl.contactbook.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lwl.contactbook.dto.AddressDTO;
import com.lwl.contactbook.dto.ContactDTO;
import com.lwl.contactbook.dto.ContactWithAddressDTO;
import com.lwl.contactbook.service.ContactBookServiceImpl;

@SpringBootTest
public class ContactBookServiceTest {

	@Autowired
	private ContactBookServiceImpl contactBookService;

	@BeforeEach
	public void before() {
		contactBookService.addContact(new ContactDTO(1, "pradeep", "pradeep@gmail.com", "1234", null));
		contactBookService.addContact(new ContactDTO(2, "praveen", "praveen@gmail.com", "1234567890", null));

		contactBookService.addAddress(new AddressDTO(1, "Hyd", "TS"), 1);
	}

	@Test
	public void getAllContacts() {
		List<ContactWithAddressDTO> actual = contactBookService.getAllContacts();
		assertEquals("pradeep", actual.get(0).getName());
	}

	@Test
	public void search() {
		List<AddressDTO> actual = contactBookService.searchByCity("Hyd");
		assertEquals(1, actual.size());
	}

	@Test
	public void addContact() {
		ContactDTO expected = new ContactDTO(3, "santhosh", "santhosh@gmail.com", "94877565", null);
		ContactDTO actual = contactBookService.addContact(expected);
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void deleteContact() {
		boolean actual = contactBookService.deleteContact(1);
		assertEquals(true, actual);
	}

	@Test
	public void updateContact() {
		ContactDTO expected = new ContactDTO(1, "santhosh", "santhosh@gmail.com", "94877565", null);
		ContactDTO actual = contactBookService.updateContact(expected);
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void getContact() {
		ContactDTO actual = contactBookService.getContact(2);
		assertEquals("praveen", actual.getName());
	}

	public void addAddress() {
		AddressDTO expected = new AddressDTO(2, "Kansas", "KS");
		AddressDTO actual = contactBookService.addAddress(expected, 2);
		assertEquals(expected, actual);
	}

	public void searchByCity(String str) {
		List<AddressDTO> actual = contactBookService.searchByCity("Hyd");
		assertEquals(1, actual.size());
	}

	public void deleteAddress() {
		boolean actual = contactBookService.deleteAddress(1);
		assertEquals(true, actual);
	}

	public void updateAddress() {
		AddressDTO expected = new AddressDTO(1, "Kansas", "KS");
		AddressDTO actual = contactBookService.updateAddress(expected);
		assertEquals(expected, actual);
	}

	public void getAddress() {
		ContactDTO actual = contactBookService.getContact(1);
		assertEquals("Pradeep", actual.getName());
	}

}
