import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

/**
 * Created by tu4nFPT on 08/10/2016.
 */
public class CreatEnemyPlanes extends Thread {

    private boolean isRun = true;
    Random r;
    public CreatEnemyPlanes( ) {
        r = new Random();
    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isRun) {
                int x = r.nextInt(GameWindow.BACKGROUND_WIDTH - Plane.PLANE_WIDTH);
                Plane enemyPlane = null;
                try {
                    enemyPlane = new Plane(x, 0, ImageIO.read(new File("resources/plane1.png")));
                } catch (Exception e) {
                }
                for (int i = 0; i < GameWindow.ENEMY_PLANES.size(); i++) {
                    GameWindow.ENEMY_PLANES.get(i).addBullet(false);
                }
                GameWindow.ENEMY_PLANES.add(enemyPlane);
            }
        }
    }
    public void stopThread(){
        isRun = false;
    }
    public void startThread(){
        isRun = true;
    }
}
