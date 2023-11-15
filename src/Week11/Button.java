import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class Button extends JButton {
    private boolean selected;
    private Color bg;
    private Color fg;

    /**
     * @param String buttonLabel
     * @param Color fg
     * @param Color bg
     */
    public Button(String buttonLabel, Color bg, Color fg) {
        super(buttonLabel);
            this.bg = bg;
            this.fg = fg;

        applyCommon();
        deselect();
    }

    // Apply default appearance to the button upon exiting the bounds.
    public void deselect() {
        selected = false;
        setBackground(bg);
        setForeground(fg);
    }

    // Apply focused appearance to the button upon hover.
    public void select() {
        selected = true;
        setBackground(fg);
        setForeground(bg);
    }

    // Apply hover functionality to all buttons, as well as removing certain default stylings.
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
                        repaint();
                        revalidate();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        deselect();
                        repaint();
                        revalidate();
                    }
                });

        setFocusPainted(false);
        setBorder(new EmptyBorder(3,3,3,3));

        repaint();
        revalidate();
    }
}
