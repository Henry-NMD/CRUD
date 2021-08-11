package fa.training.model.invisible;

import java.util.List;

import fa.training.entities.Customer;

public interface CustomerDao {
	
	/**
	 * execute a query to get all customer from database
	 * 
	 * @return list customer
	 */
	  List<Customer> getAllCustomer() throws Exception;
	  
	  
	  /**
	   * add a customer to database
	   * 
	   * @param customer
	   * @return boolean type
	   */
	 int addCustomer(Customer customer) throws Exception; 
	 
	 
	 /**
	  * update a customer to database
	  * 
	  * @param customer
	  * @return boolean type
	  */
	 int updateCustomer(Customer customer) throws Exception;
	 
	 
	 /**
	  * delete a customer to DB
	  * 
	  * @param customerId
	  * @return boolean type
	  */
	 int deleteCustomer(int customerId) throws Exception;
	 
	 /**
	  * get all customer id from database
	  * 
	  * @return list customer id
	  */
	 List<Integer> getAllCustomerId() throws Exception;
	 
}
