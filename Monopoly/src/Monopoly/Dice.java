public class Dice {
    private int valueDice;

    public Dice(int valueDice) {
        this.valueDice = valueDice;
    }

    public int getValueDice() {
        return valueDice;
    }

    public int roll() {
        valueDice = 1 + (int) (Math.random() * 6);
        return valueDice;
    }

}
