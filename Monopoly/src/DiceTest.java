public class DiceTest {
    public static void main(String[] args) {
        Dice dice1 = new Dice(0);
        Dice dice2 = new Dice(0);
        dice1.roll();
        dice2.roll();
        System.out.println("Dice value 1: " + dice1.roll());
        System.out.println("Dice value 2: " + dice2.roll());
        System.out.println("Total value: " + (dice1.roll() + dice2.roll()));
    }
}