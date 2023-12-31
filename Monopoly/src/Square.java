public class Square {
    private String name;
    private int location;
    private SquareType type;

    public Square(String name, int location, SquareType type) {
        this.name = name;
        this.location = location;
        this.type = type;
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

    public SquareType getType() {
        return type;
    }

    public void setType(SquareType type) {
        this.type = type;
    }
}
