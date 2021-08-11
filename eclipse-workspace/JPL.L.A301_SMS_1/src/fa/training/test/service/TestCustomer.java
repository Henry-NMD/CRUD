package fa.training.test.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fa.training.exception.DuplicateException;
import fa.training.exception.FormatException;
import fa.training.exception.IdNotFountException;
import fa.training.service.CustomerService;

class TestCustomer {
	private String customerIdTrue = "1";
	private String customerNameTrue = "testname";
	private String customerIdFail = "10a";
	private String customerIdNotFound = "100";
	private String customerIdExitst = "1";
	private String customerNameFail = "testName1@!";
	private CustomerService customerService = new CustomerService();

	@Test
	void test() {
//		fail("Not yet implemented");
	}

	/**
	 * test case add customer success
	 */
	@Before
	void testAddCustomer_cuccess() {
		int statusAdd = 1;
		try {
			assertEquals(statusAdd, customerService.addCustomerService(customerIdTrue, customerNameTrue));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test case add customer fail for customerId fail
	 */
	@Test
	void testAddCustomer_fail_case1() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			customerService.addCustomerService(customerIdFail, customerNameTrue);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case add customer fail for customerName fail
	 */
	@Test
	void testAddCustomer_fail_case2() throws Exception {
		FormatException thrown = assertThrows(FormatException.class, () -> {
			customerService.addCustomerService(customerIdTrue, customerNameFail);
		});
		String message = "name contains special character";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case add customer fail for customerId exitst
	 */
	@Test
	void testAddCustomer_fail_case3() throws Exception {
		DuplicateException thrown = assertThrows(DuplicateException.class, () -> {
			customerService.addCustomerService(customerIdExitst, customerNameTrue);
		});
		String message = "id exitst";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case update customer success
	 */
	@Test
	void testUpdateCustomer_cuccess() {
		int statusAdd = 1;
		try {
			assertEquals(statusAdd, customerService.updateCustomerService(customerIdTrue, customerNameTrue));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test case update customer fail for customerId fail
	 */
	@Test
	void testUpdateCustomer_fail_case() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			customerService.updateCustomerService(customerIdFail, customerIdTrue);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case update customer fail for customerName fail
	 */
	@Test
	void testUpdateCustomer_fail_case2() throws Exception {
		FormatException thrown = assertThrows(FormatException.class, () -> {
			customerService.updateCustomerService(customerIdTrue, customerNameFail);
		});
		String message = "name contains special character";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case update customer fail for customerId not found
	 */
	@Test
	void testUpdateCustomer_fail_case3() throws Exception {
		IdNotFountException thrown = assertThrows(IdNotFountException.class, () -> {
			customerService.updateCustomerService(customerIdNotFound, customerNameTrue);
		});
		String message = "id not found";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case delete customer fail for customerId Not Found
	 */
	@Test
	void testDeleteCustomer_fail1() throws Exception {
		IdNotFountException thrown = assertThrows(IdNotFountException.class, () -> {
			customerService.deleteCustomerService(customerIdNotFound);
		});
		String message = "id not found";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case delete customer fail for customerId fail
	 */
	@Test
	void testDeleteCustomer_fail2() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			customerService.deleteCustomerService(customerIdFail);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case delete customer success
	 */
	@Test
	void testDeleteCustomer_cuccess() throws Exception {
		int statusAdd = 1;
		String id = "8";
		try {
			assertEquals(statusAdd, customerService.deleteCustomerService(customerIdTrue));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
