package com.lwl.contactbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class ContactWithAddressDTO {

	private int cid;
	private String name;
	private String email;
	private String mobile;
	private String city;
	private String state;

}
