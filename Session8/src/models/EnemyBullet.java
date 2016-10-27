package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyBullet extends GameObject{

    public static final int WIDTH = 30;

    public EnemyBullet(int x, int y) {
        super(x, y, WIDTH, WIDTH);
    }

}
