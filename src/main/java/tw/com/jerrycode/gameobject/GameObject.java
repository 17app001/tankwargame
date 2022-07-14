package tw.com.jerrycode.gameobject;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int oldX;
    protected int oldY;
    protected int width;
    protected int height;

    protected Image[] image;

    public GameObject(Image[] image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
        width = image[0].getWidth(null);
        height = image[0].getHeight(null);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);

    }

    public abstract void draw(Graphics g);

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
}
