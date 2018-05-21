package model;

public class Player {

    private final String playerName;
    private final DraughtColor draughtColor;

    public Player (String playerName, DraughtColor draughtColor) {
        this.draughtColor = draughtColor;
        this.playerName = playerName;
    }

    public String getName() {
        return playerName;
    }
    public DraughtColor getDraughtColor() {
        return draughtColor;
    }
}
