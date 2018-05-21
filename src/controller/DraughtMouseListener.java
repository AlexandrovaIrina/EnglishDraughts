package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraughtMouseListener extends MouseAdapter{




    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("ddd");

        JOptionPane.showMessageDialog(null,
                "WOW11",
                "Output",
                JOptionPane.PLAIN_MESSAGE);

        super.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        JOptionPane.showMessageDialog(null,
                "WOW",
                "Output",
                JOptionPane.PLAIN_MESSAGE);
    }
}
