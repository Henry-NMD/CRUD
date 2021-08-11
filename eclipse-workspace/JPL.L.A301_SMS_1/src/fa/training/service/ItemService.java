package fa.training.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.LineItem;
import fa.training.exception.DuplicateException;
import fa.training.exception.IdNotFountException;
import fa.training.model.impl.ItemDaoLmpl;
import fa.training.model.impl.OrderDaoLmpl;
import fa.training.validator.Validator;

/**
 * 
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class ItemService {
	private ItemDaoLmpl itemDaoLmpl = new ItemDaoLmpl();
	private OrderDaoLmpl orderDao = new OrderDaoLmpl();

	/**
	 * get value from main class and check value then call
	 * getAllItemByOrderId,addLineItem, updateOrderTotal function in DAO class
	 * 
	 * @param OrId
	 * @param proId
	 * @param quatities
	 * @return statusAddItem
	 * @throws Exception
	 */
	public int addLineItemService(String OrId, String proId, String quatities) throws Exception {
		List<LineItem> listItem = null;
		List<LineItem> listItemAfterAdd = null;
		int OrderId = 0, productId, quantity;
		int resultPrice;
		double price = 0;
		int statusAdd = 0;
		int statusUpdate = 0;
		double total = 0;
		try {
			if ((Validator.inputTypeInt(OrId) == true) && (Validator.inputTypeInt(proId) == true)
					&& (Validator.inputTypeInt(quatities) == true)) {
				throw new NumberFormatException();
			} else {
				OrderId = Integer.parseInt(OrId);
				productId = Integer.parseInt(proId);
				quantity = Integer.parseInt(quatities);
			}
			listItem = itemDaoLmpl.getAllItemByOrderId(OrderId);
			if (!listItem.isEmpty()) {
				for (int i = 0; i < listItem.size(); i++) {
					if (OrderId == listItem.get(i).getOrderId()) {
						if (productId == listItem.get(i).getProductId()) {
							throw new DuplicateException();
						}
					}
				}
			}
			resultPrice = itemDaoLmpl.getPriceByProductId(productId);
			price = quantity * resultPrice;
			LineItem item = new LineItem(OrderId, productId, quantity, price);
			statusAdd = itemDaoLmpl.addLineItem(item);
			if (statusAdd != 0) {
				listItemAfterAdd = itemDaoLmpl.getAllItemByOrderId(OrderId);
				for (int i = 0; i < listItemAfterAdd.size(); i++) {
					total = total + listItemAfterAdd.get(i).getPrice();
				}
				statusUpdate = orderDao.updateOrderTotal(OrderId, total);

				if (statusUpdate == 0) {
					throw new SQLException();
				}
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		} catch (DuplicateException e) {
			throw new DuplicateException("id exitst");
		} catch (IdNotFountException e) {
			throw new IdNotFountException("id not found");
		} catch (SQLException e) {
			throw new SQLException("update order total fail!");
		} catch (Exception e) {
			throw new Exception("somethins went wrong");
		}
		return statusAdd;
	}

	/**
	 * get value from main class and check value then call getAllItemByOrderId
	 * function in DAO class
	 * 
	 * @param orderId
	 * @return list line item
	 */
	public List<LineItem> getItemByOrderIdService(String OrId) throws Exception {
		List<LineItem> listItem = null;
		int status = 0;
		int OrderId;
		try {
			if (Validator.inputTypeInt(OrId) == true) {
				throw new NumberFormatException();
			} else {
				OrderId = Integer.parseInt(OrId);
			}
			listItem = itemDaoLmpl.getAllItemByOrderId(OrderId);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("not match int type");
		}
		return listItem;
	}

}
