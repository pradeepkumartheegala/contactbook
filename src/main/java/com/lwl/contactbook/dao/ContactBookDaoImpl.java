package com.lwl.contactbook.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.lwl.contactbook.domain.Address;
import com.lwl.contactbook.domain.Contact;
import com.lwl.contactbook.dto.ContactWithAddressDTO;

@Repository
public class ContactBookDaoImpl implements ContactBookDao {

	private Logger log = LoggerFactory.getLogger(ContactBookDaoImpl.class);

	private static final String CONTACT_WITH_ADDRESS = "SELECT c.cid,name,email,mobile, city,state FROM contact c, address a where c.cid=a.cid";
	private static final String SEARCH = "SELECT c.cid,name,email,mobile, city,state FROM address a inner join contact c on c.cid=a.cid and city= ?";
	private static final String ADDCONTACT = "insert into contact(name,email,mobile) values(?,?,?)";
	private static final String DELETE_CONTACT = "delete from contact where cid=?";
	private static final String UPDATE_CONTACT = "UPDATE contact SET name = ?, mobile= ?, email=? WHERE cid = ?";
	private static final String GET_CONTACT = "SELECT * FROM contact WHERE cid = ?";
	private static final String ADD_ADDRESS = "INSERT INTO address(city, state,cid) values(?,?,?)";
	private static final String SEARCH_BY_CITY = "SELECT * FROM address where city=?";
	private static final String DELETE_ADDRESS = "delete from address where aid=?";
	private static final String UPDATE_ADDRESS = "UPDATE address SET city = ?, state= ?, cid=? WHERE aid = ?";
	private static final String GET_ADDRESS = "SELECT * FROM address WHERE aid = ?";
	private static final String FIND_BY_MOBILE="select name from contact where mobile=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ContactWithAddressDTO> getAllContacts() {

		List<ContactWithAddressDTO> contactWithAddressDTOs = jdbcTemplate.query(CONTACT_WITH_ADDRESS,
				new BeanPropertyRowMapper<ContactWithAddressDTO>(ContactWithAddressDTO.class));
		log.info("Contact count: {}",
				contactWithAddressDTOs != null ? contactWithAddressDTOs.size() : contactWithAddressDTOs);
		return contactWithAddressDTOs;
	}

	@Override
	public List<ContactWithAddressDTO> search(String str) {
		List<ContactWithAddressDTO> contactWithAddressDTO = jdbcTemplate.queryForList(SEARCH,
				ContactWithAddressDTO.class, str);
		return contactWithAddressDTO;

	}

	@Override
	public Contact addContact(Contact contact) {
		int rowsChanged = jdbcTemplate.update(ADDCONTACT, contact.getName(), contact.getMobile(),
				contact.getEmail());
		if (rowsChanged != 0) {
			return contact;
		}
		return null;
	}

	@Override
	public boolean deleteContact(int cid) {

		Assert.notNull(cid,"Cid can't be null");
		int rowsChanged = jdbcTemplate.update(DELETE_CONTACT, cid);
		if (rowsChanged != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Contact updateContact(Contact contact) {
		int rowsChanged = jdbcTemplate.update(UPDATE_CONTACT, contact.getName(), contact.getMobile(),
				contact.getEmail(), contact.getCid());
		if (rowsChanged != 0) {
			log.info("Contact is updated Successfully");
			return contact;
		}
		return null;
	}

	@Override
	public Contact getContact(int cid) {
		Contact contact = jdbcTemplate.queryForObject(GET_CONTACT, new Object[] { cid },
				new BeanPropertyRowMapper<Contact>(Contact.class));
		if(contact!=null) {
			log.info("Got the Contact with: "+cid);
			return contact;
		}
		return contact;
	}

	@Override
	public Address addAddress(Address address) {
		int rowsChanged = jdbcTemplate.update(ADD_ADDRESS, address.getCity(), address.getState(),
				address.getCid());
		if (rowsChanged != 0) {
			log.info("Address is added successful");
			return address;
		}
		return null;
	}

	@Override
	public List<Address> searchByCity(String str) {

		return jdbcTemplate.queryForList(SEARCH_BY_CITY, Address.class, str);
	}

	@Override
	public boolean deleteAddress(int aid) {
		int rowsChanged = jdbcTemplate.update(DELETE_ADDRESS, aid);
		if (rowsChanged != 0) {
			log.info("Deleted the Address successfully with "+aid);
			return true;
		}
		return false;
	}

	@Override
	public Address updateAddress(Address address) {

		int rowsChanged = jdbcTemplate.update(UPDATE_ADDRESS, address.getCity(), address.getState(), address.getCid(),
				address.getAid());
		if (rowsChanged != 0) {
			log.info("Updating the Address successfully with "+address.getAid());
			return address;
		}
		return null;
	}

	@Override
	public Address getAddress(int aid) {
		Address address = jdbcTemplate.queryForObject(GET_ADDRESS, new Object[] { aid },
				new BeanPropertyRowMapper<Address>(Address.class));
		log.info("Getting the address with: "+aid);
		return address;
	}

	@Override
	public String findByMobile(String mobile) {
		String name=jdbcTemplate.queryForObject(FIND_BY_MOBILE, String.class,mobile);
		return name;
	}

}
