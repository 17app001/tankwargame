package tw.com.jerrycode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import tw.com.jerrycode.gameobject.Direction;
import tw.com.jerrycode.gameobject.GameObject;
import tw.com.jerrycode.gameobject.Tank;
import tw.com.jerrycode.gameobject.Wall;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;

    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<GameObject>();

    private Image[] bulletImage;

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

    public Image[] getbulletImage() {
        return bulletImage;
    }

    // 回傳所有遊戲物件容器
    public CopyOnWriteArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    // 啟動遊戲執行緒
    public void run() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(25);
                    System.out.println(gameObjects.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 讀取圖形跟初始遊戲物件
    void init() {
        // 牆面圖形
        Image[] wallImg = { new ImageIcon("assets/images/brick.png").getImage() };
        String[] ext = { "U", "D", "L", "R", "LU", "RU", "LD", "RD" };

        Image[] iTankImg = new Image[ext.length];
        Image[] eTankImg = new Image[ext.length];
        bulletImage = new Image[ext.length];

        // UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        for (int i = 0; i < ext.length; i++) {
            iTankImg[i] = new ImageIcon("assets/images/itank" + ext[i] + ".png").getImage();
            eTankImg[i] = new ImageIcon("assets/images/etank" + ext[i] + ".png").getImage();
            bulletImage[i] = new ImageIcon("assets/images/missile" + ext[i] + ".png").getImage();
        }

        // 玩家物件
        playerTank = new Tank(iTankImg, 380, 500, Direction.UP, false);
        // playerTank.setEnemy(true);
        playerTank.setSpeed(5);

        gameObjects.add(playerTank);
        // 產生敵方
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gameObjects.add(new Tank(eTankImg, 200 + j * 100, 50 + i * (eTankImg[0].getHeight(null) + 47),
                        Direction.DOWN, true));
            }
        }

        // 牆面配置
        gameObjects.add(new Wall(wallImg, 80, 10, false, 15));
        gameObjects.add(new Wall(wallImg, 140, 10, true, 10));
        gameObjects.add(new Wall(wallImg, 640, 10, false, 15));
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
            case KeyEvent.VK_CONTROL:
                playerTank.fire();
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

        // 多型
        for (GameObject object : gameObjects) {
            object.draw(g);
        }

        for (GameObject object : gameObjects) {
            if (!object.getAlive()) {
                gameObjects.remove(object);
            }
        }

    }
}
