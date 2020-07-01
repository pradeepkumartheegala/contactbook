package com.lwl.contactbook.auth;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
//@Table(name="appuser")
public class AppUser {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "user_role",joinColumns=
//            @JoinColumn(name="u_id", referencedColumnName="id"),
//            inverseJoinColumns=
//            @JoinColumn(name="r_id", referencedColumnName="id"))
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public AppUser(String username, String password, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public AppUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public AppUser() {
	}
}
