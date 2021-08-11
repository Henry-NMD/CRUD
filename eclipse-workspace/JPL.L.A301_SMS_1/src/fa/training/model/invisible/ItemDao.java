package fa.training.model.invisible;

import java.util.List;

import fa.training.entities.LineItem;

public interface ItemDao {
	
	/**
	 * get list Item to database
	 * @param orderId
	 * @return list Items
	 */
	List<LineItem> getAllItemByOrderId(int orderId) throws Exception;
	
	
	/**
	 * add a item to database
	 * @param item
	 * @return boolean type
	 */
	int addLineItem(LineItem item) throws Exception;
	
}
