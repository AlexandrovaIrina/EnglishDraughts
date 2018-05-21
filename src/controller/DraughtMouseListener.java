package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraughtMouseListener extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        JOptionPane.showMessageDialog(null,
                "WOW!!!",
                "Output",
                JOptionPane.PLAIN_MESSAGE);

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        JOptionPane.showMessageDialog(null,
                "WOW!",
                "Output",
                JOptionPane.PLAIN_MESSAGE);
    }
}
