public class Tax extends Square{
   
    
    private int totalTax;
    public Tax() {
        super("Tax", 37, SquareType.TAX);
        
    }

   

    public int getTotalTax() {
        return totalTax;
    }
    
    public int calculateTotalTax(Player player, Board board) {
        int totalTax = 0;
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (board.timer.getElapsedTime() <= 5) {
                totalTax += 5 / 100 * player.getCash() + 5 / 100 * player.calculateTotalPropertyValue();
            } else if (board.timer.getElapsedTime() > 5 && board.timer.getElapsedTime() < 10) {
                totalTax += 1 / 10 * player.getCash() + 1 / 10 * player.calculateTotalPropertyValue();
            } else {
                totalTax += 1 / 10 * player.getCash() + 15 / 100 * player.calculateTotalPropertyValue();
            }
        }
        return totalTax;
    }
    
}
