package com.shaping.code.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

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

	@Autowired
	private EntityManager entityManager;

	@Test
	public void createUser() {

		Role role = entityManager.find(Role.class, 2l);
		User user = new User();
		user.setFirstName("Bridget");
		user.setLastName("Smith");
		user.setEmail("bridgetsmith@gmail.com");
		user.setPassword("annieHector");
		user.setEnabled(false);
		user.addRole(role);
		User saveUser = this.user.save(user);
		assertThat(saveUser.getId()).isGreaterThan(0);

	}

	@Test
	public void createUserWithMoreRole() {

		Role asistance = entityManager.find(Role.class, 5l);
		Role courier = entityManager.find(Role.class, 4l);
		Role vendor = new Role(2l);
		Role admin = new Role(1l);
		Role admin2 = new Role(1l);

		User user = new User();
		user.setFirstName("Juli");
		user.setLastName("Smith");
		user.setEmail("julismith@gmail.com");
		user.setPassword("annieHector");
		user.setEnabled(true);
//		user.addRole(asistance);
//		user.addRole(admin);//here you can see error will not raise because of same reference will store here admin
//		user.addRole(admin);
		
		user.addRole(asistance);
		user.addRole(admin);
		user.addRole(admin2);//in this case error will be raise because of different object are there so handle this kind of case use hascode(),equals()
		User saveUser = this.user.save(user);
		assertThat(saveUser.getId()).isGreaterThan(0);

	}

	@Test
	public void listAllUsers() {

		List<User> users = (List<User>) user.findAll();

		users.forEach(usr -> System.out.println(usr.getFirstName()));
	}

}
