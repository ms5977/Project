package com.blog.mains.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RegUsers")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@Column(length = 10000)
	private String about;
	
	
	@ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name="userId",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="roleId",referencedColumnName = "id")
			)
	Set<Role>roles=new HashSet<Role>();
}
