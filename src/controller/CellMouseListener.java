package controller;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CellMouseListener extends MouseMotionAdapter{
    public void mouseDragged (MouseEvent e){
        super.mouseDragged(e);
        JOptionPane.showMessageDialog(null,
                "WOW",
                "Output",
                JOptionPane.PLAIN_MESSAGE);
    }
}
