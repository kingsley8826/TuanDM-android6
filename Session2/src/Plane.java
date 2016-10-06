import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tu4nFPT on 04/10/2016.
 */
public class Plane {
    private int x;
    private int y;
    private Image image;
    private ArrayList<Bullet> bullets;
    public static final int PLANE_WIDTH = 70;
    public static final int PLANE_HEIGHT = 50;

    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        bullets = new ArrayList<>();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void addBullet(){
        Bullet bullet = null;
        try {
            bullet = new Bullet(ImageIO.read(new File("resources/bullet.png")));
            bullet.setLocation((x + PLANE_WIDTH / 2 - bullet.BULLET_WIDTH / 2), y);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullets.add(bullet);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                x += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_SPACE:
                addBullet();
                break;
        }
    }
    public void mouseMoved(MouseEvent e) {
        x = e.getX() - PLANE_WIDTH / 2;
        y = e.getY() - PLANE_HEIGHT / 2;
    }
    public void mousePressed(){
        addBullet();
    }
    public void drawImage(Graphics g){
        g.drawImage(image, x, y,PLANE_WIDTH, PLANE_HEIGHT, null);
    }

}
