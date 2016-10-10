package controllers;

import models.Bullet;
import views.BulletView;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class BulletController {
    Bullet bullet;
    BulletView bulletView;

    public BulletController(Bullet bullet, BulletView bulletView) {
        this.bullet = bullet;
        this.bulletView = bulletView;
    }

    public void run(){
        bullet.fly();
    }
    public void draw(Graphics g){
        bulletView.drawImage(g,bullet);
    }
}
