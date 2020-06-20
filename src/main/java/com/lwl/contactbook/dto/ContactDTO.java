package com.lwl.contactbook.dto;

import com.lwl.contactbook.domain.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactDTO {
	private int cid;
	private String name;
	private String email;
	private String mobile;
	private Address address;
}
