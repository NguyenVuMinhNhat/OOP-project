public class Beach extends PropertySquare {

    public Beach(String name, int location, int initialValue) {
        super(name, location, initialValue);
        setType(SquareType.BEACH);
    }

    @Override
    public void calculatePropertyValue(Player player) {
        switch (player.totalNumberOfBeach()) {
            case 1:
                setPropertyValue(25000);
                break;
            case 2:
                setPropertyValue(100000);
                break;
            case 3:
                setPropertyValue(200000);
                break;
            default:
                break;
        }
    }
    

    @Override
    public void calculateUpgradeCost() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void calculatePropertyValue() {
        // TODO Auto-generated method stub
    }

    @Override
    public void calculateVisitCost() {
        // TODO Auto-generated method stub
        setVisitCost(getPropertyValue());
    }

    @Override
    public int calculateSellValue() {
        return (7 / 10) * getPropertyValue();
    }


   
}
