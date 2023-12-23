public class Beaches extends Square {

    private int propertyValue;
    private int level;
    private int beachValue;
    private boolean isOwned;

    public Beaches(String name, int location, int propertyValue, int level) {
        super(name, location);
        this.propertyValue = propertyValue;
        this.level = level;
        this.beachValue = 50000;
    }

    public int getBeachValue() {
        return beachValue;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public void calculateBeachValue(Players player) {
        switch (player.totalNumberOfBeach()) {
            case 1:
                beachValue = 25000;
                break;
            case 2:
                beachValue = 100000;
                break;
            case 3:
                beachValue = 200000;
                break;
            case 4:
                // win
                break;
            default:
                break;
        }
    }
}
