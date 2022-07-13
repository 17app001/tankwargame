package tw.com.jerrycode;

import javax.swing.*;

import tw.com.jerrycode.gameobject.Direction;
import tw.com.jerrycode.gameobject.Tank;
import tw.com.jerrycode.gameobject.Wall;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;
    private ArrayList<Tank> enemyTanks = new ArrayList<Tank>();
    private ArrayList<Wall> walls = new ArrayList<Wall>();

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        // 遊戲初始
        init();
    }

    // 啟動遊戲執行緒
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

    // 讀取圖形跟初始遊戲物件
    void init() {
        // 牆面圖形
        Image wallImg = new ImageIcon("assets/images/brick.png").getImage();

        // 玩家物件
        playerTank = new Tank(380, 500, Direction.UP, false);
        playerTank.setSpeed(5);
        // 產生敵方
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                enemyTanks.add(new Tank(200 + j * 60, 50 + i * 60, Direction.DOWN, true));
            }
        }
        // 牆面配置
        walls.add(new Wall(wallImg, 80, 10, false, 15));
        walls.add(new Wall(wallImg, 140, 10, true, 10));
        walls.add(new Wall(wallImg, 640, 10, false, 15));
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);
        // 繪製玩家
        playerTank.draw(g);
        // 繪製敵方
        for (Tank enemy : enemyTanks) {
            enemy.draw(g);
        }
        // 繪製牆面
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }
}
