/*
 *  2004-9-28
 * Terry
 */
package ip;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main Class
 * 
 * @author terry
 */
public class IPSearch {

	public static void main (String[] args) {

		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LookAndFeel alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel();
			UIManager.setLookAndFeel(alloyLnF);
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		IPSearchUI.init(args);
		IPSearchUI frame = new IPSearchUI();

		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Center the window
		frame.setLocationRelativeTo(null);

		frame.setSize(480, 450);
		frame.setVisible(true);
	}
}