public class Tax extends Square {

    private int totalTax;

    public Tax() {
        super("Tax", 34, SquareType.TAX);

    }

    public void setTotalTax(int totalTax) {
        this.totalTax = totalTax;
    }

    public int getTotalTax() {
        return totalTax;
    }

    public int calculateTotalTax(Player player, Board board) {
        int totalTax = 0;
        
            if (board.getTimer().getElapsedTime() <= 5) {
                totalTax = (player.getCash() + player.calculateTotalPropertyValue()) / 100;
            } 
            if (board.getTimer().getElapsedTime() > 5 && board.getTimer().getElapsedTime() < 10) {
                totalTax = (5 * (player.getCash() + player.calculateTotalPropertyValue())) / 100;
            }
            if (board.getTimer().getElapsedTime() >= 10) {
                 totalTax = (player.getCash() / 10) + (player.calculateTotalPropertyValue() * 15) / 100;
            } 
            
            
        
        return totalTax;
    }

}
