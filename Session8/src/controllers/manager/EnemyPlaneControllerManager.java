package controllers.manager;

import controllers.EnemyPlaneController;
import controllers.EnemyPlaneType;
import models.EnemyPlane;

/**
 * Created by tu4nFPT on 14/10/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {
    public EnemyPlaneControllerManager() {
        super();
        createEnemyPlane();
    }

    private void createEnemyPlane(){
        for (int i = 0; i < 5; i++) {
            int y = 60;
            int x = i * (EnemyPlane.ENEMYPLANE_HEIGHT + 15) + 300;
            EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(x, y, EnemyPlaneType.GRAY);
            baseControllers.add(enemyPlaneController);
        }
        for (int i = 0; i < 5; i++) {
            int y = 100;
            int x = i * (EnemyPlane.ENEMYPLANE_HEIGHT + 15) + 300;
            EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(x, y, EnemyPlaneType.YELLOW);
            baseControllers.add(enemyPlaneController);
        }

    }
}