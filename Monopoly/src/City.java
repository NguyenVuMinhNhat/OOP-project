public class City extends PropertySquare {
    private boolean isWorldCup;

    public City(String name, int position, int initialValue, int level, int upgradeCost) {
        super(name, position, initialValue, level, upgradeCost);
        setType(SquareType.CITY);
    }

    public boolean isWorldCup() {
        return isWorldCup;
    }

    public void setWorldCup(boolean isWorldCup) {
        this.isWorldCup = isWorldCup;
    }

    @Override
    public void calculateUpgradeCost() {

        switch (getLevel()) {
            case 1:
                setUpgradeCost(getUpgradeCost() + 50000);
                break;
            case 2:
                setUpgradeCost(getUpgradeCost() + 200000);
                break;
            case 3:
                setUpgradeCost(getUpgradeCost() + 300000);
                break;
            case 4:
                setUpgradeCost(getUpgradeCost() + 500000);
                break;
            case 5:
                setUpgradeCost(getUpgradeCost() + 1000000);
                break;
            default:
                break;
        }

    }

    @Override
    public void calculatePropertyValue(Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void calculatePropertyValue() {

        switch (getLevel()) {
            case 1:
                setPropertyValue(100000 * getLevel() + getInitialValue());
                System.out.println("You upgrade " + getName() + " to level 1!");
                break;
            case 2:
                setPropertyValue(200000 * getLevel() + getInitialValue());
                System.out.println("You upgrade " + getName() + " to level 2!");
                break;
            case 3:
                setPropertyValue(400000 * getLevel() + getInitialValue());
                System.out.println("You upgrade " + getName() + " to level 3!");
                break;
            case 4:
                setPropertyValue(800000 * getLevel() + getInitialValue());
                System.out.println("You upgrade " + getName() + " to level 4!");
                break;
            case 5:
                setPropertyValue(1600000 * getLevel() + getInitialValue());
                System.out.println("You upgrade " + getName() + " to level 5!");
                break;
            default:
                break;
        }

    }

    

    @Override
    public void calculateVisitCost(boolean isWorldCup) {
        if (isWorldCup) {
            setVisitCost(getVisitCost() * 10);
        }
        else{
            setVisitCost(getPropertyValue() / 2);
        }
        
    }

    @Override
    public int calculateSellValue() {

        return (getPropertyValue() * 7) / 10;
    }

   

    @Override
    public String toString() {

        return "|Name: " + getName() + "|City buy cost: $" + getInitialValue() + "|Upgrade cost: $"
                + getUpgradeCost() + "|";
    }

    @Override
    public void calculateVisitCost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateVisitCost'");
    }

}
