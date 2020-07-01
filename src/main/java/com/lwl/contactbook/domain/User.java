package com.lwl.contactbook.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class User {

	@Id
//	@GeneratedValue
	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String roles;
}
