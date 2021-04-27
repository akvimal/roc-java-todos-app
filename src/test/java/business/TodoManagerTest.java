package business;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import dao.TodoDao;
import model.Todo;

public class TodoManagerTest {
	
	@Test
	public void findAll() {
		TodoManager manager = new TodoManager();
		
		TodoDao daoMock = mock(TodoDao.class);
		
		ArrayList<Todo> mockReturnObjs = new ArrayList<Todo>();
		when(daoMock.findAll()).thenReturn(mockReturnObjs);
		
		manager.setDao(daoMock);
		
		ArrayList<Todo> todos = manager.findAll();
		
		
//		//assertion
		
		assertNotNull(todos);
	}

}
