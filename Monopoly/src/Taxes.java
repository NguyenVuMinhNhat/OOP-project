public class Taxes extends Square{
   
    Board timer = new Board();
    
    private int totalTax;
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name = "Taxes");
    }

    @Override
    public void setLocation(int location) {
        // TODO Auto-generated method stub
        super.setLocation(location = 37);
    }

    public int getTotalTax() {
        return totalTax;
    }
    
    public int calculateTotalTax(Players player, Cities city){
        if (timer.timer.getElapsedTime() <= 5) {
            totalTax = 0.05 * player.getTotalCash() + 0.05 * city.getCityValue();
        }
        else if (timer.timer.getElapsedTime() > 5 && timer.timer.getElapsedTime() < 10){
            totalTax = 0.1 * player.getTotalCash() + 0.1 * city.getCityValue();
        }
        else{
            totalTax = 0.1 * player.getTotalCash() + 0.15 * city.getCityValue();
        }
        return totalTax;
    }
    
}
