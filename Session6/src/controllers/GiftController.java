package controllers;

import models.GameConfig;
import models.GameObject;
import models.Plane;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by tu4nFPT on 21/10/2016.
 */
public class GiftController extends GameController implements Contactable{

    public static final int SPEED = 3;
    public GiftController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }

    @Override
    public void  run(){
        gameObject.move(0,SPEED);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof PlaneController){
            ((PlaneController) contactable).setDoubleBullet(true);
        }
    }
}
