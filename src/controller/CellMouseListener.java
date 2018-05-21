package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellMouseListener extends MouseAdapter {
    @Override
    public void mousePressed (MouseEvent e){
        JOptionPane.showMessageDialog(null,
                "WOW!!",
                "Output",
                JOptionPane.PLAIN_MESSAGE);
    }
}
