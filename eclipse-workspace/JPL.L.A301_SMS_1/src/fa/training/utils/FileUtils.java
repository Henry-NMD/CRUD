package fa.training.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {
	
	/**
	 * open a connection to file
	 * 
	 * @return file reader
	 * @throws FileNotFoundException 
	 */
	public static FileReader getConectToFile() throws FileNotFoundException {
		FileReader 	file = new FileReader("Entity.txt");
		return file;
	}
}
