package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Root window for all the applications subviews
 */
public class RootWindow implements Viewable{
	private JFrame window;
	
	public RootWindow() throws IOException{
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 600);		
		BufferedImage icon = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
		window.setIconImage(icon);
	}

	public JFrame getWindow() {
		return window;
	}

	@Override
	public void show() {
		window.setVisible(true);
	}
	
	/**
	 * Replaces the old root panel with the new one
	 * @param panel The new root panel
	 */
	public void replaceRoot(JPanel panel){
		window.setContentPane(panel);
	}
	
}
