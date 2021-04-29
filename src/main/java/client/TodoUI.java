package client;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.TodoManager;
import exceptions.ItemNotFoundException;
import model.Todo;
import util.ConfigReader;

/**
 * User Interface for Todo App
 * 
 * @author Vimal
 */
public class TodoUI { //Presentation Layer

	private static final Logger logger = LogManager.getLogger(TodoUI.class);

	public static void main(String[] args) {

		logger.info("Application Started");

		System.out.println("Welcome to Todos");

		Scanner input = new Scanner(System.in);

		try {
			run(input);
		} catch (Exception e) {
			if (e instanceof SQLException) {
				System.out.println("ERROR: " + e.getLocalizedMessage());
				System.exit(0);
			}
			logger.error("Unexpected Error", e);
		} finally {
			input.close();
		}
		
		logger.info("Application Stopped");
	}

	private static void run(Scanner input) throws Exception, ItemNotFoundException {
		
		// load all application configurations
		ConfigReader.getInstance();

		TodoManager manager = new TodoManager();

		int menuAction = 0;
		do {
			showMainMenu();

			System.out.println("Chose the action: ");
			menuAction = input.nextInt();

			switch (menuAction) {

			case 1: // List Items
				displayTodos(manager);
				break;

			case 2: // Add Item
				System.out.println("Add item (y/n):");
				String confirmAdd = input.next();
				if ("y".equalsIgnoreCase(confirmAdd)) {

					String desc = readDescription(input);
					Date dt = readDate(input);

					// saving data
					manager.save(new Todo(desc, dt));
					System.out.println("Item Saved");
				}
				break;

			case 3: // Update Item Status
				System.out.println("Update item (y/n):");
				String confirmUpdate = input.next();

				if ("y".equalsIgnoreCase(confirmUpdate)) {

					displayTodos(manager);

					int id = readId(input, manager);
					String doneInput = readStatus(input);

					// call MANAGER to update
					manager.update(id, doneInput.equalsIgnoreCase("y") ? true : false);
					System.out.println("Item Status Changed");
				}
				break;
			case 4: // Exit
				System.out.println("Application Stopped");
				System.exit(0);
				break;
			default:
				break;
			}
		} while (menuAction > 0 && menuAction < 5);
	}

	private static String readStatus(Scanner input) {
		String doneInput;
		do {
			System.out.println("Item DONE (y/n):");
			doneInput = input.next();

			if (!("y".equalsIgnoreCase(doneInput) || "n".equalsIgnoreCase(doneInput))) {
				doneInput = null;
				System.out.println("Enter valid input");
			}
		} while (doneInput == null);
		return doneInput;
	}

	private static int readId(Scanner input, TodoManager manager) throws Exception {
		int id;
		do {
			System.out.println("Enter ID:");
			id = input.nextInt();

			if (!isValidId(manager, id)) {
				id = 0;
				System.out.println("ID not valid");
			}
		} while (id == 0);
		return id;
	}

	private static Date readDate(Scanner input) {
		Date dt = null;
		do {
			System.out.println("Due Date (YYYY-MM-DD):");
			String date = input.next();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dt = formatter.parse(date);
			} catch (ParseException e) {
				System.out.println("Invalid date format");
			}
		} while (dt == null);
		return dt;
	}

	private static String readDescription(Scanner input) {
		String desc;
		do {
			System.out.println("Description:");
			desc = input.next();
			if (!desc.matches("^(\\s?\\.?[a-zA-Z]+)+$")) {
				desc = null;
				System.out.println("No special characters allowed, except .");
			}

		} while (desc == null);
		return desc;
	}

	private static boolean isValidId(TodoManager manager, int id) throws Exception {
		try {
			manager.findById(id);
		} catch (ItemNotFoundException e) {
			return false;
		}
		return true;
	}

	private static void displayTodos(TodoManager manager) throws Exception {
		List<Todo> todos = manager.findAll();
		printTableFormat(todos);
	}

	private static void showMainMenu() {
		System.out.println();
		System.out.println("1. List All Items");
		System.out.println("2. Add New Item");
		System.out.println("3. Update Item Status");
		System.out.println("4. Exit");
	}

	static void printTableFormat(List<Todo> list) {
		for (Todo todo : list) {
			System.out.print(todo.getId() + " | ");
			System.out.print(todo.getDescription() + " | ");
			System.out.print(todo.getDueDate() + " | ");
			System.out.println(todo.getStatus());
			System.out.println("-------------------------");
		}
	}
}
