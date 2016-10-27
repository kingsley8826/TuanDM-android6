package controllers;

import controllers.manager.CollisionPool;
import controllers.manager.ControllerManager;
import controllers.manager.NotificatonCenter;
import models.EnemyPlane;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.AnimationDrawer;
import views.GameDrawer;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyPlaneController extends GameController implements Contactable, Subcriber{
    private int count = 0;
    private ControllerManager bulletControllerManager;
    private FlyBehavior flyBehavior;
    private ShootBehavior shootBehavior;
    public EnemyPlaneController(GameObject gameObject, GameDrawer gameDrawer, FlyBehavior flyBehavior, ShootBehavior shootBehavior) {
        super(gameObject, gameDrawer);
        this.flyBehavior =flyBehavior;
        this.shootBehavior = shootBehavior;

        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
        NotificatonCenter.instance.resgister(this);
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

    @Override
    public void destroy() {
        super.destroy();
        ExplosionController explositonController = ExplosionController.create(
                this.gameObject.getX(),
                this.gameObject.getY());
        ControllerManager.explosionManager.add(explositonController);
        new Thread(() -> Utils.playSound("resources/shot.wav", false)).start();
    }

    public static EnemyPlaneController create(int x, int y, EnemyPlaneType enemyPlaneType) {
//        Image image = null;
        String[] arrName = {"enemy_plane_yellow_1.png","enemy_plane_yellow_2.png","enemy_plane_yellow_3.png"};
        AnimationDrawer animationDrawer = null;
        FlyBehavior flyBehavior = null;
        ShootBehavior shootBehavior = null;
        if (enemyPlaneType == EnemyPlaneType.GRAY) { //Grey
//            image = Utils.loadImageFromRes("plane1.png");
            animationDrawer = new AnimationDrawer(arrName);
            flyBehavior = new FlyDownRightBehavior(2, 1);
            shootBehavior = new ShootDownRightBehavior();
        } else if (enemyPlaneType == EnemyPlaneType.RED) { //Red
//            image = Utils.loadImageFromRes("plane2.png");
            animationDrawer = new AnimationDrawer(arrName);
            flyBehavior = new FlyDownBehavior(1);
            shootBehavior = new ShootDownBehavior();
        }else if (enemyPlaneType == EnemyPlaneType.YELLOW) { //Yellow
//            image = Utils.loadImageFromRes("enemy_plane_yellow_3.png");
            animationDrawer = new AnimationDrawer(Utils.loadSprite("enemy_plane_yellow.png", 1, 1, 3, 32, 32));
            flyBehavior = new FlyDownLeftBehavior(2, 1);
            shootBehavior = new ShootDownRightBehavior();
        }
        return new EnemyPlaneController(
                new EnemyPlane(x, y),
                animationDrawer,
                flyBehavior,
                shootBehavior
        );
    }

    static final int DAMAGE_RADIUS = 200;
    @Override
    public void onEvent(EventType eventType, GameController gameController) {
        if(eventType == EventType.BOMB_EXPLODE){
            double distance = Utils.distance(gameObject,gameController.getGameObject());
            if(distance < DAMAGE_RADIUS){
                this.destroy();
            }
        }
    }
}
