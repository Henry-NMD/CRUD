package fa.training.readfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fa.training.utils.FileUtils;

public class ReadOrder {

	/**
	 * get value from map collection
	 * 
	 * @param choise
	 * @return map collection
	 */
	public static Map<String, String> checkChoise() {
		String[] listString = readFileTo();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < listString.length; i++) {
			if (i == 0) {
				map.put("orderId", listString[i]);
			} else if (i == 1) {
				map.put("orderDate", listString[i]);
			} else if (i == 2) {
				map.put("customerId", listString[i]);
			} else if (i == 3) {
				map.put("employeeId", listString[i]);
			} else {
				map.put("total", listString[i]);
			}
		}
		return map;
	}

	/**
	 * read a file into String array
	 * 
	 * @param path
	 * @return list read object from file
	 */
	private static String[] readFileTo() {
		String search = "Order";
		BufferedReader reader = null;
		String newString = null;
		String[] saveElement = null;
		String line = null;
		try {
			reader = new BufferedReader(FileUtils.getConectToFile());
			line = reader.readLine();
			while (line != null) {
				Pattern pattern = Pattern.compile(search);
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) == ':') {
							newString = line.substring(i + 1);
						}
					}
					saveElement = newString.split("\\s");
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return saveElement;
	}
}
