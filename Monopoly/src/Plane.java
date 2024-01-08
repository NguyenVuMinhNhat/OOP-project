import java.util.Scanner;

public class Plane extends Square {

    private int travelCost = 50000;

    Player player = new Player();

    public Plane() {
        super("World Tour", 30, SquareType.PLANE);

    }

    public void printLocation(Board board, Player player) {
        int numUnownedCity = 0;
        for (int i = 0; i < board.getSquares().size(); i++) {
            if (board.getSquares().get(i).getType() == SquareType.CITY) {
                City city = (City) board.getSquares().get(i);
                if (!city.isOwned()) {
                    numUnownedCity++;
                }
            }
        }

        int maxSize = Math.max(player.getOwnedProperty().size(), numUnownedCity);
        System.out.println("Please choose one of these options by typing the keyboard");
        for (int i = 0; i < maxSize; i++) {
            if (player.getOwnedProperty().get(i).getType() == SquareType.CITY) {
                System.out.print(
                        player.getOwnedProperty().get(i).getLocation() + ". "
                                + player.getOwnedProperty().get(i).getName());
                System.out.println();
            }
            if (i < board.getSquares().size()) {
                System.out.printf(
                        board.getSquares().get(i).getLocation() + ". "
                                + board.getSquares().get(i).getName());
                System.out.println();
            }
        }
    }

    public void travel(Board board, Player player) {
        printLocation(board, player);
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        boolean validChoice = false;
        while (true) {
            try {
                System.out.println("Your choice is: ");
                choice = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < player.getOwnedProperty().size(); i++){
                    if(player.getOwnedProperty().get(i).getLocation() == choice);
                    validChoice = true;
                    break;
                }
                if (!validChoice) {
                    System.out.println("Choice does not match any owned city.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please input valid integer!!!");
            }
        }
        System.out.println("Player " + player.getName() + " move to " + player.getOwnedProperty().get(choice - 1).getName());
        player.setLocation(player.getOwnedProperty().get(choice - 1).getLocation());
        System.out.println("Player " + player.getName() + " new location: " + player.getLocation());
        
        
    }
}
