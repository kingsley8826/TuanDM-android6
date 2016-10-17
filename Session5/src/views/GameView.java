package views;

import models.EnemyBullet;
import models.GameObject;

import java.awt.*;

/**
 * Created by tu4nFPT on 11/10/2016.
 */
public class GameView {
    private Image image;

    public GameView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, GameObject gameObject) {
        g.drawImage(image, gameObject.getX(), gameObject.getY(), gameObject.getWidth(),gameObject.getHeight(),  null);
    }

}
