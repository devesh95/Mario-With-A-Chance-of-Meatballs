import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Begin extends JPanel implements ActionListener{

        JButton beg;
        JLabel te;
        public Begin()
        {
            beg=new JButton("Start Game");
            beg.addActionListener(this);
            te=new JLabel("To Start Press Enter");
            add(te);
            add(beg);
            setBackground(Color.BLACK);
        }
        public void actionPerformed (ActionEvent e)
        {
            if(e.getSource()==beg)
            {
                Collision c=new Collision();
                c.remove(new Begin());
                c.add(new Board());
                c.add(new Add(), BorderLayout.SOUTH);
            }
        }
}

