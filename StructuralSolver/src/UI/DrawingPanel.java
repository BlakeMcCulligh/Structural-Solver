package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import drawing.DrawLine;
import drawing.SwitchTool;
import drawingObjects.Joint;
import drawingObjects.Line;
import drawingObjects.Panal;
import drawingObjects.Scene;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3700151035793102631L;
	Scene scene = new Scene();
	Select selector = new Select(scene);

	Point dragStart, dragEnd;
	Point panStart;

	double zoom = 1.0;

	int offsetX = 0, offsetY = 0;

	@SuppressWarnings("static-access")
	public DrawingPanel() {

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		JButton deselectBtn = new JButton("Deselect All");

		deselectBtn.addActionListener(e -> {
			selector.clearSelection();
			repaint();
		});

		scene.points.add(new Joint(100, 100));
		scene.points.add(new Joint(200, 100));
		scene.lines.add(new Line(new Joint(150, 150), new Joint(250, 150)));

		ArrayList<Joint> polyPoints = new ArrayList<>();
		polyPoints.add(new Joint(300, 200));
		polyPoints.add(new Joint(350, 250));
		polyPoints.add(new Joint(300, 300));

		scene.polygons.add(new Panal(polyPoints));

		selector.setMode(SelectMode.OFF, Integer.MAX_VALUE);
	}

	@Override
	@SuppressWarnings("static-access")
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		AffineTransform original = g2.getTransform();

		g2.translate(offsetX, offsetY);
		g2.scale(zoom, zoom);

		for (Joint p : scene.points) {
			p.draw(g2);
		}
		for (Line l : scene.lines) {
			l.draw(g2);
		}
		for (Panal poly : scene.polygons) {
			poly.draw(g2);
		}

		if (dragStart != null && dragEnd != null && Select.mode != SelectMode.OFF) {
			g2.setColor(Color.BLUE);
			int x = Math.min(dragStart.x, dragEnd.x);
			int y = Math.min(dragStart.y, dragEnd.y);
			int w = Math.abs(dragEnd.x - dragStart.x);
			int h = Math.abs(dragEnd.y - dragStart.y);
			g2.drawRect(x, y, w, h);
		}

		if (dragStart != null && dragEnd != null && SwitchTool.activeButton[1]) {
			g2.setColor(Color.BLACK);
			g2.drawLine(dragStart.x, dragStart.y, dragEnd.x, dragEnd.y);
		} else if (SwitchTool.activeButton[1] && DrawLine.clickNum == 1) {
			g2.setColor(Color.BLACK);
			g2.drawLine((int) DrawLine.click1.x, (int) DrawLine.click1.y, (int) DrawLine.mouse.x,
					(int) DrawLine.mouse.y);
		}

		g2.setTransform(original);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = transformPoint(e.getPoint());

		if (SwitchTool.activeButton[1]) {
			DrawLine.point(new Joint(p.x, p.y));
		}

		selector.additive = e.isShiftDown();
		selector.toggleSelection(p.x, p.y);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		selector.additive = e.isShiftDown();
		if (SwingUtilities.isMiddleMouseButton(e)) {
			panStart = e.getPoint();
		} else {
			dragStart = transformPoint(e.getPoint());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isMiddleMouseButton(e)) {
			panStart = null;
		} else if (dragStart != null && Select.mode != SelectMode.OFF) {
			dragEnd = transformPoint(e.getPoint());
			Rectangle box = new Rectangle(Math.min(dragStart.x, dragEnd.x), Math.min(dragStart.y, dragEnd.y),
					Math.abs(dragEnd.x - dragStart.x), Math.abs(dragEnd.y - dragStart.y));
			boolean strict = dragEnd.x < dragStart.x;
			selector.dragSelect(box, strict);
			dragStart = dragEnd = null;
			repaint();
		} else if (dragStart != null && SwitchTool.activeButton[1]) {
			dragEnd = transformPoint(e.getPoint());
			Double length = Math.sqrt(Math.pow(dragEnd.x - dragStart.x, 2) + Math.pow(dragEnd.y - dragStart.y, 2));
			if (length > 5) {
				DrawLine.LineFinish(dragStart.x, dragStart.y, dragEnd.x, dragEnd.y);
			}
			dragStart = dragEnd = null;
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (SwingUtilities.isMiddleMouseButton(e)) {
			Point current = e.getPoint();
			offsetX += current.x - panStart.x;
			offsetY += current.y - panStart.y;
			panStart = current;
			repaint();
		} else {
			dragEnd = transformPoint(e.getPoint());
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double delta = 0.1f * e.getPreciseWheelRotation();
		zoom -= delta;
		zoom = Math.max(0.1, Math.min(zoom, 10));
		repaint();
	}

	private Point transformPoint(Point p) {
		double x = (p.x - offsetX) / zoom;
		double y = (p.y - offsetY) / zoom;
		return new Point((int) x, (int) y);
	}
}
