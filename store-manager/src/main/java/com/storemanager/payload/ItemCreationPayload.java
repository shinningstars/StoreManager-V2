package com.storemanager.payload;

import java.time.LocalDateTime;

import com.storemanager.entity.Categories;

public class ItemCreationPayload {
	
	private Long item_Id;
	private String item_Name;
	private Long price;
	private Categories categories;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean isDeleted = false;
	
	
	public Long getItem_Id() {
		return item_Id;
	}
	public void setItem_Id(Long item_Id) {
		this.item_Id = item_Id;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "ItemCreationPayload [item_Id=" + item_Id + ", item_Name=" + item_Name + ", price=" + price
				+ ", categories=" + categories + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isDeleted=" + isDeleted + "]";
	}
	
	
}
