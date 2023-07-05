package com.storemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanager.payload.CategoryCreationPayload;
import com.storemanager.payload.ItemCreationPayload;
import com.storemanager.payload.ItemUpdatePayload;
import com.storemanager.service.CategoryService;
import com.storemanager.payload.CategoryUpdatePayload;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryservice;
	
	@Description(value = "Create an item")
	@PostMapping("/create")
	public ResponseEntity<Object> createItem(@RequestBody CategoryCreationPayload categoryCreationPayload) {
		return categoryservice.createCategory(categoryCreationPayload);
	} 	
	
	@Description(value = "Update an item")
	@PostMapping("/update")
	public ResponseEntity<Object> updateCategory(@RequestBody CategoryUpdatePayload categoryUpdatePayload) {
		return categoryservice.updateCategory(categoryUpdatePayload);
	}

	@Description(value = "Delete a Category")
	@PostMapping("/delete/{category_Id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable Long category_Id) {
		return categoryservice.deleteCategory(category_Id);
	}
	
	@Description(value = "This API returns a category")
	@GetMapping("/select/{category_Id}")
	public ResponseEntity<Object> getCategory(@PathVariable Long category_Id) {
		return categoryservice.getCategory(category_Id);
	}

	@Description(value = "This API returns all categories")
	@GetMapping("/get-all-category")
	public ResponseEntity<Object> getCategories() {
		return categoryservice.getCategories();
	}
}
