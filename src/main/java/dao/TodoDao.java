package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.TodoManager;
import model.Todo;
import util.DBConnection;

//Persistence Layer
public class TodoDao {
	
	
	private static final Logger logger = LogManager.getLogger(TodoDao.class);
	
	
	public ArrayList<Todo> findAll() throws SQLException {
		ArrayList<Todo> todos = new ArrayList<Todo>();
		
		try {
			Connection con = DBConnection.getInstance().getConnection();
		
		
			System.out.println("fetching data for findAll");
		
			String sql = "SELECT id, description, due_date, done FROM todos";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String desc = rs.getString("description");
				Date due = rs.getDate("due_date");
				String status = rs.getString("done");

				todos.add(new Todo(id,desc,due,status));
			}
		
		} catch (SQLException e) {
			logger.error("Unable to perform DB operation", e);
			throw e;
		} 
		
		return todos;
	}
	
	
	public boolean save(Todo todo) {
		return false;
	}
	
	public boolean update(Todo todo) {
		return false;
	}
	
}
