package com.storemanager.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sm_category")
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_Id;

//	@Id
//	@Column(name = "CATEGORY_NAME", nullable = false)
	private String categoryName;

	private String description;

	@OneToMany(mappedBy = "categories")
	private List<Items> ItemList;

//	@JoinColumn
//	private String category_Name;

//	@CreationTimestamp  
//	private LocalDateTime createdAt;
//	private LocalDateTime updatedAt;
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

	public List<Items> getItemList() {
		return ItemList;
	}

	public void setItemList(List<Items> itemList) {
		ItemList = itemList;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Categories [category_Id=" + category_Id + ", categoryName=" + categoryName + ", description="
				+ description + ", ItemList=" + ItemList + ", isDeleted=" + isDeleted + "]";
	}

	

}