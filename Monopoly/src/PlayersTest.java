
public class PlayersTest {
    public static void main(String[] args) {
        Dice dice1 = new Dice(0);
        Dice dice2 = new Dice(0);
        Players p1 = new Players("Thang");
        Players p2 = new Players("Nhat");
        
        
        
        // p2.move(dice1, dice2);
        // p1.rollDice(dice1, dice2);
        // p2.rollDice(dice1, dice2);
        //p1.freeFromJail(dice1, dice2);
        // System.out.println("Player name: " + p1.getName());
        // System.out.println("Money: " + p1.getMoney());
        // System.out.println("Location: " + p1.getLocation());
        // System.out.println("In Jail? " + p1.isInJail());
        
        // System.out.println("player 1: " + p1.rollDice(dice1, dice2));
        // System.out.println("Player 2: " + p2.rollDice(dice1, dice2));
        dice1.roll();
        dice2.roll();

        // System.out.println(p1.move(dice1, dice2));
        // p1.toJail(dice1, dice2);
        int x = p1.getLocationAfter(dice1, dice2);
        System.out.println(x);
        if(x == 10){
            System.out.println("Jail");
            p1.toJail(dice1, dice2);
        }
    }
}
