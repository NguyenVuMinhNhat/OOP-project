public class City extends PropertySquare implements Comparable<City> {

    public City(String name, int location, int initialValue, int level, int upgradeCost) {
        super(name, location, initialValue, level, upgradeCost);
        setType(SquareType.CITY);
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
    public void calculateVisitCost() {
        setVisitCost(getPropertyValue()  / 2) ;
    }

    @Override
    public int calculateSellValue() {

        return  (getPropertyValue() * 7) / 10;
    }

    @Override
    public int compareTo(City otherCity) {
        // TODO Auto-generated method stub
        return Integer.compare(this.getLocation(), otherCity.getLocation());
    }

    @Override
    public String toString() {

        return "|Name: " + getName() + "|City buy cost: $" + getInitialValue() + "|Upgrade cost: $"
                + getUpgradeCost() + "|";
    }

}
