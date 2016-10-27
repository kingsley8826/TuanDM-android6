package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyPlane  extends GameObjectWithHp{

    public static final int ENEMYPLANE_WIDTH = 35;
    public static final int ENEMYPLANE_HEIGHT = 30;

    public EnemyPlane(int x, int y) {
        super(x, y, ENEMYPLANE_WIDTH, ENEMYPLANE_HEIGHT, 1);
    }
}
