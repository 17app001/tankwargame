package tw.com.jerrycode.gameobject;

import java.awt.*;

public class Tank extends GameObject {
    private int speed;
    private boolean[] dirs;
    private boolean enemy;

    private Direction direction;

    public Tank(Image[] image, int x, int y, Direction direction, boolean enemy) {
        super(image, x, y);
        this.direction = direction;
        this.enemy = enemy;
        speed = 5;
        dirs = new boolean[4];
    }

    public void setDirs(boolean[] dirs) {
        this.dirs = dirs;
    }

    public boolean getEnemy() {
        return this.enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // 為了避免上下或者左右一起按
    // 上下左右(0,1,2,3)
    private void determineDirection() {
        // 上左(0,2)
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) {
            direction = Direction.UP_LEFT;
            // 上右(0,3)
        } else if (dirs[0] && !dirs[2] && !dirs[1] && dirs[3]) {
            direction = Direction.UP_RIGHT;
            // 下左(1,2)
        } else if (!dirs[0] && dirs[2] && dirs[1] && !dirs[3]) {
            direction = Direction.DOWN_LEFT;
            // 下右(1,3)
        } else if (!dirs[0] && !dirs[2] && dirs[1] && dirs[3]) {
            direction = Direction.DOWN_RIGHT;
            // 上(0)
        } else if (dirs[0] && !dirs[2] && !dirs[1] && !dirs[3]) {
            direction = Direction.UP;
            // 下(1)
        } else if (!dirs[0] && !dirs[2] && dirs[1] && !dirs[3]) {
            direction = Direction.DOWN;
            // 左(2)
        } else if (!dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) {
            direction = Direction.LEFT;
            // 右(3)
        } else if (!dirs[0] && !dirs[2] && !dirs[1] && dirs[3]) {
            direction = Direction.RIGHT;
        }
    }

    // 移動方法
    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                x -= speed;
                y -= speed;
                break;
            case UP_RIGHT:
                x += speed;
                y -= speed;
                break;
            case DOWN_LEFT:
                x -= speed;
                y += speed;
                break;
            case DOWN_RIGHT:
                x += speed;
                y += speed;
                break;
        }
    }

    // 是否停止
    public boolean isStop() {
        for (boolean dir : dirs) {
            if (dir) {
                return false;
            }
        }

        return true;
    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }

        g.drawImage(image[direction.ordinal()], x, y, null);
    }

}
