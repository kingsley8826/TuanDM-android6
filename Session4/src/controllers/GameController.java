package controllers;

import models.GameObject;
import views.GameView;

import java.awt.*;

/**
 * Created by tu4nFPT on 14/10/2016.
 */
public class GameController implements BaseController{
    protected GameView gameView;
    protected GameObject gameObject;

    public GameController(GameObject gameObject, GameView gameView) {
        this.gameView = gameView;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameView.drawImage(g, gameObject);
    }
    public void run(){

    }
    public boolean collision(){
        return false;
    }
}
