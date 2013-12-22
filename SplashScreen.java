import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SplashScreen extends JWindow {

	ImageIcon splashImage = new ImageIcon(getClass().getResource("C:/Documents and Settings/ADMIN/DesktopLoading.gif"));
	JLabel jlblImage = new JLabel();
	JProgressBar jProgressBar1 = new JProgressBar();
	int width, height;
        String loadingtext="Loading Game...";
        int value=5;

	public SplashScreen() {
		super();
		try  {
			jbInit();

			// Center the screen
			Toolkit tk = this.getToolkit();

			width = 397;  // image is 500 wide
			height = 298; // image is 330 tall. Add a bit for the progress bar
			int x = (tk.getScreenSize().width - width) / 2;
			int y = (tk.getScreenSize().height - height) / 2;
			this.setLocation(x, y);
			this.setSize(width, height);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method stores all initialization commands for the window.
	 */
	private void jbInit() throws Exception {
		//jlblImage.setText("jLabel1");
		jlblImage.setIcon(splashImage);
		jProgressBar1.setStringPainted(true);
		this.getContentPane().add(jlblImage, BorderLayout.CENTER);
		this.getContentPane().add(jProgressBar1, BorderLayout.SOUTH);
	}


	/**
	 * Sets the text of the progress bar and its value
	 *
	 * @param msg The message to be displayed in the progress bar
	 * @param theVal An integer value from 0 to 100
	 */
	public void setStatus(String msg, int value) {
		jProgressBar1.setString(loadingtext);
		jProgressBar1.setValue(value);
	}
}