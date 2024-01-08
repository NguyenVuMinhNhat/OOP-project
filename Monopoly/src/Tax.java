public class Tax extends Square {

    private int totalTax;

    public Tax() {
        super("Tax", 37, SquareType.TAX);

    }

    public void setTotalTax(int totalTax) {
        this.totalTax = totalTax;
    }

    public int getTotalTax() {
        return totalTax;
    }

    public int calculateTotalTax(Player player, Board board) {
        int totalTax = 0;
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (board.timer.getElapsedTime() <= 5) {
                totalTax += 5 * (player.getCash() + player.calculateTotalPropertyValue()) / 100;
            } else if (board.timer.getElapsedTime() > 5 && board.timer.getElapsedTime() < 10) {
                totalTax += (player.getCash() + player.calculateTotalPropertyValue()) / 10;
            } else {
                totalTax += (player.getCash() / 10) + (player.calculateTotalPropertyValue() * 15) / 100;
            }
        }
        return totalTax;
    }

}
