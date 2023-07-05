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

import com.storemanager.payload.AdminCreationPayload;
import com.storemanager.payload.AdminUpdatePayload;
import com.storemanager.payload.ItemCreationPayload;
import com.storemanager.payload.ItemUpdatePayload;
import com.storemanager.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Description(value = "Create an item")
	@PostMapping("/create")
	public ResponseEntity<Object> createItem(@RequestBody ItemCreationPayload itemPayload) {
		return itemService.createItem(itemPayload);
	} 	
	
	@Description(value = "Update an item")
	@PostMapping("/update")
	public ResponseEntity<Object> updateItem(@RequestBody ItemUpdatePayload itemUpdatePayload) {
		return itemService.updateItem(itemUpdatePayload);
	}

	@Description(value = "Delete an item")
	@PostMapping("/delete/{item_Id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable Long item_Id) {
		return itemService.deleteItem(item_Id);
	}
	
	@Description(value = "This API returns an item")
	@GetMapping("/select/{item_Id}")
	public ResponseEntity<Object> getItem(@PathVariable Long item_Id) {
		return itemService.getItem(item_Id);
	}

	@Description(value = "This API returns all items")
	@GetMapping("/get-all-items")
	public ResponseEntity<Object> getItems() {
		return itemService.getItems();
	}
	

}
