package model;

public class Player {

    private final String playerName;
    private final DraughtColor draughtColor;
    private boolean canPlay;
    private int score;

    public Player (String playerName, DraughtColor draughtColor, boolean canPlay) {
        this.draughtColor = draughtColor;
        this.playerName = playerName;
        this.canPlay = canPlay;
        score = 12;
    }
    public boolean getCanPlay() {
        return canPlay;
    }
    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }
    public String getName() {
        return playerName;
    }
    public DraughtColor getPlayer() {
        return draughtColor;
    }
    public int getScore() {return score;}
}
