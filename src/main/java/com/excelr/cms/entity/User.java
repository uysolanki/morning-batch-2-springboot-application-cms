package com.excelr.cms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userid;
		private String username;
		private String password;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(
		           name = "users_roles",
		           joinColumns = @JoinColumn(name = "fkuser_id"),
		           inverseJoinColumns = @JoinColumn(name = "fkrole_id")
		           )
		List<Role> roles;
}
