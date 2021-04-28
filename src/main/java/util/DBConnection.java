package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance;
	private Connection conn = null;
	
	private DBConnection() {
		try {
			String url = ConfigReader.getInstance().getProperty("DB_URL");
			String user = ConfigReader.getInstance().getProperty("DB_USER");
			String password = ConfigReader.getInstance().getProperty("DB_PASSWORD");
			
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}

}
