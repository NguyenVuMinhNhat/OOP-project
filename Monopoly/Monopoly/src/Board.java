import java.util.ArrayList;
import java.util.List;

public class Board {
    Timer timer = new Timer();
    private List<Square> squares;
    private List<Player> players;
    private List<City> EventCity = new ArrayList<>();

    public Board() {
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

    public List<City> getEventCity() {
        return EventCity;
    }

    public void setEventCity(List<City> eventCity) {
        this.EventCity = eventCity;
    }

    public boolean isGameOver() {
        int activePlayers = 0;
        for (Player player : players) {
            if (!player.isBankrupt()) {
                activePlayers++;
            }
        }
        if (activePlayers <= 1) {
            return false;
        }

        for (Player player : players) {
            if (player.totalNumberOfBeach() == 4) {
                return false;
            }
        }

        if (timer.getRemainingTime() == 0) {
            return false;
        }

        return true;
    }

}