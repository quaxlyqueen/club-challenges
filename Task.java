
import java.util.Arrays;
import java.util.Comparator;

// Represents a Task, which can be written and loaded to/from a file and are comparable to each other.
public class Task {
	private String title;
	private String description;
	private String dueDate;
	private boolean completed;

	public static final Comparator<Task> BY_TITLE = new SortByTitleComparator();

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

	// TODO: Implement Comparable or Comparator.
	private static class SortByTitleComparator implements Comparator<Task> {

		@Override
		public int compare(Task t1, Task t2) {
			String[] tokens1 = t1.title.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			String[] tokens2 = t2.title.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

			int length = Math.min(tokens1.length, tokens2.length);

			for (int i = 0; i < length; i++) {
				if (Character.isDigit(tokens1[i].charAt(0)) && Character.isDigit(tokens2[i].charAt(0))) {
					int intCompare = Integer.compare(Integer.parseInt(tokens1[i]), Integer.parseInt(tokens2[i]));

					if (intCompare != 0)
						return intCompare;
				} else {

					int stringCompare = tokens1[i].compareTo(tokens2[i]);

					if (stringCompare != 0)
						return stringCompare;
				}
			}

			return Integer.compare(tokens1.length, tokens2.length);
		}

	}
}
