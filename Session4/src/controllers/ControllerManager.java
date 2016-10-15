package controllers;

import models.GameObject;

import java.awt.*;
import java.util.Vector;

/**
 * Created by tu4nFPT on 11/10/2016.
 */
public class ControllerManager implements BaseController{
    protected Vector<GameController> gameControllers;

    public ControllerManager() {
        this.gameControllers = new Vector<>();
    }
    public void add(GameController gameController){
        this.gameControllers.add(gameController);
    }

    @Override
    public void run() {
        for(BaseController baseController : gameControllers){
            baseController.run();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < gameControllers.size(); i++){
            gameControllers.get(i).draw(g);
        }
    }
}
