import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Gui extends JFrame {
    public Gui() {
        super("Java Swing Challenge");
            init();

        add(new Panel(Color.DARK_GRAY, Color.WHITE));

        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(750, 550));
        setResizable(false);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error with cross platform look and feel.");
        }
    }
}
