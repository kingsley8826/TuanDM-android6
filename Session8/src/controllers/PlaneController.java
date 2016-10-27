package controllers;

import controllers.manager.CollisionPool;
import controllers.manager.ControllerManager;
import models.Bullet;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.SingerDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends GameController implements Contactable{
    private ControllerManager bulletControllerManager;
    private int dx;
    private int dy;
    public static final int SPEED = 10;
    private boolean doubleBullet = false;

    public PlaneController(Plane gameObject, SingerDrawer singerDrawer) {
        super(gameObject, singerDrawer);
        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    public void setDoubleBullet(boolean doubleBullet) {
        this.doubleBullet = doubleBullet;
    }

    public void getHit(int damage) {
        ((Plane) gameObject).decreaseHP(damage);
//        System.out.println(((Plane) gameObject).hp);
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
                createBullet();
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
        Utils.playSound("resources/shot.wav", false);
        createBullet();
    }
    private void createBullet(){
        SingerDrawer singerDrawer;
        if(!doubleBullet){
            singerDrawer = new SingerDrawer(Utils.loadImageFromRes("bullet.png"));
        }else{
            singerDrawer = new SingerDrawer(Utils.loadImageFromRes("bullet-double.png"));
        }
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getMiddleX()- Bullet.BULLET_WIDTH / 2, gameObject.getY() - Bullet.BULLET_HEIGHT),
                singerDrawer
        );
        bulletControllerManager.add(bulletController);
    }
    public static void createPlane(){
        planeController = new PlaneController(
                new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT - 10),
                new SingerDrawer(Utils.loadImageFromRes("plane3.png")));
        planeController2 = new PlaneController(
                new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT*2 - 10 ),
                new SingerDrawer(Utils.loadImageFromRes("plane4.png")));
    }
    public static PlaneController planeController = null;
    public static PlaneController  planeController2 = null;
    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof EnemyBulletController){
            ((EnemyBulletController) contactable).destroy();
        }else if(contactable instanceof GiftController){
            ((GiftController) contactable).destroy();
        }

    }
}
