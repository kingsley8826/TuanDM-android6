package controllers;

import models.GameObject;
import models.Explosion;
import utils.Utils;
import views.AnimationDrawer;

/**
 * Created by tu4nFPT on 27/10/2016.
 */
public class ExplosionController extends GameController{
    public ExplosionController(GameObject gameObject, AnimationDrawer animationDrawer) {
        super(gameObject, animationDrawer);
    }
    public static ExplosionController create(int x, int y){
        return  new ExplosionController(
                new Explosion(x, y,32,32),
                new AnimationDrawer(Utils.loadSprite("explosion.png",1, 1, 6, 32, 32))
        );
    }

    @Override
    public void run() {
        super.run();
        int countRepeat = ((AnimationDrawer) gameDrawer).getRepeatCount();
        if(countRepeat >= 1){
            gameObject.setLive(false);
        }
    }
}
