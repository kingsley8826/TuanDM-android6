package controllers.Screens;

import controllers.manager.GameScreen;
import controllers.manager.ScreenManager;
import javafx.scene.input.KeyCode;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by tu4nFPT on 27/10/2016.
 */
public class LaunchGameScreen extends GameScreen{

    public LaunchGameScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void run() {

    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(Utils.loadImageFromRes("1945-logo.png"),
                0, 0,
                GameConfig.instance.getScreenWidth(), GameConfig.instance.getScreenHeight(), null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.screenManager.change(new PlayGameScreen(screenManager), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
