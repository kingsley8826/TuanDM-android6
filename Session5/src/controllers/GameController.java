package controllers;

import models.GameConfig;
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
        if(GameConfig.instance.yOutsideScreen(gameObject)){
            gameObject.setLive(false);
        }
    }
//    public boolean collision(){
//        return false;
//    }

    public GameObject getGameObject() {
        return gameObject;
    }
//    public boolean checkCollideWith(GameController gameController){
//        return  this.getGameObject().checkCollideWith(gameController.getGameObject());
//    }
    public void destroy(){
        gameObject.setLive(false);
    }

}
