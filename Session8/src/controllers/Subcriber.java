package controllers;

/**
 * Created by tu4nFPT on 25/10/2016.
 */
public interface Subcriber {
    void onEvent(EventType eventType, GameController gameController);
}
