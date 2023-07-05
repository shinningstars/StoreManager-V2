package com.storemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.storemanager.entity.Admin;
import com.storemanager.entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long>{
	
	@Query("SELECT a FROM Items a WHERE a.isDeleted = ?1")
	List<Items> findAllByIsDeleted(boolean isDeleted);
	
//	@Query("SELECT a FROM Admin a WHERE a.email = ?1 AND a.isDeleted = 'false'")
//	List<Items> findByEmail(String email);
//
//	@Query("SELECT a FROM Admin a WHERE a.contact = ?1 AND a.isDeleted = 'false'")
//	List<Items> findByContact(Long contact);
//
//	@Query("SELECT a FROM Admin a WHERE a.contact = ?1 AND a.isDeleted = 'false'")
//	Optional<Items> getByEmailAndPassword(String email, String password);
	
}
