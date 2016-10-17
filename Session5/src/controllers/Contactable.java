package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 16/10/2016.
 */
public interface Contactable {
    GameObject getGameObject();
    void onCollide(Contactable contactable);
}
