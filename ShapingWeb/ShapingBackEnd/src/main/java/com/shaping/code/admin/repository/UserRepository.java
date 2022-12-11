package com.shaping.code.admin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shaping.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	// JPA Query
	@Query("Select u from User u where u.email=:email")
	public User findByEmail(@Param("email") String email);

	@Query("Select count(*)  from User  where id=:id ")
	public Long countById(long id);
}
