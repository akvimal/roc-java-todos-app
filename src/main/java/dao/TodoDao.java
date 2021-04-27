package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Todo;

//Persistence Layer
public class TodoDao {
	
	static Connection con = null;
	
	static {
		try {
			//System.out.println("Connecting..");
			
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/learndb",
					"postgres","postgres");
			
			//System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Todo> findAll(){

		ArrayList<Todo> todos = new ArrayList<Todo>();
		
		try {

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
			e.printStackTrace();
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