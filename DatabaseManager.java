import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	private static String className = "org.mariadb.jdbc.Driver";
	private static String url = "jdbc:mariadb://127.0.0.1:3306/MyDB";	//	ÇÊ¿ä¿¡ µû¶ó MyDB ºÎºÐ ¼öÁ¤
	private static String ID = "id";	//	MariaDB ID Insert
	private static String PW = "pwd";	//	MariaDB PWD Insert 
	
	static {
		try {
			Class.forName(className);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url, ID, PW);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
