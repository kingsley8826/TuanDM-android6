package controllers;

import models.Bullet;
import models.Plane;
import utils.Utils;
import views.BulletView;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController {
    private Plane plane;
    private PlaneView planeView;
    private Vector<BulletController> bulletControllersVector;
    private int dx;
    private int dy;
    public static final int SPEED = 10;

    public PlaneController(Plane plane, PlaneView planeView) {
        this.plane = plane;
        this.planeView = planeView;
        bulletControllersVector = new Vector<>();
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx = SPEED;
                break;
            case KeyEvent.VK_LEFT:
                dx -= SPEED;
                break;
            case KeyEvent.VK_UP:
                dy -= SPEED;
                break;
            case KeyEvent.VK_DOWN:
                dy = SPEED;
                break;
            case KeyEvent.VK_SPACE:
                BulletController bulletController = new BulletController(
                        new Bullet(plane.getMiddleX()- Bullet.BULLET_WIDTH, plane.getY() - Bullet.BULLET_HEIGHT),
                        new BulletView(Utils.loadImageFromRes("bullet.png"))
                );
                bulletControllersVector.add(bulletController);
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

    public void  run(){
        //update model
        plane.move(dx, dy);
        for (BulletController bulletController: bulletControllersVector){
            bulletController.run();
        }
    }

    public void draw(Graphics g){
        planeView.drawImage(g, plane);
        for (BulletController bulletController: bulletControllersVector){
            bulletController.draw(g);
        }
    }

    public void mouseMoved(MouseEvent e) {
        plane.moveTo( e.getX() - (plane.PLANE_WIDTH / 2), e.getY() - (plane.PLANE_HEIGHT / 2));
    }
    public void mousePressed(MouseEvent e) {
        plane.moveTo( e.getX() - (plane.PLANE_WIDTH / 2), e.getY() - (plane.PLANE_HEIGHT / 2));
        BulletController bulletController = new BulletController(
                new Bullet(plane.getMiddleX()- Bullet.BULLET_WIDTH, plane.getY() - Bullet.BULLET_HEIGHT),
                new BulletView(Utils.loadImageFromRes("bullet.png"))
        );
        bulletControllersVector.add(bulletController);
    }
}
