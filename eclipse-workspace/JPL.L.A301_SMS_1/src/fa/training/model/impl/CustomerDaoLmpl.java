package fa.training.model.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.model.invisible.CustomerDao;
import fa.training.query.SQLCommand;
import fa.training.utils.DBUtils;
/**
 * 
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class CustomerDaoLmpl implements CustomerDao {
	private Connection connection;
	private ResultSet results = null;
	private Statement statement = null;
	private CallableStatement callableStatement = null;

	@Override
	public List<Customer> getAllCustomer() throws Exception {
		List<Customer> listCustomers = new ArrayList<Customer>();
		Customer customer = null;
		int i;
		try {
			connection = DBUtils.getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(SQLCommand.getAllCustomer);
			while (results.next()) {
				i = 1;
				customer = new Customer();
				customer.setCustomerId(results.getInt(i++));
				customer.setCustomerName(results.getString(i++));
				listCustomers.add(customer);

			}
		} catch (SQLException e) {
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
		return listCustomers;
	}

	@Override
	public int addCustomer(Customer customer) throws Exception {
		int checkAdd = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			callableStatement = connection.prepareCall(SQLCommand.addCustomer);
			int i = 1;
			callableStatement.setInt(i++, customer.getCustomerId());
			callableStatement.setString(i++, customer.getCustomerName());
			checkAdd = callableStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return checkAdd;
	}

	@Override
	public int updateCustomer(Customer customer) throws Exception {
		int checkUpdateCustomer = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			callableStatement = connection.prepareCall(SQLCommand.updateCustomer);
			int i = 1;
			callableStatement.setInt(i++, customer.getCustomerId());
			callableStatement.setString(i++, customer.getCustomerName());
			checkUpdateCustomer = callableStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return checkUpdateCustomer;
	}

	@Override
	public int deleteCustomer(int customerId) throws Exception {
		int checkDelete = 0;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			callableStatement = connection.prepareCall(SQLCommand.deleteCustomer);
			int i = 1;
			callableStatement.setInt(i++, customerId);
			checkDelete = callableStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return checkDelete;
	}

	@Override
	public List<Integer> getAllCustomerId() throws Exception {
		List<Integer> listCustomerId = new ArrayList<Integer>();
		int customerId;
		try {
			connection = DBUtils.getConnection();
			statement = connection.createStatement();
			results = statement.executeQuery(SQLCommand.getAllCustomerById);
			while (results.next()) {
				customerId = results.getInt("customer_id");
				listCustomerId.add(customerId);
			}
		} catch (SQLException e) {
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
		return listCustomerId;
	}

}
