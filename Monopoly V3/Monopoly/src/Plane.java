import java.util.ArrayList;
import java.util.Scanner;

public class Plane extends Square {

    
    private ArrayList<City> availableCities = new ArrayList<>();
    

    public Plane() {
        super("World Tour", 31, SquareType.PLANE);

    }

    public void printLocation(Board board, Player player) {
        availableCities.clear();
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (player.getOwnedProperty().get(i).getType() == SquareType.CITY) {
                City city = (City) player.getOwnedProperty().get(i);

                availableCities.add(city);

            }
        }

        for (int i = 0; i < board.getSquares().size(); i++) {
            if (board.getSquares().get(i) instanceof City) {
                City city = (City) board.getSquares().get(i);
                if (city.getOwner() == null) {
                    availableCities.add(city);
                }
            }
        }

        for (int i = 0; i < availableCities.size(); i++) {

            System.out.println((i + 1) + ". " + availableCities.get(i).getName() + " (Location: "
                    + availableCities.get(i).getPosition() + ", Level: "
                    + availableCities.get(i).getLevel() + ")");
        }

    }

    public void travel(Board board, Player player, GUIV2 gui) {
        printLocation(board, player);
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (true) {
            try {
                System.out.println("Please input your choice (the number before each city): ");
                choice = gui.getUserInputNumber();
                if (choice >= 1 && choice <= availableCities.size()) {
                    System.out.println(
                            "Player " + player.getName() + " choose "
                                    + availableCities.get(choice - 1).getName());
                    break;
                } else {
                    System.out.println("Please choose a valid number!!!");
                }

            } catch (Exception e) {
                System.out.println("Please input an integer!!!");
            }
        }
        System.out.println(
                "Player " + player.getName() + " move to " + availableCities.get(choice - 1).getName());
        player.setLocation(availableCities.get(choice - 1).getPosition());
        System.out.println("Player " + player.getName() + " new location: " + player.getLocation());
        player.interactWithSquare(board, gui);
        gui.updatePlayerPosition(board, player);
    }
}
