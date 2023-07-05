package com.storemanager.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storemanager.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("SELECT a FROM Admin a WHERE a.isDeleted = ?1")
	List<Admin> findAllByIsDeleted(boolean isDeleted);
	
	@Query("SELECT a FROM Admin a WHERE a.email = ?1 AND a.isDeleted = 'false'")
	List<Admin> findByEmail(String email);

	@Query("SELECT a FROM Admin a WHERE a.contact = ?1 AND a.isDeleted = 'false'")
	List<Admin> findByContact(Long contact);

	@Query("SELECT a FROM Admin a WHERE a.contact = ?1 AND a.isDeleted = 'false'")
	Optional<Admin> getByEmailAndPassword(String email, String password);
	
	
	
}
