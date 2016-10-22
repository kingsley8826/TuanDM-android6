package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 21/10/2016.
 */
public class ShootDownRightBehavior implements ShootBehavior {
    @Override
    public void doShoot(GameObject gameObject, ControllerManager controllerManager) {
        EnemyBulletController enemyBulletController =
                EnemyBulletController.create(
                        gameObject.getX(), gameObject.getY(),
                        new FlyDownRightBehavior(1,3));

        controllerManager.add(enemyBulletController);
    }
}
