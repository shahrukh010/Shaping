package com.shaping.code.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shaping.code.admin.user.RoleRepository;
import com.shaping.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository role;

	@Test
	public void createRole() {

		/*
		 * Role admin = new Role(1l,"Admin","Manage Administration"); Role vendor = new
		 * Role(2l,"Vendor","Manage Product"); Role editor = new
		 * Role(3l,"Editor","Manage Product and Shiping"); Role courier = new
		 * Role(4l,"Courier","Manage Product Track and Delivery");
		 * 
		 * Role saveAdmin = role.save(admin); Role saveVendor = role.save(vendor); Role
		 * saveEditor = role.save(editor); Role saveCourier = role.save(courier);
		 * 
		 * role.saveAll(List.of(saveAdmin,saveVendor,saveEditor,saveCourier));
		 */

		Role role = new Role();
		role.setId(5l);
		role.setName("Assistance");
		role.setDescription("Manage product review and feedback");
		this.role.save(role);

	}
	
	

}
