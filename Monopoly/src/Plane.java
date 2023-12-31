import java.util.Scanner;

public class Plane extends Square {

    private int travelCost = 50000;
    
    Player player = new Player();

    public Plane() {
        super("World Tour", 30, SquareType.PLANE);

    }

    public void printLocation(Board board, Player player) {
        int numUnownedCity = 0;
        for(int i = 0; i < board.getSquares().size(); i++){
            if(board.getSquares().get(i).getType() == SquareType.CITY){
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
                        player.getOwnedProperty().get(i).getLocation() + ". " + player.getOwnedProperty().get(i).getName());
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
        while (!validChoice) {
            try {
                System.out.println("Your choice is: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 1 && choice <= player.getOwnedProperty().size() + board.getSquares().size()) {
                    // Check if choice matches any owned city location
                    for (int i = 0; i < player.getOwnedProperty().size(); i++) {
                        if (player.getOwnedProperty().get(i).getLocation() == choice) {
                            validChoice = true;
                            break;
                        }
                    }
                    if (!validChoice) {
                        for (int i = 0; i < board.getSquares().size(); i++) {
                            if (board.getSquares().get(i).getLocation() == choice) {
                                validChoice = true;
                                break;
                            }
                        }
                    }

                    if (validChoice) {
                        System.out.println("You choose: " + choice);
                        break;
                    } else {
                        System.out.println("Choice does not match any location. Please choose again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Please input valid integer!!!");
            }
        }
        switch (choice) {
            case 2:
                System.out.println("You move to Hong Kong.");
                player.setLocation(2);
                break;
            case 3:
                System.out.println("You move to BangKok.");
                player.setLocation(3);
                break;
            case 4:
                System.out.println("You move to LonDon.");
                player.setLocation(4);
                break;
            case 6:
                System.out.println("You move to LonDon.");
                player.setLocation(6);
                break;
            case 7:
                System.out.println("You move to Paris.");
                player.setLocation(7);
                break;
            case 8:
                System.out.println("You move to Dubai.");
                player.setLocation(8);
                break;
            case 9:
                System.out.println("You move to NewYork.");
                player.setLocation(9);
                break;
            case 11:
                System.out.println("You move to Kuala Lumpur.");
                player.setLocation(11);
                break;
            case 12:
                System.out.println("You move to Istanbul.");
                player.setLocation(8);
                break;
            case 13:
                System.out.println("You move to Delhi.");
                player.setLocation(13);
                break;
            case 15:
                System.out.println("You move to Mumbai.");
                player.setLocation(15);
                break;
            case 16:
                System.out.println("You move to Maldives.");
                player.setLocation(16);
                break;
            case 17:
                System.out.println("You move to Rome.");
                player.setLocation(17);
                break;
            case 18:
                System.out.println("You move to Sao Paulo.");
                player.setLocation(18);
                break;
            case 19:
                System.out.println("You move to Taipei.");
                player.setLocation(19);
                break;
            case 21:
                System.out.println("You move to Seoul.");
                player.setLocation(21);
                break;
            case 22:
                System.out.println("You move to Ha Long Bay.");
                player.setLocation(22);
                break;
            case 23:
                System.out.println("You move to Barcelona.");
                player.setLocation(23);
                break;
            case 24:
                System.out.println("You move to LosAngles.");
                player.setLocation(24);
                break;
            case 26:
                System.out.println("You move to Ha Noi.");
                player.setLocation(26);
                break;
            case 27:
                System.out.println("You move to Milan.");
                player.setLocation(27);
                break;
            case 28:
                System.out.println("You move to Berlin.");
                player.setLocation(28);
                break;
            case 29:
                System.out.println("You move to Moscow.");
                player.setLocation(29);
                break;
            case 31:
                System.out.println("You move to Da Nang.");
                player.setLocation(31);
                break;
            case 32:
                System.out.println("You move to Sydney.");
                player.setLocation(32);
                break;
            case 33:
                System.out.println("You move to Osaka.");
                player.setLocation(33);
                break;
            case 34:
                System.out.println("You move to Shanghai.");
                player.setLocation(34);
                break;
            case 36:
                System.out.println("You move to Madrid.");
                player.setLocation(36);
                break;
            case 38:
                System.out.println("You move to Chicago.");
                player.setLocation(38);
                break;
            case 39:
                System.out.println("You move to Stockholm.");
                player.setLocation(39);
                break;
            case 40:
                System.out.println("You move to Tokyo.");
                player.setLocation(40);
                break;
            // case 40 except location o nha tu, o xuat phat, o may bay, o bien, o thue, o
            // co hoi (chi cho bay den o thanh pho)
            default:
                break;
        }
    }
}
