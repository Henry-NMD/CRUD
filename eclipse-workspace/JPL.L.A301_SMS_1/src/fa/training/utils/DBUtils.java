package fa.training.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	/**
	 * open a connection to DB
	 * 
	 * @return connection
	 * @throws SQLException
	 */
	public static java.sql.Connection getConnection() throws SQLException {
		java.sql.Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS", "root", "P210506!");
		return conn;
	}

}
