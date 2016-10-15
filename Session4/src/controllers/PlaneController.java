package controllers;

import models.Bullet;
import models.GameObject;
import utils.Utils;
import views.BulletView;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends GameController{
    public static Vector<BulletController> bulletControllersVector;
    private int dx;
    private int dy;
    public static final int SPEED = 10;

    public PlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllersVector = new Vector<>();
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx = SPEED;
                break;
            case KeyEvent.VK_LEFT:
                dx = -SPEED;
                break;
            case KeyEvent.VK_UP:
                dy = -SPEED;
                break;
            case KeyEvent.VK_DOWN:
                dy = SPEED;
                break;
            case KeyEvent.VK_SPACE:
                creatBullet();
                break;

        }
    }
    public void keyReleased(KeyEvent e) {

        System.out.println("keyReleased");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;

        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for(BulletController bulletController : bulletControllersVector){
            bulletController.draw(g);
        }
    }
    @Override
    public void  run(){
        //update model
        gameObject.move(dx, dy);
        for (BulletController bulletController: bulletControllersVector){
            bulletController.run();
        }
    }


    public void mouseMoved(MouseEvent e) {
        gameObject.moveTo( e.getX() - (gameObject.getWidth() / 2), e.getY() - (gameObject.getHeight() / 2));
    }
    public void mousePressed(MouseEvent e) {
        creatBullet();
    }
    private void creatBullet(){
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getMiddleX()- Bullet.BULLET_WIDTH / 2, gameObject.getY() - Bullet.BULLET_HEIGHT),
                new BulletView(Utils.loadImageFromRes("bullet.png"))

        );
        bulletControllersVector.add(bulletController);
    }
}
