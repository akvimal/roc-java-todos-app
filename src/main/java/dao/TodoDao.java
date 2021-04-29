package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.ItemNotFoundException;
import model.Todo;
import util.DBConnection;

/**
 * Data Access Object for Todo
 * 
 * @author Vimal
 */
public class TodoDao { // Persistence Layer

	private static final Logger logger = LogManager.getLogger(TodoDao.class);

	public ArrayList<Todo> findAll() throws Exception {

		ArrayList<Todo> todos = new ArrayList<Todo>();

		try {
			Connection con = DBConnection.getInstance().getConnection();
			String sql = "SELECT id, description, due_date, done FROM todos";

			logger.debug("fetching data", sql);

			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String desc = rs.getString("description");
				Date due = rs.getDate("due_date");
				String status = rs.getString("done");

				todos.add(new Todo(id, desc, due, status));
			}

		} catch (SQLException e) {
			logger.error("Unable to perform DB operation", e);
			throw e;
		}

		logger.debug("Returning results", todos);
		return todos;
	}

	public Todo findById(int id) throws ItemNotFoundException, Exception {

		Todo todo = null;

		Connection con = DBConnection.getInstance().getConnection();

		logger.debug("fetching data for id: " + id);

		String sql = "SELECT id, description, due_date, done FROM todos WHERE id = ?";

		logger.debug(sql);

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			String desc = rs.getString("description");
			Date due = rs.getDate("due_date");
			String status = rs.getString("done");

			todo = new Todo(id, desc, due, status);
		} else {
			throw new ItemNotFoundException();
		}

		logger.debug("Returning todo", todo);

		return todo;
	}

	public boolean save(Todo todo) throws Exception {
		int inserted = 0;

		logger.debug("Received todo data to save: " + todo);

		Connection con = DBConnection.getInstance().getConnection();

		String sql = "INSERT INTO todos (description, due_date) VALUES (?,?)";

		logger.debug(sql);

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, todo.getDescription());
		pstmt.setDate(2, new java.sql.Date(todo.getDueDate().getTime()));

		inserted = pstmt.executeUpdate();

		logger.debug("Inserted todo: " + inserted);

		return inserted != 0;
	}

	public boolean updateStatus(int id, boolean done) throws Exception {

		logger.debug("Received todo status update: " + done);

		Connection con = DBConnection.getInstance().getConnection();

		String sql = "UPDATE todos SET done = ? WHERE id = ?";

		logger.debug(sql);

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, done ? "Y" : "N");
		pstmt.setInt(2, id);

		int updated = pstmt.executeUpdate();

		logger.debug("Updated todo: " + updated);

		return updated != 0;
	}

}
