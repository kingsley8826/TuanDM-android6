package controllers;

import models.Bullet;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.GameView;

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

    public PlaneController(Plane gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    public void setDoubleBullet(boolean doubleBullet) {
        this.doubleBullet = doubleBullet;
    }

    public void getHit(int damage) {
        ((Plane) gameObject).decreaseHP(damage);
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
        GameView gameView;
        if(!doubleBullet){
            gameView = new GameView(Utils.loadImageFromRes("bullet.png"));
        }else{
            gameView = new GameView(Utils.loadImageFromRes("bullet-double.png"));
        }
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getMiddleX()- Bullet.BULLET_WIDTH / 2, gameObject.getY() - Bullet.BULLET_HEIGHT),
                gameView
        );
        bulletControllerManager.add(bulletController);
    }
    public static final PlaneController planeController = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT - 10),
            new GameView(Utils.loadImageFromRes("plane3.png")));
    public static final PlaneController  planeController2 = new PlaneController(
            new Plane(GameConfig.instance.getScreenWidth() /2,  GameConfig.instance.getScreenHeight() - Plane.PLANE_HEIGHT*2 - 10 ),
            new GameView(Utils.loadImageFromRes("plane4.png")));

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof EnemyBulletController){
            ((EnemyBulletController) contactable).destroy();
        }else if(contactable instanceof GiftController){
            ((GiftController) contactable).destroy();
        }
    }
}
