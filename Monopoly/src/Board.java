import java.util.ArrayList;
import java.util.List;

public class Board {
    Timer timer = new Timer();
    private List<Square> squares;
    private List<Player> players;

    public Board(){
        this.squares = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
}