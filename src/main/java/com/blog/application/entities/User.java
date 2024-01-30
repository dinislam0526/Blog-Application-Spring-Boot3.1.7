package com.blog.application.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name",nullable = false,length = 100)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

}
