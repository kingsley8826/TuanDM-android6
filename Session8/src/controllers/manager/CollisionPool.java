package controllers.manager;

import controllers.BaseController;
import controllers.Contactable;
import models.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by tu4nFPT on 16/10/2016.
 */
public class CollisionPool implements BaseController {
    private Vector<Contactable> contactableVector;

    private CollisionPool() {
        contactableVector = new Vector<>();
    }

    @Override
    public void run() {
        for (int i = 0; i < contactableVector.size()- 1; i++){
            for (int j = i + 1; j < contactableVector.size(); j++){
                Contactable contactablei = contactableVector.get(i);
                Contactable contactablej = contactableVector.get(j);
                GameObject gameObjecti = contactablei.getGameObject();
                GameObject gameObjectj = contactablej.getGameObject();
                if(gameObjecti.checkCollideWith(gameObjectj)){
                    contactablei.onCollide(contactablej);
                    contactablej.onCollide(contactablei);
                }
            }
        }
        Iterator<Contactable> iterator = contactableVector.iterator();
        while (iterator.hasNext()){
            Contactable contactable = iterator.next();
            if(!contactable.getGameObject().isAlive()){
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }
    public void register(Contactable contactable){
        contactableVector.add(contactable);
    }

    public static void create(){
        instance = new CollisionPool();
    }
    public static CollisionPool instance = null;
}
