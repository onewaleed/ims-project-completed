package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in Item details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemsDAO orderItemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderItemsDAO orderItemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderItemDAO = orderItemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			List<OrderItem> items = orderItemDAO.readAll(order);
			LOGGER.info("order id: "+ order.getId());
			LOGGER.info("customer id: " + order.getCustomerID());
			for(OrderItem currentItem : items) {
				LOGGER.info("item id: " + currentItem.getItemID());
				LOGGER.info("number of items: " + currentItem.getNumItems());
				LOGGER.info("-------------");
			}
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer ID");
		long customerID = utils.getLong();
		LOGGER.info("Please enter the item ID");
		long itemID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		long orderID = order.getId();
		LOGGER.info("Please enter the amount of items you want");
		long numItems = utils.getLong();
		orderItemDAO.create(new OrderItem(orderID, itemID, (int)numItems));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order number
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		long id = utils.getLong();
		orderItemDAO.delete(id);
		return orderDAO.delete(id);
	}

	@Override
	public Order update() {
		LOGGER.info("Enter the id of the order you would like to update");
		long orderID = utils.getLong();
		LOGGER.info("Enter the amount of items you would like to update this order to");
		long numItems = utils.getLong();
		orderItemDAO.update(orderID, (int)numItems);
		return null;
	}

	@Override
	public void calculate() {
		LOGGER.info("Enter the id of the order you would like to calculate the costs of");
		long orderID = utils.getLong();
		long itemID = orderItemDAO.readItemID(orderID);
		long numItems = orderItemDAO.readNumItems(orderID);
		double itemPrice = orderItemDAO.readCost(itemID);
		LOGGER.info("-------------");
		LOGGER.info("The cost of the order is: £" + numItems*itemPrice);
		LOGGER.info("-------------");
	}

}
