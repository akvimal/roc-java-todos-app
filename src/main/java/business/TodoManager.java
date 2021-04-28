package business;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.TodoUI;
import dao.TodoDao;
import model.Todo;

//Business Layer
public class TodoManager {
	
	private static final Logger logger = LogManager.getLogger(TodoManager.class);
	
	private TodoDao dao = new TodoDao();

	public void setDao(TodoDao dao) {
		this.dao = dao;
	}

	public ArrayList<Todo> findAll() throws Exception {
		System.out.println("Received findAll request");
		
		ArrayList<Todo> list = dao.findAll();
		
		System.out.println("Received data from DB");
		return list;
	}

	public boolean save(Todo todo) {
		System.out.println("save called");
		return false;
	}
	
	public boolean update(Todo todo) {
		return false;
	}

}
