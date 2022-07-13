package tw.com.jerrycode;

import javax.swing.*;

import tw.com.jerrycode.gameobject.Direction;
import tw.com.jerrycode.gameobject.Tank;
import java.awt.*;
import java.awt.event.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        init();

    }

    public void run() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void init() {
        playerTank = new Tank(250, 250, Direction.UP);
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                playerTank.setY(playerTank.getY() - 5);
                break;

            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                playerTank.setY(playerTank.getY() + 5);
                break;

            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                playerTank.setX(playerTank.getX() - 5);
                break;

            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                playerTank.setX(playerTank.getX() + 5);
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);
    }

}
