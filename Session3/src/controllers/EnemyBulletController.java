package controllers;

import models.EnemyBullet;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyBulletController {
    private EnemyBullet enemyBullet;
    private EnemyBulletView enemyBulletView;
    public EnemyBulletController(EnemyBullet enemyBullet, EnemyBulletView enemyBulletView) {
        this.enemyBullet = enemyBullet;
        this.enemyBulletView = enemyBulletView;
    }
    public void run(){
        enemyBullet.fly();
    }
    public void draw(Graphics g){
        enemyBulletView.drawImage( g, enemyBullet);
    }
}
