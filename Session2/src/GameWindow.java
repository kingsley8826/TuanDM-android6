import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by tu4nFPT on 02/10/2016.
 */
public class GameWindow extends Frame implements Runnable {
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;

    Image background = null;
    Image backBufferImage;
    Plane plane1;
    Plane plane2;
    public GameWindow() {
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        try {
            plane1 = new Plane(350, 550,
                    ImageIO.read(new File("resources/plane3.png")));
            plane2 = new Plane(450, 550,
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
        try {
            background = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                plane1.keyPressed(e);
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                plane2.mouseMoved(e);
                repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {
                  super.mousePressed(e);
                  plane2.mousePressed();
                  repaint();
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
        plane1.drawImage(backBufferGraphics);
        plane2.drawImage(backBufferGraphics);
        if(!plane1.getBullets().isEmpty()) {
            for (int i = 0; i < plane1.getBullets().size(); i++){
                plane1.getBullets().get(i).drawImage(backBufferGraphics);
            }
        }
        if(!plane2.getBullets().isEmpty()) {
            for (int i = 0; i < plane2.getBullets().size(); i++){
                plane2.getBullets().get(i).drawImage(backBufferGraphics);
            }
        }
        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true) {
            try {

                if(!plane2.getBullets().isEmpty()) {
                    for (int i = 0; i < plane2.getBullets().size(); i++){
                        plane2.getBullets().get(i).fly();
                    }
                }
                if(!plane1.getBullets().isEmpty()) {
                    for (int i = 0; i < plane1.getBullets().size(); i++){
                        plane1.getBullets().get(i).fly();
                    }
                }
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
