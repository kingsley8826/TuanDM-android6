package controllers.manager;

import controllers.BaseController;
import controllers.GameController;
import models.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by tu4nFPT on 11/10/2016.
 */
public class ControllerManager implements BaseController {
    protected Vector<BaseController> baseControllers;
    public static void createExplosion(){
        explosionManager = new ControllerManager();
    }
    public static ControllerManager explosionManager = null;
    public ControllerManager() {
        this.baseControllers = new Vector<>();
    }
    public void add(BaseController baseController){
        this.baseControllers.add(baseController);
    }

    @Override
    public void run() {
        synchronized (baseControllers) {
            Iterator<BaseController> iterator = baseControllers.iterator();
            while (iterator.hasNext()) {
                BaseController baseController = iterator.next();
                if (baseController instanceof GameController) {
                    GameObject gameObject =
                            ((GameController) baseController).getGameObject();
                    if (!gameObject.isAlive()) {
                        iterator.remove();
                    } else {
                        baseController.run();
                    }
                } else {
                    baseController.run();
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        synchronized (baseControllers) {
            for (int i = 0; i < baseControllers.size(); i++) {
                baseControllers.get(i).draw(g);
            }
        }
    }
}
