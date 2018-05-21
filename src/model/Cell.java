package model;

import static java.lang.Character.valueOf;

public class Cell {
    private final String location;
    private final CellColor color;
    private final boolean free;

    public Cell (String location, CellColor color, boolean free) {
        this.location = location;
        this.color = color;
        this.free = free;
    }

    public boolean isFree() {
        return free;
    }
    public String getLocation() {
        return location;
    }
    public CellColor getColor() {
        return color;
    }
    public int getX() {
        return valueOf(location.charAt(1)) - 1;
    }
    public int getY() {
        switch (location.charAt(0)) {
            case 'A' : return 0;
            case 'B' : return 1;
            case 'C' : return 2;
            case 'D' : return 3;
            case 'E' : return 4;
            case 'F' : return 5;
            case 'G' : return 6;
            case 'H' : return 7;
        }
        return 8;
    }
}
