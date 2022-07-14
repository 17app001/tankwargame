package tw.com.jerrycode.gameobject;

import java.awt.*;

public class Bullet extends Tank {

    public Bullet(Image[] image, int x, int y, Direction direction, boolean enemy) {
        super(image, x, y, direction, enemy);
    }

    @Override
    public void draw(Graphics g) {
        move();
        collision();

        g.drawImage(image[direction.ordinal()], x, y, null);
    }
}
