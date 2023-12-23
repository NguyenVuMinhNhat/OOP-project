public class Start extends Square{
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name = "Start");
    }
    
    
    @Override
    public void setLocation(int location) {
        // TODO Auto-generated method stub
        super.setLocation(1);
    }
    
    public int addMoney(Players player){
        return player.setMoney(player.getMoney() + 200000)  ;
    }
    
}
