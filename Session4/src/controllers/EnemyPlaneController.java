package controllers;

import models.EnemyBullet;
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
public class EnemyPlaneController extends GameController{
    private static int SPEED = 3;
    private int count = 0;
    private Vector<EnemyBulletController> enemyBulletControllerVector;

    public EnemyPlaneController(GameObject gameObject,GameView gameView) {
        super(gameObject, gameView);
        enemyBulletControllerVector = new Vector<>();
    }

    @Override
    public void  run(){
        //update model
        count++;
        gameObject.move(0, SPEED);
        createBullet();
        for (EnemyBulletController enemyBulletController: enemyBulletControllerVector){
            enemyBulletController.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 0; i < enemyBulletControllerVector.size(); i++){
            enemyBulletControllerVector.get(i).draw(g);
        }
    }

    @Override
    public boolean collision() {
        for(int i = 0 ; i < PlaneController.bulletControllersVector.size(); i++){
            BulletController bulletController = PlaneController.bulletControllersVector.get(i);
            if(bulletController.gameObject.getX() > gameObject.getX() - bulletController.gameObject.getWidth()
                    && bulletController.gameObject.getX() < gameObject.getX() + gameObject.getWidth()
                    && bulletController.gameObject.getY() > gameObject.getY() - bulletController.gameObject.getHeight()
                    && bulletController.gameObject.getY() < gameObject.getY() +  gameObject.getHeight()){
                PlaneController.bulletControllersVector.remove(i);
                i--;
                return  true;
            }
        }
        return false;
    }

    private void createBullet(){
        if(count >= 20) {
            count = 0;
            int x = gameObject.getMiddleX() - EnemyBullet.BULLET_WIDTH / 2;
            int y = gameObject.getY() + EnemyBullet.BULLET_HEIGHT;
            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    new EnemyBullet(x, y),
                    new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png"))
            );
            enemyBulletControllerVector.add(enemyBulletController);
        }
    }

}
