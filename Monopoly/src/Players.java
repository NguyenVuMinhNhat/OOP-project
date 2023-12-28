import java.util.Scanner;

public class Players {
    private String name;
    private int money;
    private static final int NUM_PLAYERS = 4;
    private int location;
    private boolean inJail;
    private int jailTurn;
    private static final int TO_JAIL = 10;
    private int currentPlayerIndex;
    

    public Players(String name) {
        this.name = name;
        this.money = 2000000;
        this.location = 1;
        this.inJail = false;
        this.jailTurn = 0;
        this.currentPlayerIndex = 0;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public static int getNumPlayers() {
        return NUM_PLAYERS;
    }

    public int getLocation() {
        return location;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getJailTurn() {
        return jailTurn;
    }
    
    public void setLocation(int newLocation){
        this.location = newLocation;
    }

    public int move(Dice dice1, Dice dice2) {
        int player_step = dice1.getValueDice() + dice2.getValueDice();
        return player_step;
    }

    public int rollDice(Dice dice1, Dice dice2) {
        dice1.roll();
        dice2.roll();
        if (dice1.getValueDice() == dice2.getValueDice())
            return move(dice1, dice2) + rollDice(dice1, dice2);

        return move(dice1, dice2);
    }

    public boolean stayinJail() {
        if (jailTurn == 3) {
            inJail = false;
            return false;
        }
        return true;
    }
    private int propertyValue(){
        
    }
    public int totalMoney() {
        int total = money;
        total += propertyValue();
        return total;
    }

    public int Num_Beaches() {

    }
   
    public int getLocationAfter(Dice dice1, Dice dice2){
        location += move(dice1, dice2);
        return location;
    }
    public void toJail(Dice dice1, Dice dice2) {
            inJail = true;
            //freeFromJail(dice1, dice2);
            jailTurn = 0;
    } 
    

     public void freeFromJail(Dice dice1, Dice dice2) {
        Scanner sc = new Scanner(System.in);
        dice1.roll();
        dice2.roll();
        location = 10;
    
        boolean jailExit = false;
    
        while (!jailExit) {                
            if (dice1.getValueDice() == dice2.getValueDice()) {
                inJail = false;
                location += move(dice1, dice2);
                jailExit = true;
            } else {
                int choice = sc.nextInt();
                System.out.println("Choose an option: \n1. Pay 300000 and leave the Jail \n2. Stay 3 turns or roll doubles");
                switch (choice) {
                    case 1:
                        System.out.println("You choose option 1: ");
                        money -= 300000;
                        inJail = false;
                        location += move(dice1, dice2);
                        System.out.println(name + " is free now");
                        jailExit = true;
                        break;
    
                    case 2:
                        System.out.println("You chose option 2:");
                        jailTurn++;
                        if (jailTurn == 3) {
                            inJail = false;
                            jailExit = true;
                        }
                        break;
    
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }
        }
    }

    public void takeTurn(Dice dice1, Dice dice2) {
        if (isInJail()) {
            freeFromJail(dice1, dice2);
        } else {
            int steps = rollDice(dice1, dice2);
            int currentLocation = getLocation();
            int newLocation = getLocationAfter(dice1, dice2);
    
            System.out.println(getName() + " rolled a " + dice1.getValueDice() + " and a " + dice2.getValueDice() + ".");
            System.out.println(getName() + " moved from position " + currentLocation + " to position " + newLocation + ".");
            setLocation(newLocation); // Update the player's location
        }
    }
    
    public void playGame() {
        while (!isGameOver()) {
            Players currentPlayer = players[currentPlayerIndex];
            takeTurn(currentPlayer);
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length; // Move to the next player
        }
    }
}
