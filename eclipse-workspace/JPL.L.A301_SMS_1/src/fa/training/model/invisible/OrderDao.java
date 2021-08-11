package fa.training.model.invisible;

import java.util.List;

import fa.training.entities.Order;

public interface OrderDao {
	
	
	/**
	 * get all a order to database
	 * 
	 * @param customerId
	 * @return a order of customer
	 */
	List<Order> getAllOrderByCustomerId(int customerId) throws Exception;
	
	
	/**
	 * add a order to database
	 * 
	 * @param order
	 * @return boolean type
	 */
	int addOrder(Order order) throws Exception;
	
	
	/**
	 * update a order to database
	 * 
	 * @param orderId
	 * @return boolean type
	 */
	int updateOrderTotal(int orderId , double orderTotal) throws Exception;
	
	/**
	 * Compute order total
	 * 
	 * @param orderId
	 * @return list with a row containing the computed order total from the line items
	 */
	Double computeOrderTotal(int orderId);
	
}
