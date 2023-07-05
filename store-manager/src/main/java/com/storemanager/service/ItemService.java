package com.storemanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanager.constants.Constants;
import com.storemanager.entity.Items;
import com.storemanager.payload.ErrorDetails;
import com.storemanager.payload.ItemCreationPayload;
import com.storemanager.payload.ItemUpdatePayload;                                                              
import com.storemanager.payload.ResponseDetails;
import com.storemanager.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public ResponseEntity<Object> createItem(ItemCreationPayload itemPayload) {

		if (itemPayload.getItem_Name() == null || itemPayload.getItem_Name().isEmpty()
				|| itemPayload.getItem_Name().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDetails("SM-003", Constants.ITEM_NAME_CANNOT_BE_EMPTY));
		}
		Items item = getItem(itemPayload);
		return ResponseEntity.ok(itemRepository.save(item));
	}

	private Items getItem(ItemCreationPayload itemPayload) {

		Items item = new Items();

		item.setItem_Name(itemPayload.getItem_Name());
		item.setPrice(itemPayload.getPrice());
		item.setCategories(itemPayload.getCategories());

		return item;
	}

	public ResponseEntity<Object> updateItem(ItemUpdatePayload itemUpdatePayload) {
		Items item = new Items();
		item.setItem_Id(itemUpdatePayload.getItem_Id());
		item.setItem_Name(itemUpdatePayload.getItem_Name());
		item.setPrice(itemUpdatePayload.getPrice());
		item.setCategories(itemUpdatePayload.getCategories());
		item.setCreatedAt(itemUpdatePayload.getCreatedAt());
		item.setUpdatedAt(LocalDateTime.now());
		item.setDeleted(itemUpdatePayload.isDeleted());
		
//		item.setUpdatedAt(LocalDateTime.now());
		return ResponseEntity.ok(itemRepository.save(item));
	}
	public ResponseEntity<Object> deleteItem(Long item_Id) {
		Optional<Items> response = itemRepository.findById(item_Id);

		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-003", "Item with given ItemId = '" + item_Id  + "' not found on the System"));

		} 
		Items item = response.get();
		item.setDeleted(true);
		itemRepository.save(item);
		return ResponseEntity.ok(new ResponseDetails("Item with Id = '" + item_Id + "' Deleted Sucessfully."));
	}
	
	public ResponseEntity<Object> getItem(Long item_Id) {
		Optional<Items> response = itemRepository.findById(item_Id);
		if (!response.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ErrorDetails("SM-004", "Item with given ItemId = '" + item_Id + "' not found on the System"));
		}
		return ResponseEntity.ok(itemRepository.save(response.get()));
	}

	public ResponseEntity<Object> getItems() {
		List<Items> items = itemRepository.findAllByIsDeleted(false);
		return ResponseEntity.ok(items);
	}
}
