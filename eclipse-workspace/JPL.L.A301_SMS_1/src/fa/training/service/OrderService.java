package fa.training.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.exception.DuplicateException;
import fa.training.exception.IdNotFountException;
import fa.training.model.impl.CustomerDaoLmpl;
import fa.training.model.impl.OrderDaoLmpl;
import fa.training.validator.Validator;

/**
 * get value and check value then call DAO
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class OrderService {
	private OrderDaoLmpl orderDaoLmpl = new OrderDaoLmpl();
	private CustomerDaoLmpl customerDaoLmpl = new CustomerDaoLmpl();

	/**
	 * get value from main class and check value then call getAllOrder function to
	 * DAO
	 * 
	 * @param id
	 * @return list order
	 * @throws Exception
	 */
	public List<Order> getAllOrderService(String id) throws Exception {
		List<Order> listOrderByCustomerId = null;
		int customerId = 0;
		try {
			if (Validator.inputTypeInt(id) == true) {
				throw new NumberFormatException();
			} else {
				customerId = Integer.parseInt(id);
			}
			listOrderByCustomerId = orderDaoLmpl.getAllOrderByCustomerId(customerId);

		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		}
		return listOrderByCustomerId;
	}

	/**
	 * get value from main class and check value then call addOrder function to DAO
	 * 
	 * @param id
	 * @param date
	 * @param cusId
	 * @param proId
	 * @return statusAddOrder
	 * @throws Exception
	 */
	public int addOrderService(String id, String date, String cusId) throws Exception {
		List<Customer> listCustomer = null;
		int Orderid, customerId;
		Date orderdate;
		int statusAddOrder = 0;
		int count = 0;
		Integer ids = 0;
		try {
			if (Validator.inputTypeInt(id) == true && Validator.inputTypeInt(cusId) == true) {
				throw new NumberFormatException();
			} else {
				Orderid = Integer.parseInt(id);
				customerId = Integer.parseInt(cusId);
			}
			orderdate = Validator.convertStringToDate(date);
			ids = orderDaoLmpl.listOrderByOrderId(Orderid);
			if (ids != null) {
				throw new DuplicateException();
			} else {
				listCustomer = customerDaoLmpl.getAllCustomer();
				for (int i = 0; i < listCustomer.size(); i++) {
					if (listCustomer.get(i).getCustomerId() == customerId) {
						count++;
					}
				}
				if (count == 0) {
					throw new IdNotFountException();
				} else {
					Order order = new Order(Orderid, orderdate, customerId);
					statusAddOrder = orderDaoLmpl.addOrder(order);
					if (statusAddOrder == 0) {
						throw new Exception();
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (IdNotFountException e) {
			throw new IdNotFountException("id not found");
		} catch (DuplicateException e) {
			throw new DuplicateException("id exitst");
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return statusAddOrder;
	}

	
	/**
	 * get value from main class then check value and call getTotalByOrderId function in DAO class
	 * 
	 * 
	 * @param id
	 * @return list order
	 * @throws Exception
	 */
	public List<Order> listOrderService(String id) throws Exception {
		List<Order> listOrder = null;
		int orderid = 0;
		try {
			if (Validator.inputTypeInt(id) == true) {
				throw new NumberFormatException();
			} else {
				orderid = Integer.parseInt(id);
			}
			listOrder = orderDaoLmpl.getTotalByOrderId(orderid);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return listOrder;
	}

}
