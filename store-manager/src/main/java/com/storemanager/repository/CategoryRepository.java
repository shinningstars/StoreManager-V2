package com.storemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storemanager.entity.Categories;
import com.storemanager.entity.Items;
import com.storemanager.payload.Category;

public interface CategoryRepository extends JpaRepository<Categories,Long>{
	
	@Query("SELECT a FROM Items a WHERE a.isDeleted = ?1")
	List<Categories> findAllByIsDeleted(boolean isDeleted);
}
