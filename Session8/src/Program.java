import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by apple on 10/2/16.
 */
public class Program  {
    public static void main(String[] args){
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
