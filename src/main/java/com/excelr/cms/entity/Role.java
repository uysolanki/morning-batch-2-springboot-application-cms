package com.excelr.cms.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Role {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int roleid;
		private String rolename;
		
		@ManyToMany(mappedBy = "roles")
		List<User> users;
		
}
