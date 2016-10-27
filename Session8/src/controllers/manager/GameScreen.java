package controllers.manager;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by tu4nFPT on 25/10/2016.
 */
public abstract class GameScreen implements MouseListener,KeyListener,MouseMotionListener{
    protected ScreenManager screenManager;

    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public abstract void run();
    public abstract void update(Graphics graphics);
}
