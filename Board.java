
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.sound.midi.*;


public class Board extends JPanel implements ActionListener {
    int time=1;
    public int tt,d=5;
    public int got;
    String st="load.PNG";
    String st1="twibg.png";
    String st2="snowbg.png";
    String st3="valley.JPG";
    String st4="endbg.PNG";
    String st5="air.PNG";
    String st6="space.PNG";
    Image loading;
    Image im1;
    Image im2;
    Image im3;
    Image im4;
    Image im5;
    Image im6;
    private Timer level1,level2, level3,level4,level5,level6;
    private Craft craft;
    private ArrayList aliens,items;
    boolean ingame,load;
    private int B_WIDTH;
    private int B_HEIGHT;
    ArrayList missiles1;
    Music music= new Music();
    SourceDataLine soundLine = null;
    int BUFFER_SIZE = 64*1024;
    JTextField tf;
    

    private int[][] pos = {
        {506, 45}, {250, 59}, {180, 39},
        {380, 90}, {80, 200}, {308, 190},
        {79, 259}, {245, 50}, {298, 150},
        {45, 209}, {560, 45}, {134, 70},
        {153, 159}, {402, 80}, {468, 60},
        {400, 59}, {78, 30}, {500, 200},
        {32, 259}, {358, 150}, {41, 390},
        {211, 220}, {460, 20}, {300, 80},
        {280, 28}, {340, 70}, {326, 30},
        {480, 45}, {189, 287}, {15, 20},
        {578,40}, {300, 100}
     };
    private int[] [] pos2 = {
        {200,300}, {376, 286}
    };

    public Board() {


        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        load=false;
        ingame=true;
        setSize(600, 400);
        craft = new Craft();
        initAliens();
        //initItems();
        level1 = new Timer(9, this);
        level2 = new Timer(8, this);
        level3 = new Timer(7, this);
        level4 = new Timer(6, this);
        level5 = new Timer(5, this);
        level6 = new Timer(4, this);
        level1.start();
        MusicBackground2();
        //Displayscores();
        tf=new JTextField(10);
    }

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    public void initAliens() {
        aliens = new ArrayList();

        for (int i=0; i<pos.length; i++ ) {
            aliens.add(new Alien(pos[i][0], pos[i][1]));
        }
    }

//    public void initItems() {
//        items = new ArrayList();
//
//        for (int i=0; i<pos2.length; i++) {
//            items.add(new item(pos2[i][0], pos2[i][1]));
//        }
//    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        ImageIcon loadingii=new ImageIcon(this.getClass().getResource(st));
        loading=loadingii.getImage();

        ImageIcon im1ii=new ImageIcon(this.getClass().getResource(st2));
        im1=im1ii.getImage();

        ImageIcon im2ii=new ImageIcon(this.getClass().getResource(st1));
        im2=im2ii.getImage();

        ImageIcon im3ii=new ImageIcon(this.getClass().getResource(st3));
        im3=im3ii.getImage();

        ImageIcon im4ii=new ImageIcon(this.getClass().getResource(st4));
        im4=im4ii.getImage();

        ImageIcon im5ii=new ImageIcon(this.getClass().getResource(st5));
        im5=im5ii.getImage();

        ImageIcon im6ii=new ImageIcon(this.getClass().getResource(st6));
        im6=im6ii.getImage();

        if (ingame) {

            g2d.drawImage(im1, 0,0, this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms = craft.getMissiles();

            for (int i = 0; i < ms.size(); i++) {
                Missile m = (Missile)ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
               }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
            //}
            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);

            if (tt>3000)
            {
                level1.stop();
                level2.start();
                g2d.drawImage(im2,0,0,this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms1 = craft.getMissiles();

            for (int i = 0; i < ms1.size(); i++) {
                Missile m = (Missile)ms1.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
//            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);

            }

            if(tt>6000)
            {
                level2.stop();
                level3.start();
                g2d.drawImage(im3,0,0,this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms2 = craft.getMissiles();

            for (int i = 0; i < ms2.size(); i++) {
                Missile m = (Missile)ms2.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
//            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);

            }
            if (tt>9000)
            {
                level3.stop();
                level4.start();
                g2d.drawImage(im4,0,0,this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms4 = craft.getMissiles();

            for (int i = 0; i < ms4.size(); i++) {
                Missile m = (Missile)ms4.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
//            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);

            }
            if (tt>11000)
            {
                level4.stop();
                level5.start();
                g2d.drawImage(im5,0,0,this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms1 = craft.getMissiles();

            for (int i = 0; i < ms1.size(); i++) {
                Missile m = (Missile)ms1.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
             }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
//            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);

            }
            if (tt>15000)
            {
                level5.stop();
                level6.start();
                g2d.drawImage(im6,0,0,this);
                if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList ms1 = craft.getMissiles();

            for (int i = 0; i < ms1.size(); i++) {
                Missile m = (Missile)ms1.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien a = (Alien)aliens.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
//            for (int i = 0; i< items.size(); i++) {
//                item it = (item)items.get(i);
//                if (it.isVisible())
//                    g2d.drawImage(it.getImage(), it.getX(),it.getY(),this);
//            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Your Score = " + tt,5,15);
            g2d.drawString("Meatballs left falling: " + aliens.size(), 5, 30);
            missiles1=craft.getMissiles();
            g2d.drawString("Missiles left: "+ (5-missiles1.size()),5,45);
            }


        } else {

            if(tt>2000){
            got=tt;
            String msg = "Game Over!";
            String ms = "Your Score= " +tt;
            String ms2 = "Game Developer = Devesh Dayal";
            //String thanks= "Answer = allyourbasebelongstous";
            //String ms3 = "Music Developer = Shourya Malhotra";
            //String ms4 = "Graphics Design = Aman Gupta";
            String close="To Exit Press ESC or N";
            String add="Name = ";
            Font small = new Font("Helvetica Neue Light", Font.BOLD, 26);
            FontMetrics metr = this.getFontMetrics(small);


            g.setColor(Color.WHITE);
            g.setFont(small);
            g.drawString(msg,215,100);
            g.drawString(ms, 190, 150);
            g.drawString(ms2,100, 200);
            //g.drawString(thanks,110,250);
            //g.drawString(ms3,90,250);
            //g.drawString(close,150,250);
           // g.drawString(add, 150, 300);

            }
        else
    {
            got=tt;
            String msg = "Game Over!";
            String ms = "Your Score= " +tt;
            String ms2 = "Game Developer = Devesh Dayal";
            //String thanks= "Sorry, your score isn't high enough!";
            //String ms3 = "Music Developer = Shourya Malhotra";
            //String ms4 = "Graphics Design = Aman Gupta";
            String close="To Exit Press ESC or N";
            String add="Name = ";
            Font small = new Font("Helvetica", Font.BOLD, 26);
            FontMetrics metr = this.getFontMetrics(small);


            g.setColor(Color.WHITE);
            g.setFont(small);
            g.drawString(msg,215,100);
            g.drawString(ms, 190, 150);
            g.drawString(ms2,100, 200);
            //g.drawString(thanks,110,250);
            //g.drawString(ms3,90,250);
            //g.drawString(close,150,250);
           // g.drawString(add, 150, 300);

    }

        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    public void actionPerformed(ActionEvent e) {

        tt=tt+time;

        if ((ingame==false) || (aliens.size()==0)) {
            ingame = false;
            time=0;

        }

        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible())
                m.move();
            else ms.remove(i);
        }

        for (int i = 0; i < aliens.size(); i++) {
            Alien a = (Alien) aliens.get(i);
            if (a.isVisible())
                a.move();
            else aliens.remove(i);
        }
        /*
            for (int i = 0; i < items.size(); i++) {
            item it = (item) items.get(i);
            if (it.isVisible())
                it.move();
            else items.remove(i);
        }
         * 
         */

        
        craft.move();
        checkCollisions();
        repaint();
    }
    

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

        for (int j = 0; j<aliens.size(); j++) {
            Alien a = (Alien) aliens.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                craft.setVisible(false);
                a.setVisible(false);
                //MusicDeath();
                ingame=false;
            }
        }
        /*
        for (int j = 0; j>items.size(); j++) {
            item it = (item) items.get(j);
            Rectangle r = it.getBounds();

            if (r3.intersects(r)) {
                craft.isVisible();
                it.setVisible(false);
            }
        }
         * 
         */

        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<aliens.size(); j++) {
                Alien a = (Alien) aliens.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(true);
                    a.setVisible(false);
                }
            }
        }
    }
    public void MusicDeath()
    {               
        try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource("nsmb_death.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }

    }
       public void MusicBackground()
    {
          if (ingame=true) {
              music.startMidi("C:/menu.mid");
          }
          else if (ingame=false){
              music.stopMidi("C:/menu.mid");

            }
    }
       public void MusicBackground2()
    {
         try {
        // From file
            Sequence sequence = MidiSystem.getSequence(new File("C:/menu.mid"));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);

        // Start playing
            if(ingame==true){
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
             }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (MidiUnavailableException e) {
        } catch (InvalidMidiDataException e) {
    }

}
    public void Displayscores() {
       if (time==0) {
            HighscoreManager hm = new HighscoreManager();
            JOptionPane.showMessageDialog(null,hm.getHighscoreString());
        }
    }

    public void keyReleased2(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_T)
        {
            JOptionPane.showMessageDialog(null,"Cheater!");
        }
    }

    int getScore(int a)
    {
        a=tt;
        return tt;
    }
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
            keyReleased2(e);
        }
     
        
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}

