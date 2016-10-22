package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 18/10/2016.
 */
public class ShootDownLeftBehavior implements ShootBehavior {
    @Override
    public void doShoot(GameObject gameObject, ControllerManager controllerManager) {
        EnemyBulletController enemyBulletController =
                EnemyBulletController.create(
                        gameObject.getX(), gameObject.getY(),
                        new FlyDownLeftBehavior(1, 3));

        controllerManager.add(enemyBulletController);
    }
}
