package com.lwl.contactbook.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String name;
	private String email;
	private String mobile;
//	@OneToOne
//	@JoinColumn(name = "aid")
	private Address address;
}