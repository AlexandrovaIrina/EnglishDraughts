package view;

import static model.CellColor.BROWN;
import static model.DraughtColor.WHITE;
import static model.DraughtStatus.MAN;
import static model.DraughtColor.BLACK;
import static model.CellColor.BEIGE;

import model.*;

import javax.swing.*;
import java.awt.*;

import controller.Controller;

public class MainFrame extends JFrame {
    private final int cellLength = 64;
    private Draught[][] draughts = new Draught[8][8];
    private Cell[][] cells = new Cell[8][8];
    private Controller controller = new Controller(this, cells);
    private final static int cellXAlign = 49;
    private final static int cellYAlign = 50;
    private JPanel panel;
    MainFrame(String s) {
        super(s);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage("GameField"), 5, 5, this);

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Image cell;
                        if (i % 2 == j % 2) {
                            cell = getImage("WhiteCell");
                            cells[i][j] = new Cell(i, j, true, BEIGE);
                        }
                        else {
                            cell = getImage("BrownCell");
                            cells[i][j] = new Cell(i,
                                    j, true, BROWN);
                            controller.setCell(i, j, cells[i][j]);
                        }
                        g.drawImage(cell, cells[i][j].transformX(), cells[i][j].transformY(), this);

                    }
                }
                final int draughtXAlign = 56;
                final int draughtYAlign = 58;
                for (int i = 1; i <= 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        Image whiteDraught = getImage("WhiteDraught");
                        g.drawImage(whiteDraught, (i % 2 + 2 * j) * cellLength + draughtXAlign,
                                (i - 1) * cellLength + draughtYAlign, this);
                        cells[i - 1][i % 2 + 2 * j] = new Cell(i - 1, i % 2 + 2 * j, false, BROWN);
                        controller.setCell(i - 1, i % 2 + 2 * j, cells[i - 1][i % 2 + 2 * j]);
                        draughts[i - 1][i % 2 + 2 * j] = new Draught(cells[i - 1][i % 2 + 2 * j], WHITE, MAN);
                    }
                }
                for (int i = 6; i <= 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        Image blackDraught = getImage("BlackDraught");
                        g.drawImage(blackDraught, (i % 2 + 2 * j) * cellLength + draughtXAlign,
                                (i - 1) * cellLength + draughtYAlign, this);
                        cells[i - 1][i % 2 + 2 * j] = new Cell(i - 1, i % 2 + 2 * j, false, BROWN);
                        controller.setCell(i - 1, i % 2 + 2 * j, cells[i - 1][i % 2 + 2 * j]);
                        draughts[i - 1][i % 2 + 2 * j] = new Draught(cells[i - 1][i % 2 + 2 * j], BLACK, MAN);

                    }
                }
                this.addMouseListener(controller.getCMME());
            }
        };
        add(panel);
        setSize(630, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    private Player firstPlayer = new Player("Player1", BLACK, true);
    private Player secondPlayer = new Player("Player2", WHITE, false);
    public void moveDraught(Cell lastCell, Cell newCell) {
        Graphics g = panel.getGraphics();
        int lastX = lastCell.getX();
        int lastY = lastCell.getY();
        int newX = newCell.getX();
        int newY = newCell.getY();
        int killingDraughtX = (lastCell.getX() + newCell.getX()) / 2;
        int killingDraughtY = (lastCell.getY() + newCell.getY()) / 2;
        Draught draught = new Draught(lastCell,
                draughts[lastX][lastY].getColor(),
                draughts[lastX][lastY].getStatus());
        Draught killingDraught = draughts[killingDraughtX][killingDraughtY];
        boolean canKill = false;
        if(killingDraught != null) canKill = draught.canKill(newCell, killingDraught);
        if (draught.movable(newCell) || canKill){
            draught = draught.changeStatus(newCell);

            if(draught.getColor() == BLACK && firstPlayer.getCanPlay()) {
                if(draught.getStatus() == MAN) drawMovingDraught(g, panel, "BlackDraught", newCell);
                else drawMovingDraught(g, panel, "BlackKingDraught", newCell);
                g.drawImage(getImage("BrownCell"), lastCell.transformY() - 1,
                        lastCell.transformX() + 1, panel);
                if (!canMoveMore(draught)) {
                    firstPlayer.setCanPlay(false);
                }
                secondPlayer.setCanPlay(true);
            }
            if(draught.getColor() == WHITE && secondPlayer.getCanPlay()) {
                if(draught.getStatus() == MAN) drawMovingDraught(g, panel, "WhiteDraught", newCell);
                else drawMovingDraught(g, panel, "WhiteKingDraught", newCell);
                g.drawImage(getImage("BrownCell"), lastCell.transformY() - 1,
                        lastCell.transformX() + 1, panel);
                if (!canMoveMore(draught)) {
                    secondPlayer.setCanPlay(false);
                }
                firstPlayer.setCanPlay(true);

            }
            if(canKill) {
                g.drawImage(getImage("BrownCell"), killingDraughtY * cellLength + cellXAlign,
                        killingDraughtX * cellLength + cellYAlign, panel);
                draughts[killingDraughtX][killingDraughtY] = null;
                cells[killingDraughtX][killingDraughtY].makeFree();
                controller.setCell(killingDraughtX, killingDraughtY, cells[killingDraughtX][killingDraughtY]);
            }
            cells[lastX][lastY].makeFree();
            cells[newX][newY].makeNotFree();
            controller.setCell(lastX, lastY, cells[lastX][lastY]);
            controller.setCell(newX, newY, cells[newX][newY]);
            draughts[newCell.getX()][newCell.getY()] = draught;
            draughts[lastX][lastY] = null;
            panel.paintComponents(g);
        }
    }
    private boolean canMoveMore(Draught draught) {
        int lastCellX = draught.getLocation().getX();
        int lastCellY = draught.getLocation().getY();
        int newX = lastCellX - 2;
        int newY = lastCellY - 2;
        int killingDraughtX = (newX + lastCellX) / 2;
        int killingDraughtY = (newY + lastCellY) / 2;
        if (killingDraughtX > 0 && killingDraughtY > 0 &&
                canMove(draught, killingDraughtX, killingDraughtY, newX, newY))
            return true;
        newY = lastCellY + 2;
        killingDraughtY = (newY + lastCellY) / 2;
        if (killingDraughtX > 0 && killingDraughtY < 7 &&
                canMove(draught, killingDraughtX, killingDraughtY, newX, newY))
            return true;
        newX = lastCellX + 2;
        killingDraughtX = (newX + lastCellX) / 2;
        if (killingDraughtX < 7 && killingDraughtY < 7 &&
                canMove(draught, killingDraughtX, killingDraughtY, newX, newY))
            return true;
        newY = lastCellY - 2;
        killingDraughtY = (newY + lastCellY) / 2;
        if (killingDraughtX < 7 && killingDraughtY > 0 &&
                canMove(draught, killingDraughtX, killingDraughtY, newX, newY))
            return true;
        return false;
    }
    private boolean canMove (Draught draught, int killingDraughtX, int killingDraughtY, int newX, int newY){
        Draught killingDraught = draughts[killingDraughtX][killingDraughtY];
        if (killingDraught != null) {
            boolean canKill = draught.canKill(cells[newX][newY], killingDraught);
            if (canKill) return true;
        }
        return false;
    }
    private static void drawMovingDraught(Graphics g, JPanel panel, String draught, Cell newCell) {
        g.drawImage(getImage(draught),
                newCell.transformY() + 7,
                newCell.transformX() + 8, panel);
    }

    private static Image getImage(String name) {
        String im = "resources/" + name + ".png";
        ImageIcon icon = new ImageIcon(im);
        return icon.getImage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("English Draughts"));
    }
}
