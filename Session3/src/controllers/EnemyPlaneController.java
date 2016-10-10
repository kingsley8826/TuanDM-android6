package controllers;

import models.EnemyPlane;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyPlaneController {

    private EnemyPlane enemyPlane;
    private EnemyPlaneView enemyPlaneView;
    private Vector<EnemyBulletController> enemyBulletControllerVector;

    public EnemyPlaneController(EnemyPlane enemyPlane, EnemyPlaneView enemyPlaneView) {
        this.enemyPlane = enemyPlane;
        this.enemyPlaneView = enemyPlaneView;
        enemyBulletControllerVector = new Vector<>();
    }

    public EnemyPlane getEnemyPlane() {
        return enemyPlane;
    }

    public Vector<EnemyBulletController> getEnemyBulletControllerVector() {
        return enemyBulletControllerVector;
    }

    public void  run(){
        //update model
        enemyPlane.fly();
        for (EnemyBulletController enemyBulletController: enemyBulletControllerVector){
            enemyBulletController.run();
        }
    }

    public void draw(Graphics g){
        enemyPlaneView.drawImage(g,enemyPlane);
        for (int i = 0; i < enemyBulletControllerVector.size(); i++){
            enemyBulletControllerVector.get(i).draw(g);
        }
    }
}
