package fa.training.validator;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * check validator
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class Validator {
	public static final String FORMATDATE = "^\\d{4}[-]\\d{2}[-]\\d{2}$";
	public static final String checkName = "[^\\w\\*]";
	public static final String intType = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]))";

	/**
	 * get value then check type
	 * 
	 * @param value
	 * @return if contains special character return true otherwise false
	 * @throws NumberFormatException
	 */
	public static boolean inputTypeInt(String value) throws NumberFormatException {
		Pattern pattern = Pattern.compile(intType);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * check name contains character special
	 * 
	 * @param name
	 * @return if name contains character special return true otherwise fail
	 */
	public static boolean isName(String name) throws Exception {
		if (name == null || name.equals("")) {
			throw new NullPointerException("name is null");
		} else {
			Pattern pattern = Pattern.compile(checkName);
			Matcher matcher = pattern.matcher(name);
			if (matcher.find()) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * check format date type
	 * 
	 * @param value
	 * @return date type
	 * @throws Exception
	 */
	public static String isDate(String value) throws Exception {
		Pattern pattern = Pattern.compile(FORMATDATE);
		Matcher matcher = pattern.matcher(value);
		if (matcher.find()) {
			return value;
		} else {
			throw new Exception("please check format date");
		}
	}

	/**
	 * convert from string to date
	 * 
	 * @param value
	 * @return date type
	 * @throws Exception
	 */
	public static Date convertStringToDate(String value) throws Exception {
		String isDate = isDate(value);
		Date date = Date.valueOf(isDate);
		return date;
	}

	/**
	 * this method is checked if the customer id exits in the list or not
	 * 
	 * @param customerId
	 * @param id
	 * @return true if customer id is exits , otherwise false
	 */
	public static boolean checkCustomerIdExist(final List<Integer> listId, int id) {
		boolean check = listId.stream().anyMatch((Integer integer) -> id == integer);
		return check;
	}

}
