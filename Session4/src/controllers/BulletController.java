package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class BulletController extends GameController{

    public static final int SPEED = 10;

    public BulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }

    @Override
    public void run() {
        gameObject.move(0, -SPEED);
    }
}
