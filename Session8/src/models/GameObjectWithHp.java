package models;

/**
 * Created by tu4nFPT on 18/10/2016.
 */
public class GameObjectWithHp extends GameObject {

    public int hp;
    public GameObjectWithHp(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
    }
    public void decreaseHP(int amount){
        hp -= amount;
        if(hp <= 0){
            isLive = false;
        }
    }
    public void increaseHP(int amount){
        hp += amount;
    }
}
