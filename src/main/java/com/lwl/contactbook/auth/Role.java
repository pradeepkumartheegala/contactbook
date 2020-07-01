package com.lwl.contactbook.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
//@Table(name="role")
public class Role {
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	
	public Role() {
		
	}

	public Role(String role) {
		this.role = role;
	}
	

}
