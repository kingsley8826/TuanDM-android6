import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tu4nFPT on 04/10/2016.
 */
public class Bullet {
    private int x;
    private int y;
    private int speed;
    private Image image;

    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;

    public Bullet(int x, int y, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = image;
    }
    public void drawImage(Graphics g){
        g.drawImage(image, x, y,BULLET_WIDTH, BULLET_HEIGHT, null);
    }
    public void fly(){
        this.y -= 10;
    }
}
