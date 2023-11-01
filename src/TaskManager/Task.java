package TaskManager;

import java.util.Comparator;

// Represents a Task, which can be written and loaded to/from a file and are comparable to each other.
public class Task implements Comparable<Task> {
	private String title;
	private String description;
	private String dueDate;
	private boolean completed;
	public static final Comparator<Task> BY_DUE_DATE = new compareByDueDate();

	// Constructor to create a new Task object.
	public Task(String title, String description, String dueDate) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = false;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	// Edit a task by providing new information.
	public void editTask(String newTitle, String newDescription, String newDueDate) {
		this.title = newTitle;
		this.description = newDescription;
		this.dueDate = newDueDate;
	}

	// Return if a task is complete or not.
	public void toggleComplete() {
		if (completed)
			completed = false;
		else
			completed = true;

		System.out.println("task status toggled");
	}

	@Override
	public int compareTo(Task o) {
		return this.title.compareTo(o.title);
	}

	static final class compareByDueDate implements Comparator<Task> {

		@Override
		public int compare(Task task1, Task task2) {
			return task1.dueDate.compareTo(task2.dueDate);
		}

	}
	// TODO: Implement Comparable or Comparator.
}
