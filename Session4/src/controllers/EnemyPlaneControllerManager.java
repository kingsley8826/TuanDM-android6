package controllers;

import models.EnemyPlane;
import utils.Utils;
import views.EnemyPlaneView;

/**
 * Created by tu4nFPT on 14/10/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {
    private int count = 100;
    public EnemyPlaneControllerManager() {
        super();
        createEnemyPlane();
    }

    @Override
    public void run() {
        count ++;
        super.run();
        createEnemyPlane();
        for (int i = 0; i < gameControllers.size(); i++){
            if(gameControllers.get(i).collision()){
                gameControllers.remove(i);
                i--;
            }
        }
    }
    private void createEnemyPlane(){
        if(count % 100 == 0) {
            for (int i = 0; i < 10; i++) {
                int y = 60;
                int x = i * (EnemyPlane.ENEMYPLANE_HEIGHT + 15);
                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                        new EnemyPlane(x, y),
                        new EnemyPlaneView(Utils.loadImageFromRes("plane1.png"))
                );
                gameControllers.add(enemyPlaneController);
            }
        }
    }
}