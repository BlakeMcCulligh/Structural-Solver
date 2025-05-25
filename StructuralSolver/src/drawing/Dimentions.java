package drawing;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import UI.MainWindow;
import drawingObjects.Scene;
import drawingObjects.Joint;
import drawingObjects.Line;

/**
 * Sets the length of a member to what is specifisized
 * 
 * @author Blake M.
 * 
 * @version 1.0 May 20, 2025
 * 
 */
public class Dimentions {
//
//	/**
//	 * List of Length Units to Select From
//	 */
//	public static String[] LengthUnits = { "mm", "m", "in", "ft" };
//
//	/**
//	 * Click Listeners
//	 */
//	public static RightClickListener1 rClick1;
//	public static RightClickListener2 rClick2;
//
//	/**
//	 * Called when the dimention button is pressed
//	 */
//	public static void dimention() {
//
//		if (Scene.SelectedLine == -1) {
//
//			rClick1 = new RightClickListener1();
//
//			MainWindow.DrawingPanel.addMouseListener(rClick1);
//
//		} else {
//
//			getClick();
//
//		}
//
//	}
//
//	/**
//	 * sets up the second click listener to get the loscation of the dimentioning
//	 */
//	public static void getClick() {
//		rClick2 = new RightClickListener2();
//		MainWindow.DrawingPanel.addMouseListener(rClick2);
//	}
//
//	/**
//	 * gets the the new dimentions with value and units
//	 * 
//	 * @param click location to put dimentioning interface
//	 */
//	public static void getDist(Point click) {
//
//		MainWindow.DrawingPanel.removeMouseListener(Dimentions.rClick2);
//
//		JPanel DoublePanal = new JPanel();
//		DoublePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
//
//		JTextField DoubleTextBox = new JTextField(8);
//		DoublePanal.add(DoubleTextBox);
//
//		// making the text box doubles only
//		DoubleTextBox.getDocument().addDocumentListener(new DocumentListener() {
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				Runnable format = new Runnable() {
//					@Override
//					public void run() {
//						String text = DoubleTextBox.getText();
//						if (!text.matches("\\d*(\\.\\d*)?")) {
//							DoubleTextBox.setText(text.substring(0, text.length() - 1));
//						}
//					}
//				};
//				SwingUtilities.invokeLater(format);
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//
//			}
//
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//
//			}
//		});
//
//		JComboBox<String> DoubleUnits = new JComboBox<>(LengthUnits);
//		DoublePanal.add(DoubleUnits);
//
//		JButton DimentionB = new JButton("Dimention");
//		DimentionB.addActionListener(
//				e -> setDimention(DoubleTextBox.getText(), DoubleUnits.getSelectedItem().toString(), DoublePanal));
//		DoublePanal.add(DimentionB);
//
//		DoublePanal.setMaximumSize(
//				new Dimension(DoublePanal.getPreferredSize().width, DoublePanal.getPreferredSize().height));
//		MainWindow.DrawingPanel.add(DoublePanal);
//
//		MainWindow.DrawingPanel.validate();
//		DoublePanal.setLocation(click);
//		MainWindow.DrawingPanel.repaint();
//
//	}
//
//	/**
//	 * sets the length of the selected line to line specificized in the textbox and
//	 * unit drop down
//	 * 
//	 * @param distance    String string with double value to be made the distance
//	 * @param Units       Units of the double value inputed
//	 * @param DoublePanal the panel that was used to get the distance information
//	 */
//	public static void setDimention(String distanceString, String Units, JPanel DoublePanal) {
//
//		MainWindow.DrawingPanel.remove(DoublePanal);
//
//		double distance = Double.valueOf(distanceString);
//
//		distance = main.UnitConvertion.linearTomm(distance, Units);
//
//		Line L = Scene.Lines.get(Scene.SelectedLine);
//
//		double distHypotinuse = Math
//				.sqrt(Math.pow(L.p[1].y - L.p[0].y, 2) + Math.pow(L.p[1].x - L.p[0].x, 2));
//
//		double multiplyer = distance / distHypotinuse;
//
//		L.p[1].y = (L.p[0].y + (L.p[1].y - L.p[0].y) * multiplyer);
//		L.p[1].x = (L.p[0].x + (L.p[1].x - L.p[0].x) * multiplyer);
//
//		Scene.Lines.set(Scene.SelectedLine, L);
//
//		MainWindow.DrawingPanel.validate();
//		MainWindow.DrawingPanel.repaint();
//
//	}
//
//	/**
//	 * Gets the distance a point is form a line represented as to p
//	 * 
//	 * @param p1 First point of the line
//	 * @param p2 Second point of the line
//	 * @param c  point to find distance to
//	 * @return distance form point to line
//	 */
//	public static double distanceFromLine(Joint p1, Joint p2, Point c) {
//		double distance = 0.0;
//
//		double denominator = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
//
//		if (denominator == 0) {
//			return Double.MAX_VALUE;
//		}
//
//		double numerator = Math.abs((p2.x - p1.x) * (c.y - p1.y) - (p2.y - p1.y) * (c.x - p1.x));
//
//		distance = numerator / denominator;
//
//		return distance;
//	}
//
//}
//
///**
// * fineds if a line has been selcted and if so siart the dimentioning process
// */
//class RightClickListener1 extends MouseAdapter {
//
//	public void mousePressed(MouseEvent e) {
//
//		Point click = MouseInfo.getPointerInfo().getLocation();
//		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {
//			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);
//
//			for (int i = 0; i < Scene.Lines.size(); i++) {
//				if (Dimentions.distanceFromLine(Scene.Lines.get(i).p[0],
//						Scene.Lines.get(i).p[1], click) < 10) {
//					Scene.SelectedLine = i;
//					MainWindow.DrawingPanel.removeMouseListener(Dimentions.rClick1);
//					Dimentions.getClick();
//				}
//			}
//
//		}
//
//	}
//}
//
///**
// * finds if a location for the dimentioning disply has been selected
// */
//class RightClickListener2 extends MouseAdapter {
//
//	public void mousePressed(MouseEvent e) {
//
//		Point click = MouseInfo.getPointerInfo().getLocation();
//		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {
//			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);
//
//			Dimentions.getDist(click);
//		}
//	}
//}
}
