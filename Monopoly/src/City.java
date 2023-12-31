public class City extends PropertySquare implements Comparable<City> {

    public City(String name, int location, int initialValue, int level, int upgradeCost) {
        super(name, location, initialValue, level, upgradeCost);
        setType(SquareType.CITY);
    }

    @Override
    public int calculateUpgradeCost() {
        int totalUpgradeCost = 0;
        switch (getLevel()) {
            case 1:
                totalUpgradeCost = getUpgradeCost() + 50000;
                break;
            case 2:
                totalUpgradeCost = getUpgradeCost() + 200000;
                break;
            case 3:
                totalUpgradeCost = getUpgradeCost() + 300000;
                break;
            case 4:
                totalUpgradeCost = getUpgradeCost() + 500000;
                break;
            case 5:
                totalUpgradeCost = getUpgradeCost() + 1000000;
                break;
            default:
                break;
        }
        return totalUpgradeCost;
    }
    
    @Override
    public void calculatePropertyValue(Player player) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int calculatePropertyValue() {
        int totalCityValue = 0;
        switch (getLevel()) {
            case 1:
                totalCityValue = 100000 * getLevel() + getPropertyValue();
                System.out.println("You upgrade " + getName() + " to level 1!");
                break;
            case 2:
                totalCityValue = 200000 * getLevel() + getPropertyValue();
                System.out.println("You upgrade " + getName() + " to level 2!");
                break;
            case 3:
                totalCityValue = 400000 * getLevel() + getPropertyValue();
                System.out.println("You upgrade " + getName() + " to level 3!");
                break;
            case 4:
                totalCityValue = 800000 * getLevel() + getPropertyValue();
                System.out.println("You upgrade " + getName() + " to level 4!");
                break;
            case 5:
                totalCityValue = 1600000 * getLevel() + getPropertyValue();
                System.out.println("You upgrade " + getName() + " to level 5!");
                break;
            default:
                break;
        }
        return totalCityValue;
    }

    @Override
    public int compareTo(City otherCity) {
        // TODO Auto-generated method stub
        return Integer.compare(this.getLocation(), otherCity.getLocation());
    }

    @Override
    public String toString() {

        return "Name: " + getName() + "\n" + "City value: " + getPropertyValue() + "\n" + "Upgrade cost: "
                + calculateUpgradeCost();
    }

}
