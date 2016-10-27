package controllers;

import controllers.manager.CollisionPool;
import models.EnemyBullet;
import models.GameObject;
import utils.Utils;
import views.SingerDrawer;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyBulletController extends GameController implements Contactable{
    private static int SPEED = 3;
    private FlyBehavior flyBehavior;
    public EnemyBulletController(GameObject gameObject, SingerDrawer singerDrawer, FlyBehavior flyBehavior) {
        super(gameObject, singerDrawer);
        CollisionPool.instance.register(this);
        this.flyBehavior = flyBehavior;
    }
    @Override
    public void run(){
        super.run();
        if(flyBehavior != null) {
            flyBehavior.doFly(this.gameObject);
        }
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof PlaneController){
            ((PlaneController) contactable).getHit(1);
        }
    }
    public static EnemyBulletController create(int x, int y,String image, FlyBehavior flyBehavior){
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new EnemyBullet(x, y),
                new SingerDrawer(Utils.loadImageFromRes(image)),
                flyBehavior
        );
        return  enemyBulletController;
    }
    public static EnemyBulletController create(int x, int y, FlyBehavior flyBehavior) {
        return create(x, y, "enemy_bullet.png", flyBehavior);
    }
}
