package controllers;

import controllers.manager.CollisionPool;
import controllers.manager.ControllerManager;
import models.GameObject;
import utils.Utils;
import views.SingerDrawer;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class BulletController extends GameController implements Contactable{

    public static final int SPEED = 10;

    public BulletController(GameObject gameObject, SingerDrawer singerDrawer) {
        super(gameObject, singerDrawer);
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
            ExplosionController explositonController = ExplosionController.create(
                    ((EnemyPlaneController) contactable).gameObject.getX(),
                    ((EnemyPlaneController) contactable).gameObject.getY());
            ControllerManager.explosionManager.add(explositonController);
            Utils.playSound("resources/dead.wav", false);
        }
    }
}
