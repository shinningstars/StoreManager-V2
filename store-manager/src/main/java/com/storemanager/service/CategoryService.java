package com.storemanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanager.constants.Constants;
import com.storemanager.entity.Categories;
import com.storemanager.entity.Items;
import com.storemanager.payload.CategoryCreationPayload;
import com.storemanager.payload.CategoryUpdatePayload;
import com.storemanager.payload.ErrorDetails;
import com.storemanager.payload.ItemCreationPayload;
import com.storemanager.payload.ItemUpdatePayload;
import com.storemanager.payload.ResponseDetails;
import com.storemanager.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<Object> createCategory(CategoryCreationPayload categoryPayload) {

		if (categoryPayload.getCategoryName() == null || categoryPayload.getCategoryName().isEmpty()
				|| categoryPayload.getCategoryName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-003", Constants.ITEM_NAME_CANNOT_BE_EMPTY));
		}
		Categories category = getCategory(categoryPayload);
		return ResponseEntity.ok(categoryRepository.save(category));
	}

	private Categories getCategory(CategoryCreationPayload categoryPayload) {

		Categories cat = new Categories();

		cat.setCategoryName(categoryPayload.getCategoryName());
		cat.setDescription(categoryPayload.getDescription());

		return cat;
	}

	public ResponseEntity<Object> updateCategory(CategoryUpdatePayload categoryUpdatePayload) {
		Categories category = new Categories();
		category.setCategory_Id(categoryUpdatePayload.getCategory_Id());
		category.setCategoryName(categoryUpdatePayload.getCategoryName());
		category.setDescription(categoryUpdatePayload.getDescription());
		category.setDeleted(categoryUpdatePayload.isDeleted());
		return ResponseEntity.ok(categoryRepository.save(category));
	}
	public ResponseEntity<Object> deleteCategory(Long category_Id) {
		Optional<Categories> response = categoryRepository.findById(category_Id);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-005", "Category with given CategoryId = '" + category_Id  + "' not found on the System"));

		} 
		Categories category = response.get();
		category.setDeleted(true);
		categoryRepository.save(category);
		return ResponseEntity.ok(new ResponseDetails("Category with Id = '" + category_Id + "' Deleted Sucessfully."));
	}
	
	public ResponseEntity<Object> getCategory(Long category_Id) {
		Optional<Categories> response = categoryRepository.findById(category_Id);
		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-006", "Category with given CategoryId = '" + category_Id + "' not found on the System"));
		}
		return ResponseEntity.ok(categoryRepository.save(response.get()));
	}

	public ResponseEntity<Object> getCategories() {
		List<Categories> Cat = categoryRepository.findAllByIsDeleted(false);
		return ResponseEntity.ok(Cat);
	}	
	
	
}
