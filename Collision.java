import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Collision extends JFrame implements ActionListener{

    JPanel hs;
    JLabel highs, info;
    JMenuBar jm;
    JMenu fi, ed, inf;
    JMenuItem cl, gd, prop, cop, pas, con, ct, ms, ho;
    int n;
    HighscoreManager hm = new HighscoreManager();
    String title="Error";

    Board board=new Board();
    Add add=new Add();
    public Collision()
    {
        setLayout(new BorderLayout());
        jm=new JMenuBar();

	fi=new JMenu("File ");
	ed=new JMenu(" Edit ");
        inf=new JMenu (" Game Info ");

        cl=new JMenuItem ("Close", new ImageIcon("src/close.gif"));
        cl.addActionListener(this);
        ho=new JMenuItem ("High Scores");
        ho.addActionListener(this);
        gd=new JMenuItem("Contact Devesh");
        gd.addActionListener(this);
        prop=new JMenuItem ("Properites");
        prop.addActionListener(this);
	cop=new JMenuItem ("Copy Ctrl+C");
        cop.addActionListener(this);
        pas=new JMenuItem ("Paste Ctrl+V");
        pas.addActionListener(this);
	con=new JMenuItem ("Controls");
        con.addActionListener(this);
	ct=new JMenuItem ("Cut Ctrl+X");
        ct.addActionListener(this);
	ms=new JMenuItem ("Devesh Dayal Creations");

        jm.add(fi);
        jm.add(ed);
        jm.add(inf);
        jm.setBackground(Color.BLACK);

        fi.add(cl);
        fi.add(prop);
        fi.add(ho);
        fi.setBackground(Color.DARK_GRAY);
        ed.add(cop);
        ed.add(ct);
        ed.add(pas);
        ed.setBackground(Color.DARK_GRAY);
        inf.add(con);
        inf.add(gd);
        inf.add(ms);
        inf.setBackground(Color.DARK_GRAY);

        info=new JLabel("Email ID = devesh95@hotmail.com");

        add(jm);
        setJMenuBar(jm);
        add(new Board());
        add(new Add(), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setTitle("Mario With a Chance of Meatballs! ");
        setBackground(Color.BLACK);
        setResizable(false);
        setVisible(true);       
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cl)
        {
            System.exit(n);
        }
        if(e.getSource()==gd)
        {
            JOptionPane.showMessageDialog(null,info);
        }
        if(e.getSource()==cop)
        {
            JOptionPane.showMessageDialog(null,"What are you planning to copy in a game?");
        }
        if(e.getSource()==ct)
        {
            JOptionPane.showMessageDialog(null,"What are you planning to cut in a game?");
        }
        if(e.getSource()==pas)
        {
            JOptionPane.showMessageDialog(null,"What are you planning to paste in a game?");
        }
        if (e.getSource()==prop)
        {
            JOptionPane.showMessageDialog(null,"Mario With a Chance of Meatballs\nVersion 1.7\nGame Developer - Devesh\nPlease give feedback!");
        }
        if (e.getSource()==con)
        {
            JOptionPane.showMessageDialog(null,"CONTROLS:\nMove Mario = Left and Right\nFire Missiles = Space\nCheat Codes = Find out!");
        }
        if (e.getSource()==ho)
        {
            JOptionPane.showMessageDialog(null,hm.getHighscoreString());
        }
        if (e.getSource()==ms)
        {
            JOptionPane.showMessageDialog(null,"Why thank you very much!");
        }
    }
}
