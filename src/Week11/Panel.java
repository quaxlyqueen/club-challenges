import javax.swing.*;

import java.awt.*;

public class Panel extends JPanel {
    private Color bg;
    private Color fg;

    public Panel(Color bg, Color fg) {
        super();
            this.bg = bg;
            this.fg = fg;

        init();
    }

    private void init() {
        setBackground(bg);
        setForeground(fg);

        Button b = new Button("Example text", new Color(160, 32, 240), Color.WHITE);
            b.addActionListener(
                e -> {
                    System.out.println("Button action.");
                }
            );

        Button b2 = new Button("Example 2 text", new Color(160, 32, 240), Color.WHITE);
            b2.addActionListener(
                e -> {
                    System.out.println("Button 2 action.");
                }
            );

        add(b);
        add(b2);
    }
}
