package controllers;

import models.EnemyPlane;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyPlaneController extends GameController implements Contactable{
    private int count = 0;
    private ControllerManager bulletControllerManager;
    private FlyBehavior flyBehavior;
    private ShootBehavior shootBehavior;
    public EnemyPlaneController(GameObject gameObject, GameView gameView, FlyBehavior flyBehavior,ShootBehavior shootBehavior) {
        super(gameObject, gameView);
        this.flyBehavior =flyBehavior;
        this.shootBehavior = shootBehavior;

        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }
    @Override
    public void  run(){
//        super.run();
        if(flyBehavior != null){
            this.flyBehavior.doFly(gameObject);
        }
        count++;
        createBullet();
        if(gameObject.getX() <= 0){
            setFlyBehavior(new FlyDownRightBehavior(2,1));
        }else if(gameObject.getX() >= (GameConfig.instance.getScreenWidth()-gameObject.getWidth())){
            setFlyBehavior(new FlyDownLeftBehavior(2,1));
        }
        bulletControllerManager.run();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletControllerManager.draw(g);
    }

    private void createBullet(){
        if(GameConfig.instance.getSeconds(count) > 0.5) {
            count = 0;
            if (shootBehavior != null) {
                shootBehavior.doShoot(this.gameObject, this.bulletControllerManager);
            }
        }
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof BulletController){
            ((BulletController) contactable).destroy();
        }
    }

    public static EnemyPlaneController create(int x, int y, EnemyPlaneType enemyPlaneType) {
        Image image = null;
        FlyBehavior flyBehavior = null;
        ShootBehavior shootBehavior = null;
        if (enemyPlaneType == EnemyPlaneType.GRAY) { //Grey
            image = Utils.loadImageFromRes("plane1.png");
            flyBehavior = new FlyDownRightBehavior(2, 1);
            shootBehavior = new ShootDownRightBehavior();
        } else if (enemyPlaneType == EnemyPlaneType.RED) { //Red
            image = Utils.loadImageFromRes("plane2.png");
            flyBehavior = new FlyDownBehavior(1);
            shootBehavior = new ShootDownBehavior();
        }else if (enemyPlaneType == EnemyPlaneType.YELLOW) { //Yellow
            image = Utils.loadImageFromRes("enemy_plane_yellow_3.png");
            flyBehavior = new FlyDownLeftBehavior(2, 1);
            shootBehavior = new ShootChaseBehavior();
        }
        return new EnemyPlaneController(
                new EnemyPlane(x, y),
                new GameView(image),
                flyBehavior,
                shootBehavior
        );
    }
}
