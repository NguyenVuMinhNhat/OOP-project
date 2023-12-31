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
            case 4:
                // win
                break;
            default:
                break;
        }
    }

    @Override
    public int calculateUpgradeCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int calculatePropertyValue() {
        // TODO Auto-generated method stub
        return 0;
    }


   
}
