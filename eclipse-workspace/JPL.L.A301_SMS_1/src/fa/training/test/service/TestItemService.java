package fa.training.test.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import fa.training.exception.DuplicateException;
import fa.training.model.impl.ItemDaoLmpl;
import fa.training.service.ItemService;

class TestItemService {
	private ItemService itemService = new ItemService();
	private String orderIdTrue = "20";
	private String productIdTrue = "12";
	private String quantityTrue = "2";
	private String orderIdFail = "11a";
	private String productIdFail = "12a";
	private String quantityFail = "2a";
	private String orderIdNotFound = "100";
	private String productExitst = "10";

	@Test
	void test() {
//		fail("Not yet implemented");
	}

	/**
	 * test case add line item success case
	 */
	@Before
	void addItem_success() throws Exception {
		int statusAddItem = 1;
		assertEquals(statusAddItem, itemService.addLineItemService(orderIdTrue, productIdTrue, quantityTrue));
	}

	/**
	 * test case add line item fail order id case
	 */
	@Test
	void addItem_fail1() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			itemService.addLineItemService(orderIdFail, productIdTrue, quantityTrue);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));
	}

	/**
	 * test case add line item fail product id case
	 */
	@Test
	void addItem_fail2() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			itemService.addLineItemService(orderIdTrue, productIdFail, quantityTrue);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));
	}

	/**
	 * test case add line item fail quantity case
	 */
	@Test
	void addItem_fail3() throws Exception {
		NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
			itemService.addLineItemService(orderIdTrue, productIdTrue, quantityFail);
		});
		String message = "not match int type";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));
	}

	/**
	 * test case add line item fail product id exitst case
	 */
	@After
	void addItem_fail5() throws Exception {
		DuplicateException thrown = assertThrows(DuplicateException.class, () -> {
			itemService.addLineItemService(orderIdTrue, productIdTrue, quantityTrue);
		});
		String message = "id exitst";
		String errorMessage = thrown.getMessage();
		assertTrue(errorMessage.contains(message));
	}

	/**
	 * delete line item after add success
	 */
	@After
	void deleteLineitem() throws Exception {
		int orderId = Integer.parseInt(orderIdTrue);
		int productId = Integer.parseInt(productIdTrue);
		ItemDaoLmpl daoLmpl = new ItemDaoLmpl();
		int status = 1;
		assertEquals(status, daoLmpl.deleteItem(status, daoLmpl.deleteItem(orderId, productId)));
	}

}
