public class Jail extends Square{
    private static int jailTurn = 0;
    public Jail() {
        super("Lost Island", 11, SquareType.JAIL);
    }

    public static void handlePlayerInJail( Board board, Player player, GUIV2 gui) {
        System.out.println(player.getName() + " is in Jail.");
        

        // Check if player has rolled doubles
        boolean rolledDoubles = rollDiceForJail();

        if (rolledDoubles) {
            System.out.println("Congratulations! " + player.getName() + " rolled doubles and got out of Jail.");
            player.setInJail(false);
            jailTurn = 0;
        } else {
            System.out.println("Sorry, " + player.getName() + " didn't roll doubles. You are still in Jail.");

            // Increment the jail turn
            jailTurn++;

            if (jailTurn > 3) {
                // Player has been in Jail for 3 turns, pay a fine to get out
                payJailFine(50000, board, player, gui);
                player.setInJail(false);
            }
        }
    }

    private static boolean rollDiceForJail() {
        Dice dice = new Dice();
        int diceRoll1 = dice.roll();
        int diceRoll2 = dice.roll();

        System.out.println("Dice Roll 1: " + diceRoll1);
        System.out.println("Dice Roll 2: " + diceRoll2);

        return diceRoll1 == diceRoll2;
    }

    private static void payJailFine(int amount, Board board, Player player, GUIV2 gui) {
        if (player.getCash() >= amount) {
            System.out.println("You paid the fine and got out of Jail.");
            player.setCash(player.getCash() - amount);
            jailTurn = 0;
        } else {
            System.out
                    .println("You don't have enough money to pay the fine. You must sell something to cover the fee.");
            player.sellPropertiesToCoverRent(amount, gui, board);
        }
    }
}
