package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
//import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO implements Dao<OrderItem> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("OrdersID");
		Long itemID = resultSet.getLong("itemID");
		int numItems = resultSet.getInt("numItems");

		return new OrderItem(orderID, itemID, numItems);
	}

	/**
	 * Reads the orderItems table.
	 * 
	 * @return A list of Items
	 */
	public List<OrderItem> readAll(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items WHERE OrdersID = ?");) {
			statement.setLong(1, order.getId());
			try (ResultSet resultSet = statement.executeQuery();) {
				List<OrderItem> ItemOrders = new ArrayList<>();
				while (resultSet.next()) {
					ItemOrders.add(modelFromResultSet(resultSet));
				}
				return ItemOrders;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrderItem readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items ORDER BY OrdersID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an entry in the order items table that links an order to an item. 
	 * 
	 */
	@Override
	public OrderItem create(OrderItem orderitems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(OrdersID, itemID, numItems) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderitems.getOrderID());
			statement.setLong(2, orderitems.getItemID());
			statement.setInt(3, orderitems.getNumItems());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public long readNumItems(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT numItems FROM order_items WHERE OrdersID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return resultSet.getLong(1);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public long readItemID(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT itemID FROM order_items WHERE OrdersID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return resultSet.getLong(1);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public double readCost(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT itemCost FROM items WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return resultSet.getDouble(1);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Deletes a item from a order in the order items table
	 * 
	 *
	 */
	@Override
	public int delete(long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE OrdersID = ?");) {
			statement.setLong(1, orderID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public Order update(long orderID, int numItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE order_items SET numItems = ? WHERE OrdersID = ?");) {
			statement.setInt(1, numItems);
			statement.setDouble(2, orderID);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<OrderItem> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem update(OrderItem t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
