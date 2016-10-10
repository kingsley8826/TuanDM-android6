package views;

import models.Bullet;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class BulletView {
    private Image image;

    public BulletView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, Bullet bullet) {
        g.drawImage(image, bullet.getX(), bullet.getY(), bullet.BULLET_WIDTH, bullet.BULLET_HEIGHT, null);
    }
}
