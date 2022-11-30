package com.shaping.code.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shaping.entity.Role;
import com.shaping.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository user;

	@Test
	public void createUser() {

		Role role = new Role(1l);
		User user = new User();
		user.setFirstName("shahrukh");
		user.setLastName("khan");
		user.setEmail("khanshahrukhbsc@gmail.com");
		user.setPassword("annieHector");
		user.setEnabled(false);
		user.addRole(role);
		this.user.save(user);

	}
}
