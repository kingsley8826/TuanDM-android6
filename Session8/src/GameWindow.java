
import controllers.Screens.LaunchGameScreen;
import controllers.Screens.PlayGameScreen;
import controllers.manager.*;
import models.*;
import utils.Utils;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable,ScreenManager {


    Image backBufferImage;

    int backgroundWidth = GameConfig.instance.getScreenWidth();
    int backgroundHeight = GameConfig.instance.getScreenHeight();

    private GameScreen currentGameScreen;
    private Stack<GameScreen> screenStack;

    public GameWindow() {
        screenStack = new Stack<>();
        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);

        this.setVisible(true);
        this.setSize(backgroundWidth, backgroundHeight);
        this.setLocationRelativeTo(null);

        change(new LaunchGameScreen(this),true);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        Utils.playSound("resources/track.wav", true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        currentGameScreen.update(backBufferGraphics);
        g.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }


    @Override
    public void run() {
        while(true) {
            try {
                currentGameScreen.run();
                Thread.sleep(GameConfig.instance.getThreadDelay());
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void change(GameScreen newGameScreen, boolean addToBackstack) {
        if(currentGameScreen != null) {
            this.detach(currentGameScreen);
        }

        if(addToBackstack && currentGameScreen != null) {
            screenStack.push(currentGameScreen);
        }
        this.attach(newGameScreen);
    }

    public void back() {
        if(screenStack.size() > 0) {
            GameScreen newGameScreen = screenStack.pop();
            detach(currentGameScreen);
            attach(newGameScreen);
        }
    }

    private void attach(GameScreen newGameScreen) {
        this.currentGameScreen = newGameScreen;
        this.addMouseListener(newGameScreen);
        this.addMouseMotionListener(newGameScreen);
        this.addKeyListener(newGameScreen);
    }

    private void detach(GameScreen oldGameScreen) {
        this.removeKeyListener(oldGameScreen);
        this.removeMouseListener(oldGameScreen);
        this.removeMouseMotionListener(oldGameScreen);
    }
}
