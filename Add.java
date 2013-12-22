import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Add extends JPanel implements ActionListener{

    public int tt;
    JTextField tf,tf2;
    JLabel na,sc;
    JButton submit;

    public Add() {
        tf=new JTextField(5);
        na=new JLabel("Enter Your Name");
        tf2=new JTextField(5);
        sc=new JLabel("Enter Your Score");
        submit=new JButton("SUBMIT SCORE");
        submit.addActionListener(this);
        add(na);
        add(tf);
        add(sc);
        add(tf2);
        add(submit);
        setBackground(Color.BLACK);
        na.setForeground(Color.WHITE);
        sc.setForeground(Color.WHITE);
    }

    public void actionPerformed (ActionEvent e)
    {
        tf.getText();
        String s1=tf.getText();
        tf2.getText();
        String s2=tf2.getText();
        int s=Integer.parseInt(s2);

        if (e.getSource()==submit)
        {
            HighscoreManager hm=new HighscoreManager();
            Board b=new Board();
            b.getScore(tt);
            hm.addScore(s1+"      ",s);
            tf.setText(" ");
            tf2.setText(" ");
        }
    }
}
