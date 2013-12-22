
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class item {

    private String item = "craft.png";

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    double x1;
    public item(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(item));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        double x1= 600*Math.random();
        x=(int)x1;

        this.y = y;
    }


    public void move() {
        if (y > 400)
        {
            x1=600*Math.random();
            x=(int)x1;
            y = 0;
        }
        y += 1;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
