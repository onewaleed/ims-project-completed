package com.qa.ims.persistence.domain;

import java.util.Objects;

public class OrderItem {

	private Long orderID;
	private Long itemID;
	private int numItems;


	public OrderItem(Long orderID, Long itemID, int numItems) {
		this.setOrderID(orderID);
		this.setItemID(itemID);
		this.setNumItems(numItems);
	}


	public Long getOrderID() {
		return orderID;
	}


	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}


	public Long getItemID() {
		return itemID;
	}


	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}


	public int getNumItems() {
		return numItems;
	}


	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}


	@Override
	public int hashCode() {
		return Objects.hash(itemID, numItems, orderID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(itemID, other.itemID) && numItems == other.numItems
				&& Objects.equals(orderID, other.orderID);
	}


	@Override
	public String toString() {
		return "OrderItem [orderID=" + orderID + ", itemID=" + itemID + ", numItems=" + numItems + "]";
	}



}
