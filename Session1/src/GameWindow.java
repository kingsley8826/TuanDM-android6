import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by tu4nFPT on 02/10/2016.
 */
public class GameWindow extends Frame {
    Image background = null;
    Image plane3 = null;
    Image plane2 = null;
    private int plane3X = 400;
    private int plane3Y = 550;
    private int plane2X = 300;
    private int plane2Y = 550;

    public GameWindow() {
        this.setVisible(true);
        this.setSize(800, 600);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("windowClosing");
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
            plane3 = ImageIO.read(new File("resources/plane3.png"));
            plane2 = ImageIO.read(new File("resources/plane2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        plane3X += 10;
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        plane3X -= 10;
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        plane3Y += 10;
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        plane3Y -= 10;
                        repaint();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                plane2X = e.getX() - 25;
                plane2Y = e.getY() - 35;
                repaint();
            }
        });
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        g.drawImage(plane3, plane3X, plane3Y, 70, 51, null);
        g.drawImage(plane2, plane2X, plane2Y, 70, 56, null);
    }
}
