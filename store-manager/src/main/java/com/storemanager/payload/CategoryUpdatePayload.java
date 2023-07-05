package com.storemanager.payload;

public class CategoryUpdatePayload {

	private Long category_Id;
	private String categoryName;
	private String description;
	private boolean isDeleted = false;

	
	public Long getCategory_Id() {
		return category_Id;
	}

	public void setCategory_Id(Long category_Id) {
		this.category_Id = category_Id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "CategoryCreationPayload [category_Id=" + category_Id + ", categoryName=" + categoryName
				+ ", description=" + description + ", isDeleted=" + isDeleted + "]";
	}
}
