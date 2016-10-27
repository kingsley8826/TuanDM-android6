package views;

import models.EnemyBullet;
import models.GameObject;

import java.awt.*;

/**
 * Created by tu4nFPT on 11/10/2016.
 */
public class SingerDrawer extends GameDrawer{
    private Image image;

    public SingerDrawer(Image image) {
        this.image = image;
    }
    @Override
    public void drawImage(Graphics g, GameObject gameObject) {
        g.drawImage(image, gameObject.getX(), gameObject.getY(), gameObject.getWidth(),gameObject.getHeight(),  null);
    }

}
