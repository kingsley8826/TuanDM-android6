package controllers;

import controllers.manager.CollisionPool;
import controllers.manager.NotificatonCenter;
import models.GameObject;
import utils.Utils;
import views.SingerDrawer;

/**
 * Created by tu4nFPT on 21/10/2016.
 */
public class GiftController extends GameController implements Contactable{

    public static final int SPEED = 2;
    public GiftController(GameObject gameObject, SingerDrawer singerDrawer) {
        super(gameObject, singerDrawer);
        CollisionPool.instance.register(this);
    }

    @Override
    public void  run(){
        gameObject.move(0,SPEED);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof PlaneController){
            ((PlaneController) contactable).setDoubleBullet(true);
            NotificatonCenter.instance.onEvent(EventType.BOMB_EXPLODE, this);
        }
    }
    public static GiftController creat(int x, int y){
        return new GiftController(
                new GameObject(x, y, 30, 30),
                new SingerDrawer(Utils.loadImageFromRes("gift.png"))
        );
    }
}
