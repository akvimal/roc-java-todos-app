package client;

import java.util.List;
import java.util.Scanner;

import business.TodoManager;
import model.Todo;

//Presentation Layer
public class TodoUI {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Todos");
		
		Scanner input = new Scanner(System.in);
		
		showMainMenu();
		
		System.out.println("Chose the action: ");
		
		int action = 0;
		do {
			action = input.nextInt();
			switch (action) {
				case 1: //List	
					//TODO: implement
					TodoManager manager = new TodoManager();
					List<Todo> todos = manager.findAll();
					printTableFormat(todos);
					showMainMenu();
					break;
				case 2: //New
					//TODO: implement
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
