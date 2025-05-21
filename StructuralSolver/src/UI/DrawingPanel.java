package UI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5995544671201203828L;

	public DrawingPanel() {

		setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawLine(0, 0, 20, 35);

	}


}
