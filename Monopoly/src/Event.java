import java.util.Scanner;

public class Event extends Square{

    Scanner sc = new Scanner(System.in);
    public Event(String name, int location, SquareType type) {
        super("World Cup", 20, SquareType.EVENT);
        //TODO Auto-generated constructor stub
    }

    public void setWorldCup(Board board, Player player){
        int choice = 0;
        
        while (true) {
            try {
                System.out.println("Please choose a city below to hold World Cup event: ");
                printCity(player);
                System.out.println("Your choice(input a number): ");
                choice = Integer.parseInt(sc.nextLine());
                if (isValidChoice(choice, player.getOwnedProperty().size())) {
                    player.getOwnedProperty().get(choice - 1).setVisitCost(player.getOwnedProperty().get(choice - 1).getVisitCost() * 10);
                    break;
                }
                else{
                    System.out.println("Please input a valid property number!!!");
                }
                
            } catch (Exception e) {
                System.out.println("Please input an integer!!!");
            }
        }
    }

    private boolean isValidChoice(int choice, int maxProperty){
        return choice >= 1 && choice <= maxProperty;
    }

    private void printCity(Player player){
        for(int i = 0; i < player.getOwnedProperty().size(); i++){
            System.out.println((i + 1) +". " + player.getOwnedProperty().get(i).getName() + "|Current visit cost: " + player.getOwnedProperty().get(i));
        }
    }
    
}
