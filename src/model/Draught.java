package model;

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

    public DraughtColor color() {
        return color;
    }
    public DraughtStatus getStatus() {
        return status;
    }
    public Cell getLocation() {
        return location;
    }

    public Draught changeStatus() {
        return new Draught(location, color, KING);
    }

    public void move(Cell newlocation) {}

    public void remove() {}
}
