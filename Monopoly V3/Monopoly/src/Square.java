import java.awt.Color;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

public class Square extends JPanel{
    private String name;
    private int position;
    private SquareType type;
    private int angle;
    private int width;

    private int height;
    private int x;

    private int y;

    public Square(String name) {
        this.name = name;
    }

    public Square(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public Square(String name, int position, SquareType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SquareType getType() {
        return type;
    }

    public void setType(SquareType type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(x , y , width , height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        double rotationAngle = Math.toRadians(angle);
        g2d.rotate(rotationAngle, width / 2.0, height / 2.0);

        String text = name;
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getAscent();
        int textX = (width - textWidth) / 2;
        int textY = height / 2 + textHeight / 2 - 25;
        g2d.drawString(text, textX, textY);

        if (this.getType() == SquareType.CITY || this.getType() == SquareType.BEACH) {
            PropertySquare propertySquare = (PropertySquare) this;
            if (propertySquare.isOwned()) {
                String visitCost = "$" + propertySquare.getVisitCost();
                g2d.drawString(visitCost, textX , textY + 20);  
                String owner = propertySquare.getOwner().getName();
                g2d.drawString(owner, textX , textY + 40);
            }
            
        }
        
        
        g2d.dispose();

    }
}
