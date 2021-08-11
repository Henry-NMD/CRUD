package fa.training.main;

import java.util.List;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.readfile.ReadCustomer;
import fa.training.readfile.ReadLineItem;
import fa.training.readfile.ReadOrder;
import fa.training.service.CustomerService;
import fa.training.service.ItemService;
import fa.training.service.OrderService;
import fa.training.utils.Constant;

public class SaleManagement {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Customer> listCustomer;
		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService();
		ItemService itemService = new ItemService();
		Customer customer;
		String choise = null;
		while (true) {
			menu();
			choise = scanner.next().trim();
			switch (choise) {
			case Constant.GETALLCUSTOMER: {
				try {
					listCustomer = customerService.getAllCustomerService();
					if (listCustomer.isEmpty()) {
						System.out.println("data is null!");
					} else {
						listCustomer.forEach((Customer customers) -> {
							System.out.println(customers);
						});
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case Constant.GETALLOERDERBYCUSTOMERID: {
				String customerId = ReadOrder.checkChoise().get("customerId");
				try {
					List<Order> listCustomers = orderService.getAllOrderService(customerId);
					if (listCustomers.isEmpty()) {
						System.out.println("data is null!");
					} else {
						listCustomers.forEach((Order order) -> {
							System.out.println(order);
						});
					}
				} catch (Exception e) {
					e.getMessage();

				}

				break;
			}
			case Constant.GETALLITEMBYORDERID: {
				String orderId = ReadLineItem.checkChoise().get("orderId");
				try {
					List<LineItem> lineItemes = itemService.getItemByOrderIdService(orderId);
					if (lineItemes.isEmpty()) {
						System.out.println("data is null!");
					} else {
						lineItemes.forEach((LineItem item) -> {
							System.out.println(item);
						});
					}
				} catch (Exception e) {
					e.getMessage();
				}

				break;
			}
			case Constant.ADDCUSTOMER: {
				String addCustomer = "addCustomer";
				String id = ReadCustomer.checkChoise(addCustomer).get("customerId");
				String name = ReadCustomer.checkChoise(addCustomer).get("customerName");
				int statusAddCustomer;
				try {
					statusAddCustomer = customerService.addCustomerService(id, name);
					if (statusAddCustomer == 1) {
						System.out.println("added success");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constant.DELETECUSTOMER: {
				String deleteCustomer = "deleteCustomer";
				String customerId = ReadCustomer.checkChoise(deleteCustomer).get("customerId");
				int statusDeleteCustomer;
				try {
					statusDeleteCustomer = customerService.deleteCustomerService(customerId);
					if (statusDeleteCustomer == 1) {
						System.out.println("deleted success");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constant.UPDATECUSTOMER: {
				String updateCustomer = "updateCustomer";
				String id = ReadCustomer.checkChoise(updateCustomer).get("customerId");
				String name = ReadCustomer.checkChoise(updateCustomer).get("customerName");
				int statusDeleteCustomer;
				try {
					statusDeleteCustomer = customerService.updateCustomerService(id, name);
					if (statusDeleteCustomer == 1) {
						System.out.println("updated success");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constant.ADDORDER: {
				String orderId, orderDate, customerId, employeeId;
				orderId = ReadOrder.checkChoise().get("orderId");
				orderDate = ReadOrder.checkChoise().get("orderDate");
				customerId = ReadOrder.checkChoise().get("customerId");
				employeeId = ReadOrder.checkChoise().get("employeeId");
				try {
					int statusAddOrder = orderService.addOrderService(orderId, orderDate, customerId);
					if (statusAddOrder == 1) {
						System.out.println("added success!");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constant.ADDLINEITEM: {
				int statusAddItem;
				String orderId, productId, quatity;
				orderId = ReadLineItem.checkChoise().get("orderId");
				productId = ReadLineItem.checkChoise().get("productId");
				quatity = ReadLineItem.checkChoise().get("quantity");
				try {
					statusAddItem = itemService.addLineItemService(orderId, productId, quatity);
					if (statusAddItem == 1) {
						System.out.println("added success!");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case Constant.COMPUTEORDERTOTAL: {
				String orderId = ReadOrder.checkChoise().get("orderId");
				try {
					List<Order> listOrder = orderService.listOrderService(orderId);
					List<LineItem> listItem = itemService.getItemByOrderIdService(orderId);
					if (listOrder.isEmpty()) {
						System.out.println("data is null");
					} else {
						System.out.println("orderid" + "\t\t\t\t" + "total");
						listOrder.forEach((Order order) -> {
							System.out.println(order.getOrderId() + "\t\t\t\t" + order.getTotal());
						});
						System.out.println("productId" + "\t" + "quantity" + "\t" + "price");
						listItem.forEach((LineItem item) -> {
							System.out.println(
									+item.getProductId() + "\t\t" + item.getQuantity() + "\t\t" + item.getPrice());
						});
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choise);
			}
		}

	}

	/**
	 * show menu
	 * 
	 */
	public static void menu() {
		System.out.println("===========LIBRALY MANAGEMENT============");
		System.out.println("1. get all customer.");
		System.out.println("2. get all order by customerId.");
		System.out.println("3. get all item by orderId.");
		System.out.println("4. get order total.");
		System.out.println("5. add customer.");
		System.out.println("6. delete customer.");
		System.out.println("7. update customer.");
		System.out.println("8. add order.");
		System.out.println("9. add line item.");
		System.out.println("your choose:");

	}

}
