import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RunGame implements PropertyChangeListener {

	private JProgressBar progbar;
	private Task task;

	public RunGame(){

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (Exception e) {

		}
		showSplash(5000);
                HighscoreManager hm = new HighscoreManager();
//                hm.addScore("Devesh       ",36540);
//                hm.addScore("Prakrit     ",29954);
//                hm.addScore("Arunima     ",24644);
//                hm.addScore("Shourya     ",25203);
//                hm.addScore("Aman          ",169);
		new Collision();

        }


	public static void main(String args[])
	{
		new RunGame();
	}

	private  void showSplash(int duration) {

		JWindow splash = new JWindow();
		JPanel content = (JPanel) splash.getContentPane();
		int width = 397;
		int height = 298;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		splash.setBounds(x, y, width, height);
		JLabel label = new JLabel(new ImageIcon("src/Loading.GIF"));

		progbar = new JProgressBar(0,100);
		progbar.setValue(0);
		progbar.setStringPainted(true);
                progbar.setString("Loading...");
		progbar.setForeground(Color.BLUE);


		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();

		content.add(label, BorderLayout.CENTER);
		content.add(progbar, BorderLayout.SOUTH);
		splash.setVisible(true);
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}
		splash.setVisible(false);
	}


	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */

		public Void doInBackground() {
			Random random = new Random();
			int progress = 0;
			// Initialize progress property.
			setProgress(0);
			while (progress < 100) {
				// Sleep for  one second.
				try {
					Thread.sleep(random.nextInt(500));
				} catch (InterruptedException ignore) {
				}
				// Make random progress.
				progress += random.nextInt(10);
				setProgress(Math.min(progress, 100));
			}
			return null;
		}


		public void done() {

		}
	}


	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progbar.setValue(progress);

		}
	}

}
