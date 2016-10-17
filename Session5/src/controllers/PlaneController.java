package controllers;

import models.Bullet;
import models.GameConfig;
import models.GameObject;
import models.Plane;
import utils.Utils;
import views.BulletView;
import views.GameView;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends GameController implements Contactable{
    private ControllerManager bulletControllerManager;
    private int HP = 3;
    private int dx;
    private int dy;
    public static final int SPEED = 10;

    public PlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    public void getShot() {
        this.HP--;
        System.out.println(HP);
        if(HP == 0){
            destroy();
        }
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
        bulletControllerManager.draw(g);
    }
    @Override
    public void  run(){
        //update model
        gameObject.move(dx, dy);
        bulletControllerManager.run();
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
        bulletControllerManager.add(bulletController);
    }
    public static final PlaneController planeController = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT - 10),
            new PlaneView(Utils.loadImageFromRes("plane3.png")));
    public static final PlaneController  planeController2 = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT*2 - 10 ),
            new PlaneView(Utils.loadImageFromRes("plane4.png")));

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof EnemyBulletController){
            ((EnemyBulletController) contactable).destroy();
        }
    }
}
