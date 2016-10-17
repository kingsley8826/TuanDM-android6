package controllers;

import models.EnemyBullet;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;
import java.util.Vector;

import static models.Plane.PLANE_HEIGHT;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyPlaneController extends GameController implements Contactable{
    private static int SPEED = 1;
    private int count = 0;
    private ControllerManager bulletControllerManager;

    public EnemyPlaneController(GameObject gameObject,GameView gameView) {
        super(gameObject, gameView);
        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    @Override
    public void  run(){
        //update model
        count++;
        gameObject.move(0, SPEED);
        bulletControllerManager.run();
        createBullet();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletControllerManager.draw(g);
    }

//    @Override
//    public boolean collision() {
//        for(int i = 0 ; i < PlaneController.bulletControllersVector.size(); i++){
//            BulletController bulletController = PlaneController.bulletControllersVector.get(i);
//            if(bulletController.gameObject.getX() > gameObject.getX() - bulletController.gameObject.getWidth()
//                    && bulletController.gameObject.getX() < gameObject.getX() + gameObject.getWidth()
//                    && bulletController.gameObject.getY() > gameObject.getY() - bulletController.gameObject.getHeight()
//                    && bulletController.gameObject.getY() < gameObject.getY() +  gameObject.getHeight()){
//                PlaneController.bulletControllersVector.remove(i);
//                i--;
//                return  true;
//            }
//        }
//        return false;
//    }

    private void createBullet(){
        if(GameConfig.instance.getSeconds(count) > 1) {
            count = 0;
            int x = gameObject.getMiddleX() - EnemyBullet.BULLET_WIDTH / 2;
            int y = gameObject.getY() + EnemyBullet.BULLET_HEIGHT;
            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    new EnemyBullet(x, y),
                    new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png"))
            );
            bulletControllerManager.add(enemyBulletController);
        }
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof BulletController){
            ((BulletController) contactable).destroy();
        }
    }
}
