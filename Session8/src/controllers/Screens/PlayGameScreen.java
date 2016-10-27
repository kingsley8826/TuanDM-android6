package controllers.Screens;

import controllers.GiftController;
import controllers.PlaneController;
import controllers.manager.*;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.SingerDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by tu4nFPT on 25/10/2016.
 */
public class PlayGameScreen extends GameScreen{

    private Image backgroundImage = null;
    private PlaneController planeController;
    private PlaneController planeController2;
    private ControllerManager controllers;

    public PlayGameScreen(ScreenManager screenManager) {
        super(screenManager);
        controllers = new ControllerManager();

        CollisionPool.create();
        PlaneController.createPlane();
        ControllerManager.createExplosion();
        NotificatonCenter.createNoti();

        planeController = PlaneController.planeController;
        planeController2 =PlaneController.planeController2;
        controllers.add(planeController);
        controllers.add(planeController2);

        controllers.add(new EnemyPlaneControllerManager());
        controllers.add(CollisionPool.instance);
        controllers.add(ControllerManager.explosionManager);
        controllers.add(GiftController.creat(100, 100));

        backgroundImage = Utils.loadImageFromRes("background.png");
    }



    @Override
    public void run() {
        controllers.run();
        if(!planeController.getGameObject().isAlive() || !planeController2.getGameObject().isAlive()){
            this.screenManager.change(new EndGameScreen(screenManager),false);
//            System.out.println("die");
        }
    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(backgroundImage,
                0, 0,
                GameConfig.instance.getScreenWidth(), GameConfig.instance.getScreenHeight(), null);
        controllers.draw(graphics);

    }
/* -------------------Key Listener-------------------------------------*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        planeController.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            this.screenManager.change(new LaunchGameScreen(screenManager), false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        planeController.keyReleased(e);
    }
    /* -------------------Mouse Listener-------------------------------------*/
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        planeController2.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    /* -------------------Mouse Motion Listener-------------------------------------*/
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        planeController2.mouseMoved(e);
    }
}
