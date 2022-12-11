package com.shaping.code.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.shaping.entity.Role;
import com.shaping.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		User saveUser = this.userRepo.save(user);
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
		user.setFirstName("Ujalla");
		user.setLastName("khan");
		user.setEmail("jalla@gmail.com");
		user.setPassword("annieHector");
		user.setEnabled(true);
		user.addRole(asistance);
		user.addRole(admin);// here you can see error will not raise because of same reference will store
							// here admin
		user.addRole(admin);

		user.addRole(asistance);
		user.addRole(admin);
		user.addRole(admin2);// in this case error will be raise because of different object are there so
								// handle this kind of case use hascode(),equals()
		User saveUser = this.userRepo.save(user);
		assertThat(saveUser.getId()).isGreaterThan(0);

	}

	@Test
	public void listAllUsers() {

		List<User> users = (List<User>) userRepo.findAll();

		users.forEach(usr -> System.out.println(usr.getFirstName()));
	}

	@Test
	public void listByUserId() {

		User userId = entityManager.find(User.class, 9l);

		User user = this.userRepo.findById(userId.getId()).get();
		System.out.println(user);

	}

	@Test
	public void updateUserDetails() {

		User userName = userRepo.findById(3l).get();
		userName.setEnabled(true);
		userName.setEmail("nicsmith@gmail.com");

		userRepo.save(userName);

	}

	@Test
	public void updateUserRole() {

		User userJuli = userRepo.findById(9l).get();
		Role roleAdmin = new Role(1l);

		userJuli.getRole().remove(roleAdmin);
		Role roleEditor = new Role(3l);
		userJuli.addRole(roleEditor);
		userRepo.save(userJuli);
	}

	@Test
	public void removeUser() {

		/*
		 */
		userRepo.deleteById(11l);
	}

	@Test
	public void createPasswordEncoder() {

		final String ROW_PASSWORD = "annieHector";

		final String CYPHER = passwordEncoder.encode(ROW_PASSWORD);

		boolean match = passwordEncoder.matches(ROW_PASSWORD, CYPHER);
		System.out.println(match);

	}

	
	@Test
	public void findByEmail() {
		
		String email ="khanshahrukhbsc@gmail.com";
		User user =  userRepo.findByEmail(email);
		assertThat(user).isNotNull();

	}

}
