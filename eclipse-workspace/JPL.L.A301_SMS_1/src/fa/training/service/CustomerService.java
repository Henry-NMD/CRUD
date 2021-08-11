package fa.training.service;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.exception.DuplicateException;
import fa.training.exception.FormatException;
import fa.training.exception.IdNotFountException;
import fa.training.model.impl.CustomerDaoLmpl;
import fa.training.utils.Constant;
import fa.training.validator.Validator;

/**
 * get value and check value then call DAO
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class CustomerService {
	private CustomerDaoLmpl customerDaoLmpl = new CustomerDaoLmpl();
	private boolean checkCustomerIdExits;

	/**
	 * call getAllCustomer function get all customer to DB
	 * 
	 * @return list customer
	 */
	public List<Customer> getAllCustomerService() throws Exception {
		List<Customer> listCustomer = new ArrayList<Customer>();
		try {
			listCustomer = customerDaoLmpl.getAllCustomer();
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return listCustomer;
	}

	/**
	 * get value from main class then check validator and call DAO
	 * 
	 * @param customer
	 * @throws Exception
	 * @return message when add customer to DB
	 */
	public int addCustomerService(String id, String name) throws Exception {
		int checkAdd = 0;
		int statusAddCustomer = 0;
		int customerId = 0;
		String customerName = null;
		try {
			if (Validator.inputTypeInt(id) == true) {
				throw new NumberFormatException();
			} else {
				customerId = Integer.parseInt(id);
			}
			if (Validator.isName(name) == false) {
				throw new FormatException();
			} else {
				customerName = name;
			}
			checkCustomerIdExits = Validator.checkCustomerIdExist(listCustomerId(), customerId);
			if (checkCustomerIdExits == true) {
				throw new DuplicateException();
			} else {
				Customer customer = new Customer(customerId, customerName);
				statusAddCustomer = customerDaoLmpl.addCustomer(customer);
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (FormatException e) {
			throw new FormatException("name contains special character");
		} catch (DuplicateException e) {
			throw new DuplicateException("id exitst");
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return statusAddCustomer;
	}

	/**
	 * get value from main class then check validator and call DA0
	 * 
	 * @param customer
	 * @throws Exception
	 * @return statusUpdateCustomer when add customer to DB
	 */
	public int updateCustomerService(String id, String name) throws Exception {
		int statusUpdateCustomer = 0;
		int customerId = 0;
		String customerName = null;
		try {
			if (Validator.inputTypeInt(id) == true) {
				throw new NumberFormatException();
			} else {
				customerId = Integer.parseInt(id);
			}
			if (Validator.isName(name) == false) {
				throw new FormatException();
			} else {
				customerName = name;
			}
			checkCustomerIdExits = Validator.checkCustomerIdExist(listCustomerId(), customerId);
			if (checkCustomerIdExits == true) {
				Customer customer = new Customer(customerId, customerName);
				statusUpdateCustomer = customerDaoLmpl.updateCustomer(customer);

			} else {
				throw new IdNotFountException();
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (FormatException e) {
			throw new FormatException("name contains special character");
		} catch (IdNotFountException e) {
			throw new IdNotFountException("id not found");
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return statusUpdateCustomer;
	}

	/**
	 * get value from main class then check validator and call DA0
	 * 
	 * @param customer
	 * @return statusDeleteCustomer when delete customer to DB
	 * @throws Exception
	 */
	public int deleteCustomerService(String id) throws Exception {
		int customerId = 0;
		int statusDeleteCustomer = 0;
		try {
			if (Validator.inputTypeInt(id) == true) {
				throw new NumberFormatException();
			} else {
				customerId = Integer.parseInt(id);
			}
			// check duplicate id
			checkCustomerIdExits = Validator.checkCustomerIdExist(listCustomerId(), customerId);
			if (checkCustomerIdExits == true) {
				statusDeleteCustomer = customerDaoLmpl.deleteCustomer(customerId);
			} else {
				throw new IdNotFountException();
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (IdNotFountException e) {
			throw new IdNotFountException("id not found");
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}
		return statusDeleteCustomer;
	}

	/**
	 * call DAO get all customer id
	 * 
	 * @return list customerId
	 */
	private List<Integer> listCustomerId() {
		List<Integer> id = null;
		try {
			id = customerDaoLmpl.getAllCustomerId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}