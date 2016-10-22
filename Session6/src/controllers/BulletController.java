package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class BulletController extends GameController implements Contactable{

    public static final int SPEED = 10;

    public BulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }


    @Override
    public void run() {
        super.run();
        gameObject.move(0, -SPEED);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof EnemyPlaneController){
            ((EnemyPlaneController) contactable).destroy();
        }
    }
}
