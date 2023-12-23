import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Monopoly {
    public static void main(String[] args) {
        Board board = new Board();
        Monopoly monopoly = new Monopoly();
        do {
            monopoly.run(board);
        } while (board.isGameOver() != true);
        
    }

    public void run(Board board) {
        
        Square squareList = new Square();
        Start start = new Start();
        printWelcome(board);
        createSquare(squareList, board, start);
        takeTurn(board., board);
        int numPlayer = printWelcome(board);
        printResult(numPlayer, board);
    }

    public static void takeTurn(Players player, Board board){
        System.out.println("Player " + player.getName() + "'s turn");

        //roll dice

        //move player

        //board.handleTurn(player) hanh dong cua player len cac propetry, chance, events, etc
    }

    public int printWelcome(Board board) {
        System.out.println("---------Welcome to Monopoly---------");
        System.out.println("This game is made by Nguyen Vu Minh Nhat and Dao Quoc Thang");

        Scanner sc = new Scanner(System.in);
        int numPlayer = 0;
        while (true) {
            try {
                System.out.println("Please choose number of players: ");
                numPlayer = Integer.parseInt(sc.nextLine());
                if (numPlayer > 1 && numPlayer <= 4) {
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
            Players player = new Players();
            player.setName(name);
            board.addPlayers(player);
        }
        System.out.println("Let's the game begin!!!!");
        return numPlayer;
    }

    

    public void createSquare(Square squareList, Board board, Start start) {
        
        squareList.addSquare(start);
        Cities HongKong = new Cities("Hong Kong", 2, 50000, 1, 1, 50000);
        squareList.addSquare(HongKong);
        board.getUnOwnedCities().add(HongKong);
        Cities BangKok = new Cities("BangKok", 3, 55000, 1, 1, 50000);
        squareList.addSquare(BangKok);
        board.getUnOwnedCities().add(BangKok);
        Cities London = new Cities("London", 4, 60000, 1, 1, 50000);
        squareList.addSquare(London);
        board.getUnOwnedCities().add(London);
        Beaches Bali = new Beaches("Bali", 5, 50000, 1);
        squareList.addSquare(Bali);
        Cities Singapore = new Cities("Singapore", 6, 70000, 1, 1, 50000);
        squareList.addSquare(Singapore);
        board.getUnOwnedCities().add(Singapore);
        Cities Paris = new Cities("Paris", 7, 75000, 1, 1, 50000);
        squareList.addSquare(Paris);
        board.getUnOwnedCities().add(Paris);
        Cities Dubai = new Cities("Dubai", 8, 80000, 1, 1, 50000);
        squareList.addSquare(Dubai);
        board.getUnOwnedCities().add(Dubai);
        Cities NewYork = new Cities("New York", 9, 85000, 1, 1, 50000);
        squareList.addSquare(NewYork);
        board.getUnOwnedCities().add(NewYork);
        // 10 injail
        Jail jail = new Jail();
        squareList.addSquare(jail);
        Cities KualaLumpur = new Cities("Kuala Lumpur", 11, 100000, 1, 1, 70000);
        squareList.addSquare(KualaLumpur);
        board.getUnOwnedCities().add(KualaLumpur);
        Cities Istanbul = new Cities("Istanbul", 12, 110000, 1, 1, 70000);
        squareList.addSquare(Istanbul);
        board.getUnOwnedCities().add(Istanbul);
        Cities Delhi = new Cities("Delhi", 13, 120000, 1, 1, 70000);
        squareList.addSquare(Delhi);
        board.getUnOwnedCities().add(Delhi);
        // 14 o co hoi or khi van
        Cities Mumbai = new Cities("Mumbai", 15, 130000, 1, 1, 70000);
        squareList.addSquare(Mumbai);
        board.getUnOwnedCities().add(Mumbai);
        Beaches Maldives = new Beaches("Maldives", 16, 50000, 1);
        squareList.addSquare(Maldives);
        Cities Rome = new Cities("Rome", 17, 140000, 1, 1, 70000);
        squareList.addSquare(Rome);
        board.getUnOwnedCities().add(Rome);
        Cities SaoPaulo = new Cities("Sao Paulo", 18, 150000, 1, 1, 70000);
        squareList.addSquare(SaoPaulo);
        board.getUnOwnedCities().add(SaoPaulo);
        Cities Taipei = new Cities("Taipei", 19, 150000, 1, 1, 70000);
        squareList.addSquare(Taipei);
        board.getUnOwnedCities().add(Taipei);
        // 20 o event
        Cities Seoul = new Cities("Seoul", 21, 160000, 1, 1, 80000);
        squareList.addSquare(Seoul);
        board.getUnOwnedCities().add(Seoul);
        Beaches HaLong = new Beaches("Ha Long Bay", 22, 50000, 1);
        squareList.addSquare(HaLong);
        Cities Barcelona = new Cities("Barcelona", 23, 175000, 1, 1, 80000);
        squareList.addSquare(Barcelona);
        board.getUnOwnedCities().add(Barcelona);
        Cities LosAngles = new Cities("LosAngles", 24, 190000, 1, 1, 80000);
        squareList.addSquare(LosAngles);
        board.getUnOwnedCities().add(LosAngles);
        // 25 o co hoi or khi van
        Cities HaNoi = new Cities("Ha Noi", 26, 205000, 1, 1, 80000);
        squareList.addSquare(HaNoi);
        board.getUnOwnedCities().add(HaNoi);
        Cities Milan = new Cities("Milan", 27, 230000, 1, 1, 80000);
        squareList.addSquare(Milan);
        board.getUnOwnedCities().add(Milan);
        Cities Berlin = new Cities("Berlin", 28, 245000, 1, 1, 80000);
        squareList.addSquare(Berlin);
        board.getUnOwnedCities().add(Berlin);
        Cities Moscow = new Cities("Moscow", 29, 260000, 1, 1, 80000);
        squareList.addSquare(Moscow);
        board.getUnOwnedCities().add(Moscow);
        // 30 o may bay
        Plane plane = new Plane();
        squareList.addSquare(plane);
        Beaches DaNang = new Beaches("Da Nang", 31, 50000, 1);
        squareList.addSquare(DaNang);
        Cities Sydney = new Cities("Sydney", 32, 270000, 1, 1, 100000);
        squareList.addSquare(Sydney);
        board.getUnOwnedCities().add(Sydney);
        Cities Osaka = new Cities("Osaka", 33, 290000, 1, 1, 100000);
        squareList.addSquare(Osaka);
        board.getUnOwnedCities().add(Osaka);
        Cities ShangHai = new Cities("Shanghai", 34, 310000, 1, 1, 100000);
        squareList.addSquare(ShangHai);
        board.getUnOwnedCities().add(ShangHai);
        // 35 o co hoi or khi van
        Cities Madrid = new Cities("Madrid", 36, 330000, 1, 1, 100000);
        squareList.addSquare(Madrid);
        board.getUnOwnedCities().add(Madrid);
        // 37 o thue
        Cities Chicago = new Cities("Chicago", 38, 350000, 1, 1, 100000);
        squareList.addSquare(Chicago);
        board.getUnOwnedCities().add(Chicago);
        Cities Stockholm = new Cities("Stockholm", 39, 270000, 1, 1, 100000);
        squareList.addSquare(Stockholm);
        board.getUnOwnedCities().add(Stockholm);
        Cities Tokyo = new Cities("Tokyo", 40, 300000, 1, 1, 100000);
        squareList.addSquare(Tokyo);
        board.getUnOwnedCities().add(Tokyo);
    }

    public void printResult(int numPlayer, Board board){
        System.out.println("The winner is: " + );
        String [][] resultTable = new String[numPlayer][2];
        
        for(int i = 0; i <= resultTable.length; i++){
            
            for(int j = 0; j <= resultTable[i].length; j++){

            }
        }
        //In ra bang ket qua tong so tien cua cac player sau round cuoi cung
    }
}
