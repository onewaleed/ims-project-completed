package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long id;
	private String itemName;
	private double itemCost;

	public Item(String itemName, double itemCost) {
		this.setItemName(itemName);
		this.setItemCost(itemCost);
	}

	public Item(Long id, String itemName, double itemCost) {
		this.setId(id);
		this.setItemName(itemName);
		this.setItemCost(itemCost);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemCost, itemName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id)
				&& Double.doubleToLongBits(itemCost) == Double.doubleToLongBits(other.itemCost)
				&& Objects.equals(itemName, other.itemName);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemCost=" + itemCost + "]";
	}
}

