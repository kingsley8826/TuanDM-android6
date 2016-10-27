package models;

/**
 * Created by tu4nFPT on 16/10/2016.
 */
public class GameConfig {
    private static final int DEFAULT_DELAY = 17;
    private static final int DEFAULT_WIDTH= 800;
    private static final int DEFAULT_HEIGHT = 600;

    private int threadDelay ; // ms
    private int screenWidth;
    private int screenHeight;
    public double getSeconds(int count){
        return threadDelay * count / 1000;
    }
    public double getMiliseconds(int count){
        return threadDelay * count;
    }

    public int getThreadDelay() {
        return threadDelay;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private GameConfig(int threadDelay, int screenWidth, int screenHeight) {
        this.threadDelay = threadDelay;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    public boolean yOutsideScreen(int y){
        return y < 0 || y > screenHeight;
    }
    public boolean yOutsideScreen(GameObject gameObject){
        return yOutsideScreen(gameObject.getY());
    }
    public static final GameConfig instance = new GameConfig(DEFAULT_DELAY, DEFAULT_WIDTH, DEFAULT_HEIGHT);

}
