import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tu4nFPT on 04/10/2016.
 */
public class Plane {
    private int x;
    private int y;
    private Image image;
    public static final int PLANE_WIDTH = 70;
    public static final int PLANE_HEIGHT = 50;
    public static final int PLANE_SPEED = 3;

    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addBullet(Boolean isFriend){
        Bullet bullet = null;
        try {
            if(isFriend) {
                bullet = new Bullet((x + PLANE_WIDTH / 2 - Bullet.BULLET_WIDTH / 2), y,
                        ImageIO.read(new File("resources/bullet.png")));
                GameWindow.MY_BULLETS.add(bullet);
            }else{
                bullet = new Bullet((x + PLANE_WIDTH / 2 - Bullet.BULLET_WIDTH / 2), y + PLANE_HEIGHT,
                        ImageIO.read(new File("resources/enemyBullet.png")));
                GameWindow.ENEMY_BULLETS.add(bullet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                addBullet(true);
                break;
        }
    }
    public void mouseMoved(MouseEvent e) {
        x = e.getX() - PLANE_WIDTH / 2;
        y = e.getY() - PLANE_HEIGHT / 2;
    }
    public void mousePressed(){
        addBullet(true);
    }
    public void drawImage(Graphics g){
        g.drawImage(image, x, y,PLANE_WIDTH, PLANE_HEIGHT, null);
    }
    public void fly(){
        y += PLANE_SPEED;
    }
    public boolean isDead(boolean isFriend){
        if(isFriend) {
            for (int i = 0; i < GameWindow.ENEMY_BULLETS.size(); i++) {
                if (GameWindow.ENEMY_BULLETS.get(i).getX() > this.x
                        && GameWindow.ENEMY_BULLETS.get(i).getX() < this.x + PLANE_WIDTH - GameWindow.ENEMY_BULLETS.get(i).BULLET_WIDTH
                        && GameWindow.ENEMY_BULLETS.get(i).getY() > this.y - GameWindow.ENEMY_BULLETS.get(i).BULLET_HEIGHT
                        && GameWindow.ENEMY_BULLETS.get(i).getY() < this.y + PLANE_HEIGHT) {
                    GameWindow.ENEMY_BULLETS.remove(i);
                    return true;
                }
            }
            return  false;
        }else{
            for (int i = 0; i < GameWindow.MY_BULLETS.size(); i++){
                if(GameWindow.MY_BULLETS.get(i).getX() > this.x
                        && GameWindow.MY_BULLETS.get(i).getX() < this.x + PLANE_WIDTH - GameWindow.MY_BULLETS.get(i).BULLET_WIDTH
                        && GameWindow.MY_BULLETS.get(i).getY() > this.y - GameWindow.MY_BULLETS.get(i).BULLET_HEIGHT
                        && GameWindow.MY_BULLETS.get(i).getY() < this.y +  PLANE_HEIGHT){
                    GameWindow.MY_BULLETS.remove(i);
                    return  true;
                }
            }
            return  false;
        }
    }
}
