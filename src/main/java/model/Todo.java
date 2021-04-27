package model;

import java.util.Date;

//POJO
public class Todo {

	private int id;
	private String description;
	private Date dueDate;
	private String status;

	public Todo(String description, Date dueDate) {
		super();
		this.description = description;
		this.dueDate = dueDate;
	}
	
	public Todo(String description, Date dueDate, String status) {
		super();
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Todo(int id, String description, Date dueDate, String status) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
	
	//getter and setter
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", status=" + status + "]";
	}
	
	
}
