package com.shaping.code.admin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shaping.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	// JPA Query
	@Query("Select u from User u where u.email=:email")
	public User findByEmail(@Param("email") String email);

	@Query("Select count(*)  from User  where id=:id ")
	public Long countById(long id);
	
	
	@Query("UPDATE User u SET u.enabled=?2 WHERE u.id=?1")
	@Modifying
	@Transactional//annotate with @Transactional otherwise it will give error
	public void updateUserStatus(long id,boolean status);
}
