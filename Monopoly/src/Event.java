import java.util.Scanner;

public class Event extends Square {

    Scanner sc = new Scanner(System.in);

    public Event(String name, int location, SquareType type) {
        super(name, location, type);
        // TODO Auto-generated constructor stub
    }

    public void setWorldCup(Board board, Player player, GUIV2 gui) {
        int choice;

        while (true) {
            try {
                System.out.println("Please choose a city below to hold World Cup event: ");
                printCity(player);
                System.out.println("Your choice (input a number): ");
                choice = gui.getUserInput();
                if (!board.getEventCity().isEmpty()) {
                    // Remove World Cup event from the previous player's chosen city
                    City previousEventCity = board.getEventCity().getFirst();
                    previousEventCity.setWorldCup(false);
                    previousEventCity.calculateVisitCost(false);
                    System.out.println("Player " + previousEventCity.getOwner().getName() +
                            " removed World Cup from " + previousEventCity.getName());
                    board.getEventCity().clear();
                }

                if (isValidChoice(choice, player.getOwnedProperty().size())) {

                    City chosenCity = (City) player.getOwnedProperty().get(choice - 1);
                    chosenCity.setWorldCup(true);
                    chosenCity.calculateVisitCost(true);

                    System.out.println("Player " + player.getName() + " successfully set World Cup at " +
                            chosenCity.getName());
                    System.out
                            .println(chosenCity.getName() + " visit cost increased to: $" + chosenCity.getVisitCost());

                    board.getEventCity().add(chosenCity);
                    chosenCity.setWorldCup(true);

                    break; // Exit the loop after successfully setting World Cup
                } else {
                    System.out.println("Please input a valid property number!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!!!");
            }
        }
    }

    private boolean isValidChoice(int choice, int maxProperty) {
        return choice >= 1 && choice <= maxProperty;
    }

    private void printCity(Player player) {
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (player.getOwnedProperty().get(i).getType() == SquareType.CITY) {
                System.out.println((i + 1) + ". " + player.getOwnedProperty().get(i).getName() + "|Current visit cost: "
                        + player.getOwnedProperty().get(i).getVisitCost());
            }

        }
    }

}
