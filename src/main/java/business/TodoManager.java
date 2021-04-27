package business;

import java.util.ArrayList;

import dao.TodoDao;
import model.Todo;

//Business Layer
public class TodoManager {

	public ArrayList<Todo> findAll(){
		TodoDao dao = new TodoDao();
		return dao.findAll();
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
