import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Week9Gui {
    private JFrame frame = new JFrame("SLCC Programming Club Challenge - Task Manager");

    private ActionListener titleSort;
    private ActionListener dueDateSort;
    private ActionListener completeFilter;
    private ActionListener addBtn;

    public Week9Gui(ArrayList<Task> tasks, ActionListener titleSort, ActionListener dueDateSort, ActionListener completeFilter, ActionListener addBtn) {
        this.titleSort = titleSort;
        this.dueDateSort = dueDateSort;
        this.completeFilter = completeFilter;
        this.addBtn = addBtn;
        initGui(tasks);
    }

    // Setup the basic GUI layout.
    private void initGui(ArrayList<Task> tasks) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(600, 450));
        frame.setSize(frame.getPreferredSize());
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error with cross platform look & feel.");
        }

        frame.getContentPane().add(buttonsView(), BorderLayout.PAGE_END);
        frame.getContentPane().add(view(tasks));

        frame.setVisible(true);
    }

    // Displays sort and filter buttons
    private JPanel buttonsView() {
        JButton sortByTitle = new JButton("Title");
            sortByTitle.addActionListener(titleSort);

        JButton sortByDueDate = new JButton("Due Date");
            sortByDueDate.addActionListener(dueDateSort);

        JButton filterByComplete = new JButton("Complete");
            filterByComplete.addActionListener(completeFilter);
        
        JButton addBtn = new JButton("Add");
           	addBtn.addActionListener(this.addBtn);
            
            

        JPanel p = new JPanel(new GridLayout(0, 4));
            p.add(new JLabel("Sort by:"));
            p.add(new JLabel("Sort by:"));
            p.add(new JLabel("Filter by:"));
            p.add(new JLabel("Add to:"));
            p.add(sortByTitle);
            p.add(sortByDueDate);
            p.add(filterByComplete);
            p.add(addBtn);

        return p;
    }

    // Displays tasks and their completion status.
    private JPanel view(ArrayList<Task> tasks) {
        JPanel v = new JPanel(new GridLayout(tasks.size(), 4));

        for(Task t : tasks) {
            v.add(new JLabel(t.getTitle()));
            v.add(new JLabel(t.getDescription()));
            v.add(new JLabel(t.getDueDate()));
            JCheckBox toggleComplete = new JCheckBox("Completed", t.isCompleted());
                toggleComplete.addActionListener(
                    e -> { t.toggleComplete(); }
                );
            v.add(toggleComplete);
        }

        return v;
    }

    // Clear everything and refresh the JFrame to update information.
    public void update(ArrayList<Task> tasks) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(buttonsView(), BorderLayout.PAGE_END);
        frame.getContentPane().add(view(tasks));
            frame.repaint();
            frame.revalidate();
            frame.pack();
        System.out.println("Updated UI");
    }
}
