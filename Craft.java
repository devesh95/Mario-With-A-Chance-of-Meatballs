/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Craft {

    private String craft = "craft.png";
    private String craft2= "craft2.png";
    private String craft3= "craft3.png";
    private String craft4= "craft4.png";

    int count=1;
    private int dx;
    private int dy;
    private int x;
    private int y;
    int n;
    private int width;
    private int height;
    private boolean visible,jump=false;
    private Image image;
    ArrayList missiles;


    public Craft() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        missiles = new ArrayList();
        visible = true;
        x = 300;
        y = 340;
    }


    public void move() {

        x += dx;
        y += dy;



        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        if (y > 340) {
            y=340;
        }
        if (x>580) {
            x=580;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(craft2));
            image = ii.getImage();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
            image = ii.getImage();
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(craft3));
            image = ii.getImage();

        }
        if (key == KeyEvent.VK_DOWN) {
            dy = +1;
        }
    }

    public void fire() {
        if (missiles.size()<5)
        missiles.add(new Missile(x + width, y +height/2));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key== KeyEvent.VK_SPACE) {

        }

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if ((key==KeyEvent.VK_ESCAPE) || (key==KeyEvent.VK_N))
        {
            //Collision c=new Collision();
            System.exit(n);
        }       
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if ((key==KeyEvent.VK_P)) {

        }

    }
}
