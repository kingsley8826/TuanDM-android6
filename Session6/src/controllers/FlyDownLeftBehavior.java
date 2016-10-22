package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 18/10/2016.
 */
public class FlyDownLeftBehavior implements FlyBehavior {
    private int speedX;
    private int speedY;

    public FlyDownLeftBehavior(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(-speedX, speedY);
    }
}
