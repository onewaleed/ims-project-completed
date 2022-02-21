package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {

	private long id;
	private long customerID;

	public Order(long customerID) {
		this.setCustomerID(customerID);
	}

	public Order(long id, long customerID) {
		this.setId(id);
		this.setCustomerID(customerID);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerID, other.customerID) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerID=" + customerID + "]";
	}


}
