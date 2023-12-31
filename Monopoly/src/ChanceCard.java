import java.util.Random;
import java.util.Scanner;

public class ChanceCard extends Square {
    Scanner sc = new Scanner(System.in);

    public ChanceCard(String name, int location, SquareType type) {
        super(name, location, type);
        // TODO Auto-generated constructor stub
    }

    public void EarthQuakeChanceCard(Player player) {
        System.out.println("Player " + player.getName()
                + " get EarthQuake card, a city will be destroyed because of Earth Quake.");
        Random random = new Random();
        int index = random.nextInt(player.getOwnedProperty().size());
        System.out
                .println("City " + player.getOwnedProperty().get(index).getName() + " is destroyed by Earth Quake!!!");
        player.getOwnedProperty().remove(index);
    }

    public void ReceiveMoneyChanceCard(Player player) {
        System.out.println("Player " + player.getName() + " get Money-Chance-Card");
        int amountMoney = player.getCash() * 5 / 100;
        System.out.println("Player " + player.getName() + " will receive $" + amountMoney);
        player.setCash(player.getCash() + amountMoney);
    }

    public void GiveOpponentCashChanceCard(Player player, Board board) {
        System.out.println("Player " + player.getName() + " get Give-Opponent-Cash-Chance-Card");
        System.out.println("Please choose player to give cash.");
        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).getName().equals(player.getName()) == false) {
                System.out.println((i + 1) + ". Player: " + board.getPlayers().get(i).getName());
            }
        }

        int choice = 0;
        while (true) {
            try {
                System.out.println("Please input your choice: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice <= board.getPlayers().size() && choice > 0) {
                    System.out.println(
                            "Player " + player.getName() + " choose " + board.getPlayers().get(choice).getName());
                    break;
                } else {
                    System.out.println("Please input valid choice!!!");
                }

            } catch (Exception e) {
                System.out.println("Please input an integer!!!");
            }
        }
        int amountMoney = player.getCash() / 10;
        System.out.println("Player " + player.getName() + " give $" + amountMoney + " to "
                + board.getPlayers().get(choice - 1).getName());
    }

    public void GiveOpponentCityChanceCard(Player player, Board board) {
        System.out.println("Player " + player.getName() + " get Give-Opponent-City-Chance-Card");
        System.out.println("Please choose player to give city.");
        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).getName().equals(player.getName()) == false) {
                System.out.println((i + 1) + ". Player: " + board.getPlayers().get(i).getName());
            }
        }

        int choicePlayer = 0;
        while (true) {
            try {
                System.out.println("Please input your choice (a number before others player's name): ");
                choicePlayer = Integer.parseInt(sc.nextLine());
                if (choicePlayer <= board.getPlayers().size() && choicePlayer > 0) {
                    System.out.println(
                            "Player " + player.getName() + " choose "
                                    + board.getPlayers().get(choicePlayer - 1).getName());
                    break;
                } else {
                    System.out.println("Please input valid choice!!!");
                }

            } catch (Exception e) {
                System.out.println("Please input an integer!!!");
            }
        }

        System.out.println("Please choose city you want to give away.");
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (player.getOwnedProperty().get(i).getType() == SquareType.CITY) {
                System.out.println(player.getOwnedProperty().get(i).getLocation() + ". "
                        + player.getOwnedProperty().get(i).getName() + "|Value: "
                        + player.getOwnedProperty().get(i).calculatePropertyValue());
            }

        }

        int choiceCity = 0;
        City choosenCity;
        while (true) {
            try {
                System.out.println("Please input the location of the city you want to give away: ");
                choiceCity = Integer.parseInt(sc.nextLine());
                boolean validChoice = false;
                for (PropertySquare property : player.getOwnedProperty()) {
                    if (property.getLocation() == choiceCity && property.getType() == SquareType.CITY) {
                        validChoice = true;
                        break;
                    }
                }
                if (validChoice) {
                    choosenCity = (City) board.getSquares().get(choiceCity - 1);
                    System.out.println("Player " + player.getName() + " choose City: "
                            + choosenCity.getName());
                    break;
                } else {
                    System.out.println("Please input a valid location of the city within your owned properties!!!");
                }
            } catch (Exception e) {
                System.out.println("Please input an integer!!!");
            }
        }

        Player receivingPlayer = board.getPlayers().get(choicePlayer - 1);

        System.out.println(
                "Player: " + player.getName() + " give " + player.getOwnedProperty().get(choiceCity - 1).getName()
                        + " to " + board.getPlayers().get(choicePlayer - 1).getName());

        player.getOwnedProperty().remove(choosenCity);
        receivingPlayer.getOwnedProperty().add(choosenCity);

    }

}
