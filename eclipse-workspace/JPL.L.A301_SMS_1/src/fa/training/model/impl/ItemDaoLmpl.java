package fa.training.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.LineItem;
import fa.training.model.invisible.ItemDao;
import fa.training.query.SQLCommand;
import fa.training.utils.DBUtils;

/**
 * 
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class ItemDaoLmpl implements ItemDao {
	private Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;
	private Statement statement = null;
	// private LineItem lineItem = null;
	private LineItem lineItem = null;

	@Override
	public List<LineItem> getAllItemByOrderId(int orderId) throws Exception {
		List<LineItem> listLineItem = new ArrayList<LineItem>();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.getAllItemByOrderId);
			int i = 1;
			preparedStatement.setInt(i++, orderId);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				lineItem = new LineItem();
				lineItem.setOrderId(results.getInt("order_id"));
				lineItem.setProductId(results.getInt("product_id"));
				lineItem.setQuantity(results.getInt("quantity"));
				lineItem.setPrice(results.getDouble("price"));
				listLineItem.add(lineItem);
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listLineItem;
	}

	@Override
	public int addLineItem(LineItem item) throws Exception {
		int statusAdd = 0;
		int i = 1;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQLCommand.addLineItem);
			preparedStatement.setInt(i++, item.getOrderId());
			preparedStatement.setInt(i++, item.getProductId());
			preparedStatement.setInt(i++, item.getQuantity());
			preparedStatement.setDouble(i++, item.getPrice());
			statusAdd = preparedStatement.executeUpdate();
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
		return statusAdd;
	}

	/**
	 * get all price by product id
	 * 
	 * @param id
	 * @return price
	 * @throws Exception
	 */
	public int getPriceByProductId(int id) throws Exception {
		int price = 0;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT list_price FROM product WHERE product_id = ?");
			int i = 1;
			preparedStatement.setInt(i++, id);
			results = preparedStatement.executeQuery();
			results.next();
			price = results.getInt(1);
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return price;
	}

	/**
	 * delete line item
	 * 
	 * @param orderId
	 * @param productId
	 * @return status = 1 if deleted else delete fail
	 */
	public int deleteItem(int orderId, int productId) {
		int i = 1;
		int statusDeleteItem = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("DELETE FROM LineItem Where order_id = ? and product_id = ?");
			preparedStatement.setInt(i++, orderId);
			preparedStatement.setInt(i++, productId);
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
	

}
