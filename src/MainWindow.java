import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
				try {
					getClicks();
				} catch (AWTException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void createGUI() {
		final JFrame frame = new JFrame("Pixel Color Test");
		frame.setSize(320, 100);
		frame.setPreferredSize(new Dimension(320, 100));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel pixelInfo = new JLabel("No clicks to process.");
		pixelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(pixelInfo);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		frame.setVisible(true);
	}
	
	private static void getClicks() throws AWTException, InterruptedException {
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point coord = pointer.getLocation();
		Robot robot = new Robot();
		robot.delay(2000);
		
		while(true) {
			coord = MouseInfo.getPointerInfo().getLocation();
			Color color = robot.getPixelColor((int)coord.getX(), (int)coord.getY());
			System.out.println("Color at pixel (" + coord.getX() + ", " + 
						coord.getY() + "): " + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ".");
			Thread.sleep(200);
		}
	}
}
