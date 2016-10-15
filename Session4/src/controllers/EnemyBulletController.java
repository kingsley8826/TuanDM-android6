package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class EnemyBulletController extends GameController{
    private static int SPEED = 10;

    public EnemyBulletController( GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }
    @Override
    public void run(){
        gameObject.move(0, SPEED);
    }
}
