package views;

import models.GameConfig;
import models.GameObject;
import utils.Utils;

import java.awt.*;
import java.util.Vector;

/**
 * Created by tu4nFPT on 25/10/2016.
 */
public class AnimationDrawer extends GameDrawer {
    private Vector<Image> imagesVector ;
    private int index = 0;
    private int count = 0;
    private int repeatCount = 0;

    public int getRepeatCount() {
        return repeatCount;
    }

    public AnimationDrawer(String[] arrName) {
        imagesVector = new Vector<>();
        for (String name : arrName){
            Image image = Utils.loadImageFromRes(name);
            imagesVector.add(image);
        }
    }

    public AnimationDrawer(Vector<Image> imagesVector) {
        this.imagesVector = imagesVector;
    }

    @Override
    public void drawImage(Graphics g, GameObject gameObject) {
        Image image = imagesVector.get(index);
        g.drawImage(image,
                gameObject.getX(),
                gameObject.getY(),
                gameObject.getWidth(),
                gameObject.getHeight(),
                null);
        count++;
        if(GameConfig.instance.getMiliseconds(count) > 50) {
            count = 0;
            index++;
            if (index >= imagesVector.size()) {
                repeatCount++;
                index = 0;
            }
        }
    }
}
