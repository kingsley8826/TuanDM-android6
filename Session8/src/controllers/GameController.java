package controllers;

import models.GameConfig;
import models.GameObject;
import views.GameDrawer;
import views.SingerDrawer;

import java.awt.*;

/**
 * Created by tu4nFPT on 14/10/2016.
 */
public class GameController implements BaseController{
    protected GameDrawer gameDrawer;
    protected GameObject gameObject;

    public GameController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameDrawer.drawImage(g, gameObject);
    }

    @Override
    public void run() {
        if(GameConfig.instance.yOutsideScreen(gameObject)) {
            gameObject.setLive(false);
        }
    }

    public void setGameDrawer(SingerDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
    public void destroy(){
        gameObject.setLive(false);
    }

}
