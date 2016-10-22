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

    @Override
    public void run() {
        if(GameConfig.instance.yOutsideScreen(gameObject)){
            System.out.println("asd");
            gameObject.setLive(false);
        }
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
    public void destroy(){
        gameObject.setLive(false);
    }

}
