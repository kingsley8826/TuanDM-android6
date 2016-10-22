package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 18/10/2016.
 */
public interface ShootBehavior {
    void doShoot(GameObject gameObject, ControllerManager controllerManager);
}
