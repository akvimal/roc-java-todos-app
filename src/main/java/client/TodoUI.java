package client;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.TodoManager;
import model.Todo;

//Presentation Layer
public class TodoUI {

	private static final Logger logger = LogManager.getLogger(TodoUI.class);
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Todos");
		
		logger.info("Application Started");
		
		Scanner input = new Scanner(System.in);
		TodoManager manager = new TodoManager();
		
		showMainMenu();
		
		System.out.println("Chose the action: ");
		
		int action = 0;
		do {
			action = input.nextInt();
			switch (action) {
				case 1: //List	
					//TODO: implement
					try {
						List<Todo> todos = manager.findAll();
						printTableFormat(todos);
						showMainMenu();
					} catch (Exception e) {
						System.out.println("ERROR: "+e.getLocalizedMessage());
						System.exit(0);
					}
					break;
				case 2: //New
					//TODO: implement
					System.out.println("Add item (y/n):");
					String confirm = input.next();
					if("y".equals(confirm.toLowerCase())) {
						String desc = "";
						do {
							System.out.println("Description:");
							desc = input.next();
						} while(!validDesc(desc));
						System.out.println("Due Date (YYYY-MM-DD):");
						String date = input.next();
						manager.save(new Todo(desc,new Date(date)));
					} else {
						
					}
					break;
				case 3: //Update
					//TODO: implement
					break;
				case 4: //Exit
					System.out.println("Application Stopped");
					System.exit(0);
					break;
				default:
					break;
			}		
		} while(action > 0 && action < 5);
		

		input.close();
	}
	
	private static boolean validDesc(String desc) {
		if(desc.matches("\\w.*")) {
			return true;
		}
		System.out.println("Invalid Description");
		return false;
	}

	private static void showMainMenu() {
		System.out.println("1. List items");
		System.out.println("2. Add item");
		System.out.println("3. Update item");
		System.out.println("4. Exit");
	}

	static void printTableFormat(List<Todo> list){
		for(Todo todo: list) {
			System.out.print(todo.getId() + " | ");
			System.out.print(todo.getDescription() + " | ");
			System.out.print(todo.getDueDate() + " | ");
			System.out.println(todo.getStatus());
			System.out.println("-------------------------");
		}
	}
}

	