package tw.com.jerrycode.gameobject;

import java.awt.Image;

import tw.com.jerrycode.App;

public class PlayerTank extends Tank implements SuperFire {

    public PlayerTank(Image[] image, int x, int y, Direction direction) {
        super(image, x, y, direction, false);
        speed = 5;
        hp = 3;
    }

    @Override
    public void superFire() {
        if (!alive) {
            return;
        }

        Bullet bullet = new Bullet(App.gameClient.getbulletImage(), 0, 0, direction, getEnemy());
        int[] pos = getCenterPos(bullet.getRectangle());

        for (Direction direction : Direction.values()) {
            bullet = new Bullet(App.gameClient.getbulletImage(), pos[0], pos[1], direction, getEnemy());
            App.gameClient.getGameObjects().add(bullet);
        }
    }
}
