package tw.com.jerrycode;

import java.awt.event.*;
import javax.swing.*;

/**
 * Hello world!
 */
public final class App extends JFrame {
    // 靜態唯一，可以直接取用
    public static GameClient gameClient;

    private App() {
        setTitle("坦克大戰");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gameClient = new GameClient();
        add(gameClient);

        pack();
        setVisible(true);

        // 遊戲區域重新繪製
        gameClient.run();

        // 按鍵偵測
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        new App();
    }
}
