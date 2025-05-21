package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import UI.MainWindow;
import drawingObjects.DrawingObjects;

public class MAIN {
	

	public static MainWindow masterWindow;
	
	public static void main(String[] args) {

		masterWindow = new MainWindow();
		
		
		
	}
	
	public static void print() {
		
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;


			protected void paintComponent(Graphics g) {

				super.paintComponent(g);

				for (int i = 0; i < DrawingObjects.Lines.size(); i++) {
					g.drawLine(DrawingObjects.Lines.get(i).Points[0].x, DrawingObjects.Lines.get(i).Points[0].y,
							DrawingObjects.Lines.get(i).Points[1].x, DrawingObjects.Lines.get(i).Points[1].y);
				}

			}
		};

		masterWindow.UpadateDrawingPanel(panel);


	}

}
