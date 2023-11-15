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
        // TODO: Add 2 buttons to the Panel object, and set the background and foreground colors.
    }
}
