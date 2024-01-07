import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Monopoly {
    Scanner sc = new Scanner(System.in);
    private Queue<Player> playerQueue;
    private static Monopoly instance;

    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly();

        monopoly.runGame();

    }

    public void runGame() {
        Board board = new Board();
        printWelcome(board);
        createSquare(board);
        while (board.isGameOver()) {
            runRound(board);
        }

        printResult(board);
    }

    public static Monopoly getInstance() {
        if (instance == null) {
            instance = new Monopoly();
        }
        return instance;
    }

    private void initializePlayerQueqe(Board board) {
        playerQueue = new LinkedList<>(board.getPlayers());
    }

    private void runRound(Board board) {
        initializePlayerQueqe(board);
        while (!playerQueue.isEmpty()) {
            Player currentPlayer = playerQueue.poll();
            if (!currentPlayer.isBankRupt()) {
                System.out.println();
                System.out.println();
                takeTurn(currentPlayer, board);
            }

            playerQueue.offer(currentPlayer);
            try {
                
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void removePlayerFromQueue(Player player) {
        playerQueue.remove(player);
    }

    public static void takeTurn(Player player, Board board) {
        System.out.println("Player " + player.getName() + "'s turn");

        if (player.isBankRupt()) {
            System.out.println("Player " + player.getName() + " is bankrupt!");
            return;
        }
       

        int currentLocation = player.getLocation();
        System.out.println("Player " + player.getName() + " current location: " + currentLocation);
        int step = player.rollDice();

        currentLocation += step;
        System.out.println("Player " + player.getName() + " current location after roll: " + currentLocation);
        if (currentLocation > 40) {
            player.setCash(player.getCash() + 200000);
            System.out.println("Player " + player.getName() + " complete a full round and receive $200,000");
            System.out.println("Player " + player.getName() + "'s new balacnce: $" + player.getCash());
            currentLocation -= 40;
            player.setLocation(currentLocation);

        } 
        
        else {
            player.setLocation(currentLocation);
        }
        System.out.println("Player " + player.getName() + " after location : " + currentLocation);

        player.interactWithSquare(board);

        if (player.isBankRupt()) {
            System.out.println("Player " + player.getName() + " is bankrupt!");
            Monopoly.getInstance().removePlayerFromQueue(player);
        }
    }

    public void printWelcome(Board board) {
        System.out.println("---------Welcome to Monopoly---------");
        System.out.println("This game is made by Nguyen Vu Minh Nhat and Dao Quoc Thang");
        initializePlayers(board);

        System.out.println("Let's the game begin!!!!");
    }

    public void initializePlayers(Board board) {
        int numPlayer = 0;
        while (true) {
            try {
                System.out.println("Please choose number of players: ");
                numPlayer = Integer.parseInt(sc.nextLine());
                if (numPlayer >= 1 && numPlayer <= 4) {
                    System.out.println("Number of players: " + numPlayer);
                    break;
                } else if (numPlayer == 1) {
                    System.out.println("There must be at least two players.");
                } else {
                    System.out.println("This game only allow 4 players.\n" + "Please choose valid number of player!!!");
                }

            } catch (Exception e) {
                System.out.println("Please input a valid number!!!");
            }
        }

        for (int i = 0; i < numPlayer; i++) {
            System.out.printf("Input name of player %d:", i + 1);
            String name = sc.nextLine();
            Player player = new Player();
            player.setName(name);
            board.getPlayers().add(player);
        }
    }

    public void createSquare(Board board) {

        City HongKong = new City("Hong Kong", 2, 50000, 1, 50000);
        board.getSquares().add(HongKong);
        City BangKok = new City("BangKok", 3, 55000, 1, 50000);
        board.getSquares().add(BangKok);
        City LonDon = new City("London", 4, 60000, 1, 50000);
        board.getSquares().add(LonDon);
        Beach Bali = new Beach("Bali", 5, 50000);
        board.getSquares().add(Bali);
        City Singapore = new City("Singapore", 6, 65000, 1, 50000);
        board.getSquares().add(Singapore);
        City Paris = new City("Paris", 7, 70000, 1, 50000);
        board.getSquares().add(Paris);
        City Dubai = new City("Dubai", 8, 75000, 1, 50000);
        board.getSquares().add(Dubai);
        City NewYork = new City("New York", 9, 80000, 1, 50000);
        board.getSquares().add(NewYork);
        Jail jail = new Jail();
        board.getSquares().add(jail);
        City KualaLumpur = new City("Kuala Lumpur", 11, 100000, 1, 70000);
        board.getSquares().add(KualaLumpur);
        City Istanbul = new City("Istanbul", 12, 110000, 1, 70000);
        board.getSquares().add(Istanbul);
        City Delhi = new City("Delhi", 13, 120000, 1, 70000);
        board.getSquares().add(Delhi);
        Card chance1 = new Card("Chance", 14);
        board.getSquares().add(chance1);
        City Mumbai = new City("Mumbai", 15, 130000, 1, 70000);
        board.getSquares().add(Mumbai);
        Beach Maldives = new Beach("Maldives", 16, 50000);
        board.getSquares().add(Maldives);
        City Rome = new City("Rome", 17, 140000, 1, 70000);
        board.getSquares().add(Rome);
        City SaoPaulo = new City("Sao Paulo", 18, 150000, 1, 70000);
        board.getSquares().add(SaoPaulo);
        City Taipei = new City("Taipei", 19, 160000, 1, 70000);
        board.getSquares().add(Taipei);
        Event event = new Event("World Cup", 20, SquareType.EVENT);
        board.getSquares().add(event);
        City Seoul = new City("Seoul", 21, 170000, 1, 80000);
        board.getSquares().add(Seoul);
        Beach HaLong = new Beach("Ha Long Bay", 22, 50000);
        board.getSquares().add(HaLong);
        City Barcelona = new City("Barcelona", 23, 185000, 1, 80000);
        board.getSquares().add(Barcelona);
        City LosAngles = new City("LosAngles", 24, 200000, 1, 80000);
        board.getSquares().add(LosAngles);
        Card chance2 = new Card("Chance", 25);
        board.getSquares().add(chance2);
        City HaNoi = new City("Ha Noi", 26, 215000, 1, 80000);
        board.getSquares().add(HaNoi);
        City Milan = new City("Milan", 27, 230000, 1, 80000);
        board.getSquares().add(Milan);
        City Berlin = new City("Berlin", 28, 245000, 1, 80000);
        board.getSquares().add(Berlin);
        City MosCow = new City("Moscow", 29, 260000, 1, 80000);
        board.getSquares().add(MosCow);
        Plane plane = new Plane();
        board.getSquares().add(plane);
        Beach Miami = new Beach("Miami", 31, 50000);
        board.getSquares().add(Miami);
        City Sydney = new City("Sydney", 32, 270000, 1, 100000);
        board.getSquares().add(Sydney);
        City Osaka = new City("Osaka", 33, 290000, 1, 100000);
        board.getSquares().add(Osaka);
        City Shanghai = new City("Shanghai", 34, 270000, 1, 100000);
        board.getSquares().add(Shanghai);
        Card chance3 = new Card("Chance", 35);
        board.getSquares().add(chance3);
        City Madrid = new City("Madrid", 36, 300000, 1, 100000);
        board.getSquares().add(Madrid);
        Tax tax = new Tax();
        board.getSquares().add(tax);
        City Chicago = new City("Chicago", 38, 350000, 1, 100000);
        board.getSquares().add(Chicago);
        City Stockholm = new City("Stockholm", 39, 370000, 1, 100000);
        board.getSquares().add(Stockholm);
        City Tokyo = new City("Tokyo", 40, 400000, 1, 100000);
        board.getSquares().add(Tokyo);

    }

    public void printResult(Board board) {
        System.out.println("-------------Game Over-------------");
        System.out.println("Results:");
        Player winner = new Player();
        for (Player player : board.getPlayers()) {
            if (!player.isBankRupt()) {
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
