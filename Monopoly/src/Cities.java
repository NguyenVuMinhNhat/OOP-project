public class Cities extends Square implements Comparable<Cities> {
    private int propertyValue;
    private int cityValue;
    private int level;
    private int houseLevel;
    private int upgradeCost;
    private boolean isOwned = false;

    public Cities(String name, int location, int propertyValue, int level, int houseLevel,
            int upgradeCost) {
        super(name, location);
        this.propertyValue = propertyValue;
        this.level = level;
        this.houseLevel = houseLevel;
        this.upgradeCost = upgradeCost;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    public int getCityValue() {
        return cityValue;
    }

    public void setCityValue(int cityValue) {
        this.cityValue = cityValue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHouseLevel() {
        return houseLevel;
    }

    public void setHouseLevel(int houseLevel) {
        this.houseLevel = houseLevel;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public int calculateUpgradeCost() {
        int totalUpgradeCost = 0;
        switch (level) {
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

    public int calculateCityValue() {
        int totalCityValue = 0;
        switch (level) {
            case 1:
                totalCityValue = 100000 * houseLevel + propertyValue;
                System.out.println("You updrade " + getName() + " to level 1!");
                break;
            case 2:
                totalCityValue = 200000 * houseLevel + propertyValue;
                System.out.println("You updrade " + getName() + " to level 2!");
                break;
            case 3:
                totalCityValue = 400000 * houseLevel + propertyValue;
                System.out.println("You updrade " + getName() + " to level 3!");
                break;
            case 4:
                totalCityValue = 800000 * houseLevel + propertyValue;
                System.out.println("You updrade " + getName() + " to level 4!");
                break;
            case 5:
                totalCityValue = 1600000 * houseLevel + propertyValue;
                System.out.println("You updrade " + getName() + " to level 5!");
                break;
            default:
                break;
        }
        return totalCityValue;
    }

    public double calculateSellValue() {
        double totalSellValue = 0;
        return totalSellValue = 0.7 * calculateCityValue();
    }

    public int calculateLevel() {
        return level++;
    }

    public int updradeHouseLevel() {
        return houseLevel++;
    }

    @Override
    public int compareTo(Cities otherCity) {
        // TODO Auto-generated method stub
        return Integer.compare(this.getLocation(), otherCity.getLocation());
    }


    @Override
    public String toString() {

        return "Name: " + getName() + "\n" + "City value: " + getCityValue() + "\n" + "Upgrade cost: "
                + calculateUpgradeCost();
    }

}
