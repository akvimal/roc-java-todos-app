package business;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.TodoDao;
import exceptions.ItemNotFoundException;
import model.Todo;

/**
 * Business Manager class for managing Todo item
 * 
 * @author Vimal
 */
public class TodoManager { // Business Layer

	private static final Logger logger = LogManager.getLogger(TodoManager.class);

	// DAO to perist data
	private TodoDao dao = new TodoDao();

	public void setDao(TodoDao dao) {
		this.dao = dao;
	}

	/**
	 * returns all the items from the system
	 * 
	 * @return List of Todo objects
	 * @throws Exception
	 */
	public ArrayList<Todo> findAll() throws Exception {
		logger.debug("Received findAll request");

		// calling DAO
		ArrayList<Todo> list = dao.findAll();

		logger.debug("Received data " + list);
		return list;
	}

	/**
	 * returns a Todo item for the given id
	 * 
	 * @param id
	 * @return
	 * @throws ItemNotFoundException
	 * @throws Exception
	 */
	public Todo findById(int id) throws ItemNotFoundException, Exception {
		logger.debug("Received findById request: " + id);
		// delegating call to DAO
		return dao.findById(id);
	}

	/**
	 * saves Todo object with data populated into the system
	 * 
	 * @param todo
	 * @return
	 * @throws Exception
	 */
	public boolean save(Todo todo) throws Exception {
		logger.debug("Received save request: " + todo);
		// delegating call to DAO
		return dao.save(todo);
	}

	/**
	 * updates the Todo item status for the given id
	 * 
	 * @param id
	 * @param done
	 * @return
	 * @throws ItemNotFoundException
	 * @throws Exception
	 */
	public boolean update(int id, boolean done) throws ItemNotFoundException, Exception {

		logger.debug("Received update request");

		try {
			dao.findById(id);
		} catch (ItemNotFoundException e) {
			logger.warn("Item entered is not correct", e);
			throw e;
		}
		// calling DAO
		dao.updateStatus(id, done);

		return false;
	}

}
