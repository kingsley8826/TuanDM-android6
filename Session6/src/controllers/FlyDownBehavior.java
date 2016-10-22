package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 18/10/2016.
 */
public class FlyDownBehavior implements FlyBehavior {
    private int speed;
    public FlyDownBehavior(int speed) {
        super();
        this.speed = speed;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(0, speed);
    }
}
