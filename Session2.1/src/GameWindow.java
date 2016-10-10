import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tu4nFPT on 02/10/2016.
 */
public class GameWindow extends Frame implements Runnable {
    public static final int BACKGROUND_WIDTH = 800;
    public static final int BACKGROUND_HEIGHT = 600;
    public static ArrayList<Bullet> MY_BULLETS;
    public static ArrayList<Bullet> ENEMY_BULLETS;
    public static ArrayList<Plane> ENEMY_PLANES;
    private int score = 0;
    private boolean isRun = true;

    Image background = null;
    Image backBufferImage;

    CreatEnemyPlanes creatEnemyPlanes;
    Plane myPlane;

    SoundPlayer shot = new SoundPlayer(new File("sound/shot.wav"));
    SoundPlayer dead = new SoundPlayer(new File("sound/dead.wav"));

    public GameWindow() {
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        MY_BULLETS = new ArrayList<>();
        ENEMY_BULLETS = new ArrayList<>();
        ENEMY_PLANES = new ArrayList<>();
        creatEnemyPlanes = new CreatEnemyPlanes();
        creatEnemyPlanes.start();
        try {
            myPlane = new Plane(450, 550,
                    ImageIO.read(new File("resources/plane4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
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
                System.out.println("windowActivated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("windowDeactivated");
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_F5){
                    restartGame();
                }
            }
        });
        try {
            background = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(isRun) {
                    myPlane.mouseMoved(e);
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(isRun) {
                    myPlane.mouseMoved(e);
                    repaint();
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {
                  super.mousePressed(e);
                  if(isRun) {
                      shot.play();
                      myPlane.mousePressed();
                      repaint();
                  }
              }
          });
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        for (int i = 0; i < ENEMY_BULLETS.size(); i++) {
            ENEMY_BULLETS.get(i).drawImage(backBufferGraphics);
        }
        for (int i = 0; i < MY_BULLETS.size(); i++) {
            MY_BULLETS.get(i).drawImage(backBufferGraphics);
        }
        for (int i = 0; i < ENEMY_PLANES.size(); i++) {
            if (!ENEMY_PLANES.get(i).isDead(false)) {
                ENEMY_PLANES.get(i).drawImage(backBufferGraphics);
            }else{
                dead.play();
                score ++;
                ENEMY_PLANES.remove(i);
            }
        }
        backBufferGraphics.setColor(Color.RED);
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 30));
        if (myPlane.isDead(true)){
            dead.play();
            creatEnemyPlanes.stopThread();
            stopThread();
            backBufferGraphics.drawImage(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
            backBufferGraphics.drawString("You lose !", 340, 320);
            backBufferGraphics.drawString("Press F5 to play again !", 260, 350);
        }else {
            myPlane.drawImage(backBufferGraphics);
        }
        backBufferGraphics.drawString(String.valueOf(score), 20, 60);
        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true) {
            if(isRun) {
                for (int i = 0; i < ENEMY_PLANES.size(); i++) {
                    ENEMY_PLANES.get(i).fly();
                }
                for (int i = 0; i < ENEMY_BULLETS.size(); i++) {
                    ENEMY_BULLETS.get(i).fly(false);
                }
                for (int i = 0; i < MY_BULLETS.size(); i++) {
                    MY_BULLETS.get(i).fly(true);
                }
                repaint();
            }
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void stopThread(){
        isRun = false;
    }
    private void startThread(){
        isRun = true;
    }
    private void restartGame(){
        score = 0;
        MY_BULLETS = new ArrayList<>();
        ENEMY_BULLETS = new ArrayList<>();
        ENEMY_PLANES = new ArrayList<>();
        startThread();
        creatEnemyPlanes.startThread();
    }

}
