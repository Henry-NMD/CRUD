package fa.training.readfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fa.training.utils.FileUtils;
/**
 * 
 * 
 * @author nguyenminhduy941gmail.com
 *
 */
public class ReadCustomer {

	/**
	 * get value from map collection
	 * @param choise
	 * @return map collection
	 * @throws FileNotFoundException 
	 */
	public static Map<String, String> checkChoise(String choise) {
		String[] listString = readFileTo();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < listString.length; i++) {
			if (choise.equals("addCustomer") || choise.equals("updateCustomer")) {
				if (i == 0) {
					map.put("customerId", listString[i]);
				} else {
					map.put("customerName", listString[i]);
				}
			} else {
				if (i == 0) {
					map.put("customerId", listString[i]);
				}
			}
		}
		return map;
	}


	/**
	 * read a file into String array
	 * 
	 * @param path
	 * @return list read object from file
	 * @throws FileNotFoundException 
	 */
	public static String[] readFileTo() {
		String search = "Customer";
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
