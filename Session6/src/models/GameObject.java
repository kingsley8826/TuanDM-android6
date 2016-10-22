package models;

import java.awt.*;

/**
 * Created by tu4nFPT on 11/10/2016.
 */
public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    protected boolean isLive;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isLive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMiddleX() {
        return x + width / 2;
    }
    public int getMiddleY() {
        return y + height / 2;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }
    public boolean checkCollideWith(GameObject gameObject){
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = gameObject.getRect();
        return rect1.intersects(rect2);
    }

}
