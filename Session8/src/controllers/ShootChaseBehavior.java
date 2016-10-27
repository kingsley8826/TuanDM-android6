package controllers;

import controllers.manager.ControllerManager;
import models.GameObject;

/**
 * Created by tu4nFPT on 21/10/2016.
 */
public class ShootChaseBehavior implements ShootBehavior{
    @Override
    public void doShoot(GameObject gameObject, ControllerManager controllerManager) {
        EnemyBulletController enemyBulletController =
                EnemyBulletController.create(
                        gameObject.getX(), gameObject.getY(),
                        new FlyChaseBehavior(PlaneController.planeController2.gameObject, 3));

        controllerManager.add(enemyBulletController);
    }
}
