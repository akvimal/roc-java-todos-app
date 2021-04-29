package business;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.TodoDao;
import model.Todo;

public class TodoManagerTest {
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void findAll() {
		TodoManager manager = new TodoManager();
		
		TodoDao daoMock = mock(TodoDao.class);
		
		ArrayList<Todo> mockReturnObjs = new ArrayList<Todo>();
		try {
			when(daoMock.findAll()).thenReturn(mockReturnObjs);
			
			manager.setDao(daoMock);
			
			ArrayList<Todo> todos = manager.findAll();
			
			assertNotNull(todos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
