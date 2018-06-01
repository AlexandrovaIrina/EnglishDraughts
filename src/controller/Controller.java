package controller;

import view.MainFrame;
import model.Cell;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static model.CellColor.BROWN;

public class Controller {
    private final MainFrame mainFrame;
    private Cell[][] cells;
    private int clicks = 0;
    private Cell lastCell;
    private final CellMouseMotionEvent cMME;

    public Controller(MainFrame mainFrame, Cell[][] cells) {
        cMME = new CellMouseMotionEvent();
        this.mainFrame = mainFrame;
        this.cells = cells;
    }

    public CellMouseMotionEvent getCMME() {
        return cMME;
    }
    public void setCell(int x, int y, Cell newCell) {
        this.cells[x][y] = newCell;
    }
    private final static int cellXAlign = 49;
    private final static int cellLength = 64;
    private final static int cellYAlign = 50;

    public class CellMouseMotionEvent  extends MouseInputAdapter implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x > cellXAlign && y > cellYAlign
                    && x < cellXAlign + cellLength * 8 && y < cellYAlign + cellLength * 8){
                x = (x - cellXAlign) / cellLength;
                y = (y - cellYAlign) / cellLength;
                super.mouseClicked(e);
                if(cells[y][x].getColor() == BROWN) {
                    if (clicks == 1 && lastCell != cells[y][x] && cells[y][x].isFree()) {
                        System.out.println("mouse clicked2");
                        mainFrame.moveDraught(lastCell, cells[y][x]);
                        clicks = 0;
                    } else {
                        if(!cells[y][x].isFree()){
                            System.out.println("mouse clicked1");
                            lastCell = cells[y][x];
                            clicks = 1;
                        }
                    }
                }
            }

        }
    }
}
