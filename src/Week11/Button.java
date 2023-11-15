import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {
    private boolean selected;
    private Color bg;
    private Color fg;

    public Button(String buttonLabel, Color bg, Color fg) {
        super(buttonLabel);
            this.bg = bg;
            this.fg = fg;

        deselect();
    }

    public void deselect() {
        selected = false;
        applyDefaultStyle();
    }

    public void select() {
        selected = true;
        applyFocusedStyle();
    }

    private void applyDefaultStyle() {
        setBackground(bg);
        setForeground(fg);
    }

    private void applyFocusedStyle() {
        setBackground(fg);
        setForeground(bg);
    }

    private void applyCommon() {
        addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        select();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        deselect();
                    }
                });

        setFocusPainted(false);
        setBorder(new EmptyBorder(3,3,3,3));

        repaint();
        revalidate();
    }
}
