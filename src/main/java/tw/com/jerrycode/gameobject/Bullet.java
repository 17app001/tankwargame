package tw.com.jerrycode.gameobject;

import java.awt.*;
import tw.com.jerrycode.App;

public class Bullet extends Tank {

    public Bullet(Image[] image, int x, int y, Direction direction, boolean enemy) {
        super(image, x, y, direction, enemy);
        setSpeed(10);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }

        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void collision() {
        // 邊界偵測
        if (iscollisionBound()) {
            alive = false;
            return;
        }

        // 跟其物件偵測
        // 偵測其他物件(使用多型)
        for (GameObject object : App.gameClient.getGameObjects()) {
            if (object == this) {
                continue;
            }

            if (object instanceof Tank) {
                if (((Tank) object).isEmeny() == getEnemy()) {
                    continue;
                }
                // 子彈攻擊敵方
                if (getRectangle().intersects(object.getRectangle())) {
                    object.alive = false;
                    alive = false;
                    continue;
                }
            }

            // 實際偵測碰撞
            if (getRectangle().intersects(object.getRectangle())) {
                alive = false;
                return;
            }
        }
    }
}
