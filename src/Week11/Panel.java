import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

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

        add(new Button("Example text", new Color(160, 32, 240), Color.WHITE));
    }
}
