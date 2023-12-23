import java.util.ArrayList;

public class Square {

    private String name;
    private int location;

    private ArrayList<Square> squares = new ArrayList<>();

    public Square() {

    }

    public Square(String name, int location) {
        this.name = name;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public void addSquare(Square square){
        squares.add(square);
    }
}
