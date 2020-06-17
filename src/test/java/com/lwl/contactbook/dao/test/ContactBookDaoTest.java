package com.lwl.contactbook.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.lwl.contactbook.dao.ContactBookDaoImpl;
import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

@SpringBootTest
public class ContactBookDaoTest {

	@Autowired
	private ContactBookDaoImpl contactDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String script = "data.sql";
	private static final String delete = "delete.sql";

	@BeforeEach
	public void before() {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			ScriptUtils.executeSqlScript(con, new ClassPathResource(script));
			con.close();
		} catch (ScriptException | SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void after() {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();

			ScriptUtils.executeSqlScript(con, new ClassPathResource(delete));

			con.close();
		} catch (ScriptException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllContacts() {
		List<ContactWithAddressDTO> actual = contactDao.getAllContacts();
		assertEquals("Pradeep", actual.get(0).getName());
		System.out.println("Done GetAllContacts");

	}

	@Test
	public void addContact() {
		Contact expected = new Contact(6, "pradeep", "pradeep@gmail.com", "353747646446");
		Contact actual = contactDao.addContact(expected);
		assertEquals(expected, actual);
		System.out.println("Done addContact");

	}

	@Test
	public void deleteContact() {
		boolean actual = contactDao.deleteContact(1);
		assertEquals(true, actual);
		System.out.println("Done deleteContact");

	}

	@Test
	public void updateContact() {
		Contact expected = new Contact(1, "Pradeep T", "pradeep@gmail.com", "353747646446");
		Contact actual = contactDao.updateContact(expected);
		assertEquals(expected, actual);
		System.out.println("Done updateContacts");

	}

	@Test
	public void getContact() {
		Contact actual = contactDao.getContact(1);
		assertEquals("Pradeep", actual.getName());
		assertEquals("pradeep@gmail.com", actual.getEmail());
		System.out.println("Done getContacts");

	}

	@Test
	public void addAddress() {
		Address expected = new Address(6, "Kansas", "KS", 2);
		Address actual = contactDao.addAddress(expected);
		assertEquals(expected, actual);
		System.out.println("Done addAddress");

	}

	@Test
	public void deleteAddress() {
		boolean actual = contactDao.deleteAddress(1);
		assertEquals(true, actual);
		System.out.println("Done deleteAddress");

	}

	@Test
	public void updateAddress() {
		Address expected = new Address(1, "Kansas", "KS", 2);
		Address actual = contactDao.updateAddress(expected);
		assertEquals(expected, actual);
		System.out.println("Done UpdateAddress");

	}

	@Test
	public void getAddress() {
		Address actual = contactDao.getAddress(1);
		assertEquals("hyd", actual.getCity());
		System.out.println("Done getAddress");

	}

	@Test
	public void search() {
		System.out.println("In Search");
		List<ContactWithAddressDTO> actual = contactDao.search("hyd");
		actual.stream().forEach(e->System.out.println(e.getCid()));
		System.out.println(actual.get(0).getCity());
		assertEquals(2, actual.size());
	}

//	@Test
//	public void searchByCity() {
//
//	}

}
