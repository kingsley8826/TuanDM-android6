
import controllers.*;
import models.*;
import utils.Utils;
import views.PlaneView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    int backgroundWidth = GameConfig.instance.getScreenWidth();
    int backgroundHeight = GameConfig.instance.getScreenHeight();

    Image backgroundImage = null;
    Image backBufferImage;
    PlaneController planeController;
    PlaneController planeController2;
    Vector<BaseController> controllers;

    public GameWindow() {
        controllers = new Vector<>();
        planeController = PlaneController.planeController;
        planeController2 = PlaneController.planeController2;

        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);

        controllers.add(planeController);
        controllers.add(planeController2);
        controllers.add(new EnemyPlaneControllerManager());
        controllers.add(CollisionPool.instance);
        this.setVisible(true);
        this.setSize(backgroundWidth, backgroundHeight);
        this.setLocationRelativeTo(null);

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

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                planeController2.mousePressed(e);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
                planeController.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased");
                planeController.keyReleased(e);
            }

        });

        try {
            backgroundImage = ImageIO.read (
                    new File("resources/background.png"));
            System.out.println("Loaded backgroundImage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
        for(BaseController baseController : controllers){
            baseController.draw(backBufferGraphics);
        }
        g.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }


    @Override
    public void run() {
        while(true) {
            try {
//                for(BaseController baseController : controllers){
//                    baseController.run();
//                }
                Iterator<BaseController> iterator = controllers.iterator();
                while(iterator.hasNext()) {
                    BaseController baseController = iterator.next();
                    if (baseController instanceof GameController) {
                        GameObject gameObject =
                                ((GameController) baseController).getGameObject();
                        if(!gameObject.isAlive()) {
                            iterator.remove();
                        } else {
                            baseController.run();
                        }
                    } else {
                        baseController.run(); // Manager
                    }
                }
                Thread.sleep(GameConfig.instance.getThreadDelay());
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
