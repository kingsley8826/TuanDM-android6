package controllers;

import models.GameObject;

/**
 * Created by tu4nFPT on 21/10/2016.
 */
public class FlyChaseBehavior implements FlyBehavior{
    private int SPEED;
    private int speedX;
    private int speedY;
    private int planeX;
    private int planeY;
    private boolean changeDirection;

    public FlyChaseBehavior(GameObject planeObject, int speed) {
        changeDirection = true;
        this.SPEED = speed;
        this.planeX = planeObject.getMiddleX();
        this.planeY = planeObject.getMiddleY();
    }
    @Override
    public void doFly(GameObject gameObject) {
        if(changeDirection){
            getDirection( planeX, planeY, gameObject.getX(), gameObject.getY());
        }
        gameObject.move(speedX,speedY);
    }
    private void getDirection(int planeX, int planeY, int bulletX, int bulletY){
        double distance_X = planeX - bulletX;
        double distance_Y = planeY - bulletY;
        double distance = Math.sqrt(distance_X * distance_X + distance_Y * distance_Y);
        double sin = distance_X / distance;
        double cos = distance_Y / distance;
        speedX = (int) Math.round(SPEED * sin);
        speedY = (int) Math.round(SPEED * cos);
        if(distance < SPEED * 30){changeDirection = false;}
    }
}
