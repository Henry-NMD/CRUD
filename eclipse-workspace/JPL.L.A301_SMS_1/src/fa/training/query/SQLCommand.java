package fa.training.query;

public class SQLCommand {

	/**
	 * the query add a customer to database
	 */
	public static String addCustomer = 
			"{CALL AddCustomer (?,?)}";

	/**
	 * the query get all customer from database
	 */
	public static String getAllCustomer = 
			"SELECT customer_id , customer_name \n" 
					+ "FROM Customer";

	/**
	 * the query add item to database
	 */
	public static String addLineItem = 
			"INSERT INTO LineItem(order_Id , product_id , quantity , price)\n"
				+ " VALUES (?,?,?,?)";

	/**
	 * the query get all list order from database
	 */
	public static String getAllOrderId = 
			"SELECT order_id,order_date,total,customer_id,employee_id \n"
					+ "FROM Orders WHERE order_id = ?";

	/**
	 * the query get all list order by customerId from database
	 */
	public static String getAllOrderByCustomerId = 
			"SELECT order_id,order_date,total,customer_id,employee_id\n"
					+ " FROM Orders Where customer_id = ?";

	/**
	 * the query get all item by orderId from database
	 */
	public static String getAllItemByOrderId = 
			"SELECT order_id , product_id , quantity , price\n"
					+ " FROM LineItem\n"
						+ " WHERE order_Id = ?";

	/**
	 * The query add a order to database
	 */
	public static String addOrder = 
			"INSERT INTO Orders (order_id,order_date,customer_id,employee_id,total)\n"
			+ " VALUES (?,?,?,?,?)";

	/**
	 * The query update a order to database
	 */
	public static String updateOrderTotal = 
			"UPDATE Orders\n"
			+ " SET  total = ?\n"
			+ "  WHERE order_id = ?";

	/**
	 * The query call store procedure using drop customer to database
	 */
	public static String deleteCustomer = 
			"{CALL DeleteCustomer(?)}";

	/**
	 * The query call store procedure using update customer to database
	 */
	public static String updateCustomer = 
			"{CALL UpdateCustomer(?,?)}";

	/**
	 * The query get all customer id
	 */
	public static String getAllCustomerById = 
			"SELECT customer_id FROM customer";

	/**
	 * The query get all item id
	 */
	public static String getAllItemById = 
			"SELECT order_Id,product_id FROM LineItem WHERE order_Id = ?";

	/**
	 * The query get all order id
	 */
	public static String getAllOrderById = 
			"SELECT order_Id FROM Orders";
	
	/**
	 * 
	 */
}
