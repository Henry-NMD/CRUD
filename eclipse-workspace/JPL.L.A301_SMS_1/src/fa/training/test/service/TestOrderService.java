package fa.training.test.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import fa.training.exception.DuplicateException;
import fa.training.exception.IdNotFountException;
import fa.training.model.impl.OrderDaoLmpl;
import fa.training.service.OrderService;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestOrderService {
	private OrderService orderService = new OrderService();
	private OrderDaoLmpl orderDaoLmpl = new OrderDaoLmpl();
	private String orderIdTrue = "41";
	private String orderDateTrue = "2020-10-10";
	private String customerIdTrue = "1";
	private String orderIdFail = "20a";
	private String customerIdFail = "1a";
	private String customerIdNotFount = "100000";
	private String orderIdExitst = "11";	
	private String orderIdNotFount = "100000";

	



	/**
	 * test case add order success
	 */
	@BeforeAll
	void testAddOrder_cuccess() {
		int statusAdd = 1;
		try {
			assertEquals(statusAdd, orderService.addOrderService(orderIdTrue, orderDateTrue, customerIdTrue));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test case add customer fail for order id fail
	 */
	@Test
	void testAddOrder_fail_case1() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			orderService.addOrderService(orderIdFail, orderDateTrue, customerIdTrue);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case add customer fail for customer id fail
	 */
	@Test
	void testAddOrder_fail_case2() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			orderService.addOrderService(orderIdTrue, orderDateTrue, customerIdFail);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case add customer fail for customer not found fail
	 */
	@Test
	void testAddOrder_fail_case3() throws Exception {
		IdNotFountException thrown = assertThrows(IdNotFountException.class, () -> {
			orderService.addOrderService(orderIdNotFount, orderDateTrue, customerIdNotFount);
		});
		String message = "id not found";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * test case add customer fail for order id exitst fail
	 */
	@Test
	void testAddOrder_fail_case4() throws Exception {
		DuplicateException thrown = assertThrows(DuplicateException.class, () -> {
			orderService.addOrderService(orderIdExitst, orderDateTrue, customerIdTrue);
		});
		String message = "id exitst";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));

	}

	/**
	 * delete order after add order success
	 */
	@AfterAll
	void testDeleteOrder_cuccess() {
		int orderId = Integer.parseInt(orderIdTrue);
		int statusDelete = 1;
		try {
			assertEquals(statusDelete, orderDaoLmpl.deleteOrder(orderId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
