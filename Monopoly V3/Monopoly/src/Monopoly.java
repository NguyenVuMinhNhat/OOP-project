
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Monopoly extends JPanel {
    Scanner sc = new Scanner(System.in);
    private Queue<Player> playerQueue;
    private int step;
    private GUIV2 gui;

    public GUIV2 getGui() {
        return gui;
    }

    public void setGui(GUIV2 gui) {
        this.gui = gui;
    }

    private static Monopoly instance;
    int numPlayers = 0;

    public Queue<Player> getPlayerQueue() {
        return playerQueue;
    }

    public void setPlayerQueue(Queue<Player> playerQueue) {
        this.playerQueue = playerQueue;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public static void main(String[] args) {

        Board board = new Board();
        Monopoly monopoly = new Monopoly();
        monopoly.initializeGame(board, monopoly);

    }

    public void initializeGame(Board board, Monopoly monopoly) {
        new StartMenuGUI(monopoly, board);

    }

    public boolean isGameOver(Board board) {
        int activePlayers = 0;
        for (Player player : board.getPlayers()) {
            if (!player.isBankrupt()) {
                activePlayers++;

            }
        }

        if (activePlayers <= 1) {
            return true;
        }

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).totalNumberOfBeach() == 4) {
                System.out.println("Player " + board.getPlayers().get(i).getName() + " has owned four beaches.");
                return true;
            }
        }

        return false;
    }

    public static Monopoly getInstance() {
        if (instance == null) {
            instance = new Monopoly();
        }
        return instance;
    }

    public void initializePlayerQueqe(Board board) {
        playerQueue = new LinkedList<>(board.getPlayers());
    }

    public void runRound(Board board, GUIV2 gui) {

        Player currentPlayer = playerQueue.poll();

        if (!currentPlayer.isInJail()) {
            takeTurn(currentPlayer, board, gui);
        } else if (currentPlayer.isInJail()) {
            currentPlayer.handleJailInteraction(board, gui);
        }

        playerQueue.offer(currentPlayer);
        if (currentPlayer.isBankrupt()) {
            System.out.println("Player " + currentPlayer.getName() + " is bankrupt!!!");
            removePlayerFromQueue(currentPlayer);
            gui.removePlayerFromGUI(board);
        }

    }

    public void removePlayerFromQueue(Player player) {
        playerQueue.remove(player);
    }

    public void takeTurn(Player player, Board board, GUIV2 gui) {
        System.out.println("Player " + player.getName() + "'s turn");

        if (player.isBankrupt()) {
            System.out.println("Player " + player.getName() + " is bankrupt!");
            return;
        }
        
        int currentLocation = player.getLocation();
        int step = player.rollDice();
        
        currentLocation += step;

        if (currentLocation > 40) {
            player.setCash(player.getCash() + 200000);
            System.out.println("Player " + player.getName() + " complete a full round and receive $200,000");
            System.out.println("Player " + player.getName() + "'s new balance: $" + player.getCash());
            currentLocation -= 40;
            player.setLocation(currentLocation);

        }

        else {
            player.setLocation(currentLocation);
        }

        gui.updatePlayerPosition(board, player);
        
        player.interactWithSquare(board, gui);
        gui.updatePlayerCash(board, player);
        
    }

    public void printWelcome(Board board) {

        System.out.println("---------Welcome to Monopoly---------");
        System.out.println("This game is made by Nguyen Vu Minh Nhat");
        System.out.println("Let's the game begin!!!!");

    }

    public void initializePlayers(Board board) {

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "How many players?", "Player number?",
                        JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    break;
                }
                numPlayers = Integer.parseInt(input);
                if (numPlayers > 1 && numPlayers <= 4) {
                    JOptionPane.showMessageDialog(null, "Number of players: " + numPlayers, "Player quantity",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else if (numPlayers == 1) {
                    JOptionPane.showMessageDialog(null, "There must be at least 2 players to start the game",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "This game only allow maximum 4 players",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Please input an integer", "ERROR: Invalid input!!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter player " + (i + 1) + " name: ", "Player name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                System.exit(0);
            }

            Color color = JColorChooser.showDialog(null, "Pick a color", Color.BLACK);
            Player player = new Player();
            player.setName(name);
            player.setColor(color);
            board.getPlayers().add(player);
            
        }
    }

    public void createSquare(Board board) {
        Start Go = new Start();
        board.getSquares().add(Go);
        City HongKong = new City("Hong Kong", 2, 50000, 0, 50000);
        board.getSquares().add(HongKong);
        City Beijing = new City("Beijing", 3, 52000, 0, 50000);
        board.getSquares().add(Beijing);
        City BangKok = new City("BangKok", 4, 55000, 0, 50000);
        board.getSquares().add(BangKok);
        City LonDon = new City("London", 5, 60000, 0, 50000);
        board.getSquares().add(LonDon);
        Beach Bali = new Beach("Bali", 6, 50000);
        board.getSquares().add(Bali);
        City Singapore = new City("Singapore", 7, 65000, 0, 50000);
        board.getSquares().add(Singapore);
        City Paris = new City("Paris", 8, 70000, 0, 50000);
        board.getSquares().add(Paris);
        City Dubai = new City("Dubai", 9, 75000, 0, 50000);
        board.getSquares().add(Dubai);
        City NewYork = new City("NewYork", 10, 80000, 0, 50000);
        board.getSquares().add(NewYork);
        Jail jail = new Jail();
        board.getSquares().add(jail);
        City KualaLumpur = new City("Kuala Lumpur", 12, 100000, 0, 70000);
        board.getSquares().add(KualaLumpur);
        City Istanbul = new City("Istanbul", 13, 110000, 0, 70000);
        board.getSquares().add(Istanbul);
        City Delhi = new City("Delhi", 14, 120000, 0, 70000);
        board.getSquares().add(Delhi);
        Card chance1 = new Card("Chance", 15);
        board.getSquares().add(chance1);
        City Mumbai = new City("Mumbai", 16, 130000, 0, 70000);
        board.getSquares().add(Mumbai);
        Beach Maldives = new Beach("Maldives", 17, 50000);
        board.getSquares().add(Maldives);
        City Rome = new City("Rome", 18, 140000, 0, 70000);
        board.getSquares().add(Rome);
        City SaoPaulo = new City("Sao Paulo", 19, 150000, 0, 70000);
        board.getSquares().add(SaoPaulo);
        City Taipei = new City("Taipei", 20, 160000, 00, 70000);
        board.getSquares().add(Taipei);
        Event event = new Event("World Cup", 21, SquareType.EVENT);
        board.getSquares().add(event);
        City Seoul = new City("Seoul", 22, 170000, 0, 80000);
        board.getSquares().add(Seoul);
        Beach HaLong = new Beach("Ha Long Bay", 23, 50000);
        board.getSquares().add(HaLong);
        City Barcelona = new City("Barcelona", 24, 185000, 0, 80000);
        board.getSquares().add(Barcelona);
        City LosAngles = new City("LosAngles", 25, 200000, 0, 80000);
        board.getSquares().add(LosAngles);
        Card chance2 = new Card("Chance", 26);
        board.getSquares().add(chance2);
        City HaNoi = new City("Ha Noi", 27, 215000, 0, 80000);
        board.getSquares().add(HaNoi);
        City Milan = new City("Milan", 28, 230000, 0, 80000);
        board.getSquares().add(Milan);
        City Berlin = new City("Berlin", 29, 245000, 0, 80000);
        board.getSquares().add(Berlin);
        City MosCow = new City("Moscow", 30, 260000, 0, 80000);
        board.getSquares().add(MosCow);
        Plane plane = new Plane();
        board.getSquares().add(plane);
        Beach Miami = new Beach("Miami", 32, 50000);
        board.getSquares().add(Miami);
        City Sydney = new City("Sydney", 33, 270000, 0, 100000);
        board.getSquares().add(Sydney);
        Tax tax = new Tax();
        board.getSquares().add(tax);
        City Shanghai = new City("Shanghai", 35, 270000, 0, 100000);
        board.getSquares().add(Shanghai);
        Card chance3 = new Card("Chance", 36);
        board.getSquares().add(chance3);
        City Madrid = new City("Madrid", 37, 300000, 0, 100000);
        board.getSquares().add(Madrid);
        City Venice = new City("Venice", 38, 330000, 0, 100000);
        board.getSquares().add(Venice);
        City Osaka = new City("Osaka", 39, 360000, 0, 100000);
        board.getSquares().add(Osaka);
        City Tokyo = new City("Tokyo", 40, 360000, 0, 100000);
        board.getSquares().add(Tokyo);
    }

    public void printResult(Board board) {
        System.out.println("-------------Game Over-------------");
        System.out.println("Results:");
        Player winner = new Player();
        for (Player player : board.getPlayers()) {
            if (!player.isBankrupt()) {
                winner = player;
            } else if (player.totalNumberOfBeach() == 4) {
                winner = player;

            }
        }
        System.out.println("The winner is: " + winner.getName());

        for (Player player : board.getPlayers()) {
            int totalMoney = player.getCash() + player.calculateTotalPropertyValue();
            System.out.println("Player " + player.getName() + ": $" + totalMoney);
        }

        // In ra bang ket qua tong so tien cua cac player sau round cuoi cung
    }
}
