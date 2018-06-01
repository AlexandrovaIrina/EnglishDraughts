package model;

import static java.lang.Math.abs;
import static model.DraughtStatus.*;
import static model.DraughtColor.*;

public class Draught {

    private final Cell location;
    private final DraughtColor color;
    private final DraughtStatus status;
    public Draught (Cell location, DraughtColor color, DraughtStatus status) {
        this.color = color;
        this.location = location;
        this.status = status;
    }

    public DraughtColor getColor() {
        return color;
    }
    public DraughtStatus getStatus() {
        return status;
    }
    public Cell getLocation() {
        return location;
    }

    public Draught changeStatus(Cell newCell) {
        if (status != KING) {
            if (color == BLACK && newCell.getX() == 0
                    || color == WHITE && newCell.getX() == 7) {
                return new Draught(newCell, color, KING);
            }
        }
        return this;
    }

    public boolean movable(Cell newCell) {
        int newX = newCell.getX();
        int newY = newCell.getY();
        if (status == MAN) {
            if (color == BLACK && newX - location.getX() == -1 && abs(newY - location.getY()) == 1)
                return true;
            if (color == WHITE && newX - location.getX() == 1 && abs(newY - location.getY()) == 1)
                return true;
        }
        else {
            if (abs(newX - location.getX()) == 1 && abs(newY - location.getY()) == 1) return true;
        }
        return false;
    }

    public boolean canKill(Cell newCell, Draught killingDraught) {
        int newX = newCell.getX();
        int newY = newCell.getY();
        if (status == MAN) {
            if (color == BLACK && killingDraught.getColor() == WHITE) {
                if (newX - location.getX() == -2 && abs(newY - location.getY()) == 2) return true;
            }
            if (color == WHITE && killingDraught.getColor() == BLACK) {
                if (newX - location.getX() == 2 && abs(newY - location.getY()) == 2) return true;
            }
        }
        else {
            if (color != killingDraught.getColor()) {
                if (abs(newX - location.getX()) == 2 && abs(newY - location.getY()) == 2) return true;
            }
        }
        return false;
    }


}
