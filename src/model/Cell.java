package model;

public class Cell {
    private final int xLocation;
    private final int yLocation;
    private final boolean free;
    private final CellColor color;

    public Cell (int xLocation, int yLocation, boolean free, CellColor color) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.free = free;
        this.color = color;
    }

    public boolean isFree() {
        return free;
    }

    public CellColor getColor() { return color; }

    public int getX() {
        return xLocation;
    }

    public int getY() {
        return yLocation;
    }
    public Cell makeFree() {return new Cell(xLocation, yLocation, true, color);}
    public Cell makeNotFree() {
        return new Cell(xLocation, yLocation, false, color);
    }
    private final static int cellXAlign = 49;
    private final static int cellYAlign = 50;
    private final static int cellLength = 64;

    public int transformX() {
        return xLocation * cellLength + cellXAlign;
    }
    public int transformY() {
        return yLocation * cellLength + cellYAlign;
    }
}
