package controllers;

import java.awt.*;
import java.util.Iterator;
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
        Iterator<GameController> iterator = gameControllers.iterator();
        while (iterator.hasNext()){
            GameController gameController = iterator.next();
            if(!gameController.getGameObject().isAlive()){
                iterator.remove();
            }
            gameController.run();
        }

    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < gameControllers.size(); i++){
            gameControllers.get(i).draw(g);
        }
    }
}
