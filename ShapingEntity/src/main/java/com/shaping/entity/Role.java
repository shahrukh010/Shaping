package com.shaping.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	private Long id;
	@Column(length = 30, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	public Role() {
		super();
	}

	public Role(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public Role(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

//****************************************************************************************************

	/**
	 * Why do We need Equals and hashcode for Role? I understand the concept of
	 * equal and hashcode completely but We are using Set<Role> in User class which
	 * itself is unique and make sure it doesnt have two same object. So to test
	 * this here what I did.
	 * 
	 * User userKunjan = new User("kunjan@gmail.com","kkapadia","Kunjan","Kapadia");
	 * 
	 * Role roleEditor = new Role(3);
	 * 
	 * Role roleAssistant = new Role(5);
	 * 
	 * 
	 * 
	 * userKunjan.addRole(roleEditor);
	 * 
	 * userKunjan.addRole(roleAssistant);
	 * 
	 * userKunjan.addRole(roleAssistant);
	 * 
	 * 
	 * 
	 * User savedUser = repo.save(userKunjan);
	 * 
	 * 
	 * 
	 * assertThat(savedUser.getId()).isGreaterThan(0);
	 * 
	 * 
	 * 
	 * I run this code to check in DB and DB has assigned two role only to this user
	 * even though I have duplicated roleAssistant on User object.
	 * 
	 * 
	 * 
	 * Can you please explain the use case when I need equal and hashcode on Role
	 * Object?
	 * 
	 * 
	 * ans: You don't see the difference because you add the same object reference
	 * roleAssistant, which the last one will be ignored.
	 * 
	 * Try this code:
	 * 
	 * Role roleEditor = new Role(3); Role roleAssistant1 = new Role(5); Role
	 * roleAssistant2 = new Role(5);
	 * 
	 * userKunjan.addRole(roleEditor); userKunjan.addRole(roleAssistant1);
	 * userKunjan.addRole(roleAssistant2);
	 */

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}

//****************************************************************************************************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
