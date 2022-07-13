package tw.com.jerrycode.gameobject;

import java.awt.*;

public class Wall extends GameObject {
    // 是否水平
    private boolean horizontal;
    // 牆面數量
    private int bricks;

    public Wall(Image[] image, int x, int y, boolean horizontal, int bricks) {
        super(image, x, y);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < bricks; i++) {
            if (horizontal) {
                g.drawImage(image[0], x + i * width, y, null);
            } else {
                g.drawImage(image[0], x, y + i * height, null);
            }
        }
    }

}
