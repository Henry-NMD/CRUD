package fa.training.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Order;
import fa.training.model.invisible.OrderDao;
import fa.training.query.SQLCommand;
import fa.training.utils.DBUtils;

public class OrderDaoLmpl implements OrderDao {
	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;
	private Statement statement = null;
	// private Order orders = null;

	@Override
	public List<Order> getAllOrderByCustomerId(int customerId) throws Exception {
		List<Order> listOrders = new ArrayList<Order>();
		Order orders = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.getAllOrderByCustomerId);
			int i = 1;
			preparedStatement.setInt(i++, customerId);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				orders = new Order();
				orders.setOrderId(results.getInt("order_id"));
				orders.setOrderDate(results.getDate("order_date"));
				orders.setTotal(results.getInt("total"));
				orders.setCustomerId(results.getInt("customer_id"));
				orders.setEmployeeId(results.getInt("employee_id"));
				listOrders.add(orders);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (results != null) {
					results.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return listOrders;
	}

	@Override
	public int addOrder(Order order) throws Exception {
		int i = 1;
		int checkAdd = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQLCommand.addOrder);
			preparedStatement.setInt(i++, order.getOrderId());
			preparedStatement.setDate(i++, order.getOrderDate());
			preparedStatement.setInt(i++, order.getCustomerId());
			preparedStatement.setInt(i++, 1);
			preparedStatement.setInt(i++, 0);
			checkAdd = preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return checkAdd;
	}

	@Override
	public int updateOrderTotal(int orderId, double orderTotal) throws Exception {
		int i = 1;
		int statusUpdate = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQLCommand.updateOrderTotal);
			preparedStatement.setDouble(i++, orderTotal);
			preparedStatement.setInt(i++, orderId);
			statusUpdate = preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return statusUpdate;
	}

	@Override
	public Double computeOrderTotal(int orderId) {
		return null;
	}

	/**
	 * get value search order id by value
	 * 
	 * @param id
	 * @return order id
	 */
	public Integer listOrderByOrderId(int id) {
		Integer orderId = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT order_id FROM orders Where Order_id = ?");
			preparedStatement.setInt(1, id);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				orderId = results.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return orderId;
	}

	/**
	 * delete order to DB
	 * 
	 * @param orderId
	 * @return status = 1 if deleted else delete fail
	 */
	public int deleteOrder(int orderId) {
		int i = 1;
		int statusDeleteItem = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("DELETE FROM orders Where order_id = ?");
			preparedStatement.setInt(i++, orderId);
			statusDeleteItem = preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return statusDeleteItem;
	}

	/**
	 * get total by order id
	 * 
	 * @param list order
	 * @return status = 1 if deleted else delete fail
	 */
	public List<Order> getTotalByOrderId(int orderId) {
		List<Order> listOrder = new ArrayList<Order>();
		Order order = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT order_id , total FROM orders Where order_id = ?");
			preparedStatement.setInt(1, orderId);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				order = new Order();
				order.setOrderId(results.getInt(1));
				order.setTotal(results.getDouble(2));
				listOrder.add(order);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listOrder;
	}

}
