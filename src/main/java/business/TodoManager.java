package business;

import java.util.ArrayList;

import dao.TodoDao;
import model.Todo;

//Business Layer
public class TodoManager {

	public ArrayList<Todo> findAll(){
		System.out.println("Received findAll request");
		
		TodoDao dao = new TodoDao();
		ArrayList<Todo> list = dao.findAll();
		
		System.out.println("Received data from DB");
		return list;
	}

	public ArrayList<Todo> findByDesc(String desc){
		TodoDao dao = new TodoDao();
		return dao.findAll();
	}
	
	public boolean save(Todo todo) {
		return false;
	}
	
	public boolean update(Todo todo) {
		return false;
	}

}
