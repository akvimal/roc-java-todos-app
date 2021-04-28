package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.TodoDao;

public class DBConnection {
	
	private static final Logger logger = LogManager.getLogger(DBConnection.class);
	
	private static DBConnection instance;
	private Connection conn = null;
	
	private DBConnection() throws SQLException {
		
			String url = ConfigReader.getInstance().getProperty("DB_URL");
			String user = ConfigReader.getInstance().getProperty("DB_USER");
			String password = ConfigReader.getInstance().getProperty("DB_PASSWORD");
			
			this.conn = DriverManager.getConnection(url, user, password);
			
	}
	
	public static DBConnection getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}

}
