package TaskManager;

import java.awt.event.*;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

public class Week9 {
	private static ArrayList<Task> tasks;
	private static String filepath;
	private static Week9Gui gui;

	// NOTE: For sorts, the Task class needs something implemented.
	private static void sortByTitle() {
		System.out.println("Sort By Title clicked!");
		// TODO
		gui.update(tasks);
	}

	// NOTE: For sorts, the Task class needs something implemented.
	private static void sortByDueDate() {
		System.out.println("Sort By Due Date clicked!");
		Collections.sort(tasks, Task.BY_DUE_DATE);
		gui.update(tasks);
	}

	// NOTE: For sorts, the Task class needs something implemented.
	private static void filterByComplete() {
		System.out.println("Filter By Complete clicked!");
		// TODO
		gui.update(tasks);
	}

	private void parse(File f) {
		// TODO: Need to open the file and get each line.
		// TODO: Parse the format ("TITLE, DESCRIPTION, DUE DATE, COMPLETED"),
		// TODO: Create a new Task object and add it to the ArrayList.
	}

	// Writes each task to the file after a change.
	private static void saveTasks() {
		System.out.println("writing tasks to " + filepath);
		// TODO: Write each Task stored in the ArrayList to the file.
	}

	///////////////////////// END OF TODO ////////////////////////////

	// Initialize tasks and GUI, populate with test data (if applicable).
	private void init() {
		filepath = getNotesDirectoryPath();
		initTasks();
		testData();
	}

	// Identify the operating system of the user, and obtain the path to the "tasks"
	// directory.
	private String getNotesDirectoryPath() {
		String os = System.getProperty("os.name").toLowerCase();

		String userHome = System.getProperty("user.home");
		String tasksDir = "tasks";

		if (os.contains("win")) // Windows
			return userHome + "\\" + tasksDir;
		else if (os.contains("mac")) // MacOS
			return userHome + "/Library/Application Support/" + tasksDir;
		else // Linux/other
			return userHome + "/" + tasksDir;
	}

	// Create the tasks file if it does not already exist. Load tasks from the
	// existing file otherwise.
	private void initTasks() {
		File f = new File(filepath);
		tasks = new ArrayList<Task>();

		if (f.isFile()) {
			System.out.println("loading tasks from " + filepath);
			parse(f);
		} else {
			try {
				f.createNewFile();
				System.out.println(filepath + " has been created.");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// Test tasks, not read from a file and are not intended to be saved to a file.
	private void testData() {
		for (int i = 0; i < 10; i++)
			tasks.add(new Task("Title " + i, "Description " + i, "Due Date " + i));
	}

	public static void main(String[] args) {
		new Week9().init();
		ActionListener sortByTitle = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByTitle();
			}
		};

		ActionListener sortByDueDate = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByDueDate();
			}
		};

		ActionListener filterByCompleted = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterByComplete();
			}
		};

		gui = new Week9Gui(tasks, sortByTitle, sortByDueDate, filterByCompleted);
	}

}