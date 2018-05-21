package view;

import controller.CellMouseListener;
import controller.DraughtMouseListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    MainFrame(String s) {
        super(s);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage("GameField"), 5, 5, this);


            }
        };
        final int cellLength = 64;
        final int cellXAlign = 49;
        final int cellYAlign = 50;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel cell;
                if (i % 2 == j % 2) cell = new JLabel(getIcon("WhiteCell"));
                else {
                    cell = new JLabel((getIcon("BrownCell")));
                }
                cell.setBounds(j * cellLength,i * cellLength, cellLength, cellLength);
                cell.addMouseMotionListener(new CellMouseListener());
                panel.add(cell);
            }
        }
        final int draughtXAlign = 56;
        final int draughtYAlign = 58;
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel blackDraught = new JLabel(getIcon("BlackDraught"));
                blackDraught.setBounds((i % 2 + 2 * j) * cellLength + draughtXAlign,
                        (i - 1) * cellLength + draughtYAlign, cellLength, cellLength);
                blackDraught.addMouseMotionListener(new DraughtMouseListener());
                panel.add(blackDraught);
            }
        }
        for (int i = 6; i <= 8; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel whiteDraught = new JLabel(getIcon("WhiteDraught"));
                whiteDraught.setBounds((i % 2 + 2 * j) * cellLength + draughtXAlign,
                        (i - 1) * cellLength + draughtYAlign,
                        cellLength, cellLength);
                whiteDraught.addMouseMotionListener(new DraughtMouseListener());
                panel.add(whiteDraught);
            }
        }
        panel.setBounds(5, 5,2 * cellXAlign - 5 + cellLength * 8,
                2 * cellYAlign - 5 + cellLength * 8);
        add(panel);
        setSize(630, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private Image getImage(String name) {
        String im = "resources/" + name + ".png";
        ImageIcon icon = new ImageIcon(im);
        return icon.getImage();
    }

    private ImageIcon getIcon(String name) {
        String im = "resources/" + name + ".png";
        return new ImageIcon(im);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("English Draughts"));
    }
}
