public abstract class PropertySquare extends Square {

    private boolean isOwned = false;
    private int propertyValue;
    private Player owner;
    private int visitCost;
    private int initialValue;
    private int level;
    private int upgradeCost;

    public PropertySquare(String name, int location,
            int initialValue) {
        super(name, location, SquareType.PROPERTY);
        this.initialValue = initialValue;
       
    }

    public PropertySquare(String name, int location,
            int initialValue, int level, int upgradeCost) {
        super(name, location, SquareType.PROPERTY);
        this.initialValue = initialValue;
        this.level = level;
        this.upgradeCost = upgradeCost;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getVisitCost() {
        return visitCost;
    }

    public void setVisitCost(int visitCost) {
        this.visitCost = visitCost;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    public int calculateLevel() {
        return level++;
    }

    public abstract int calculateUpgradeCost();

    public abstract int calculatePropertyValue();

    public abstract void calculatePropertyValue(Player player);

    public int calculateVisitCost() {
        return 2 * calculatePropertyValue();
    }

    public int calculateSellValue() {

        return 7 / 10 * calculatePropertyValue();
    }
}
