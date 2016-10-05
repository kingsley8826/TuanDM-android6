import java.awt.*;

/**
 * Created by tu4nFPT on 04/10/2016.
 */
public class Bullet {
    private int x;
    private int y;
    private Image image;

    public static final int BULLET_WIDTH = 12;
    public static final int BULLET_HEIGHT = 30;
    public static final int SPEED = 10;

    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }
    public void drawImage(Graphics g){
        g.drawImage(image, x, y,BULLET_WIDTH, BULLET_HEIGHT, null);
    }
    public void fly(){
        this.y -= SPEED;
    }
}
