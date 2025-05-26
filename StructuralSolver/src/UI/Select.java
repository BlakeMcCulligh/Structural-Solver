package UI;

import drawingObjects.Joint;
import drawingObjects.Line;
import drawingObjects.Panal;
import drawingObjects.Scene;

import java.awt.*;
import java.util.ArrayList;

//Geometry classes

enum SelectMode {
	POINT, LINE, POLYGON, ALL, OFF
}

public class Select {
	Scene scene;
	public static SelectMode mode = SelectMode.ALL;
	static int maxSelection = Integer.MAX_VALUE;
	boolean additive = false;

	static class SelectedItem {
		int index;
		Object item;
		SelectMode type;

		SelectedItem(int index, Object item, SelectMode type) {
			this.index = index;
			this.item = item;
			this.type = type;
		}
	}

	static ArrayList<SelectedItem> selectedItems = new ArrayList<>();

	Select(Scene scene) {
		this.scene = scene;
	}

	static void setMode(SelectMode m, int max) {
		mode = m;
		maxSelection = max;
		clearSelection();
	}

	static void clearSelection() {
		for (SelectedItem item : selectedItems) {
			if (item.type == SelectMode.POINT)
				((Joint) item.item).selected = false;
			else if (item.type == SelectMode.LINE)
				((Line) item.item).selected = false;
			else if (item.type == SelectMode.POLYGON)
				((Panal) item.item).selected = false;
		}
		selectedItems.clear();
	}

	@SuppressWarnings("static-access")
	void toggleSelection(int x, int y) {
		if (!additive)
			clearSelection();
		if (mode == SelectMode.POINT || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.points.size(); i++) {
				Joint p = scene.points.get(i);
				if (x >= p.x - 5 && x <= p.x + 5 && y >= p.y - 5 && y <= p.y + 5) {
					toggle(p, i, SelectMode.POINT);
					return;
				}
			}
		}
		if (mode == SelectMode.LINE || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.lines.size(); i++) {
				Line l = scene.lines.get(i);
				if (isNearLine(x, y, l.p[0].x, l.p[0].y, l.p[1].x, l.p[1].y)) {
					toggle(l, i, SelectMode.LINE);
					return;
				}
			}
		}
		if (mode == SelectMode.POLYGON || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.polygons.size(); i++) {
				Panal poly = scene.polygons.get(i);
				Polygon awtPoly = new Polygon();
				for (Joint p : poly.points)
					awtPoly.addPoint((int) p.x, (int) p.y);
				if (awtPoly.contains(x, y)) {
					toggle(poly, i, SelectMode.POLYGON);
					return;
				}
			}
		}
		if (mode == SelectMode.OFF) {
		}
	}

	void toggle(Object obj, int index, SelectMode type) {
		if (selectedItems.stream().anyMatch(i -> i.item == obj)) {
			selectedItems.removeIf(i -> i.item == obj);
			setSelected(obj, false);
		} else if (selectedItems.size() < maxSelection) {
			selectedItems.add(new SelectedItem(index, obj, type));
			setSelected(obj, true);
			System.out.println("Selected " + type + " at index " + index);
		}
	}

	void setSelected(Object obj, boolean selected) {
		if (obj instanceof Joint)
			((Joint) obj).selected = selected;
		if (obj instanceof Line)
			((Line) obj).selected = selected;
		if (obj instanceof Panal)
			((Panal) obj).selected = selected;
	}

	boolean isNearLine(int x, int y, double x2, double y2, double x3, double y3) {
		double a = x - x2;
		double b = y - y2;
		double c = x3 - x2;
		double d = y3 - y2;
		double dot = a * c + b * d;
		double len_sq = c * c + d * d;
		double param = len_sq == 0 ? -1 : dot / len_sq;
		double xx, yy;
		if (param < 0) {
			xx = x2;
			yy = y2;
		} else if (param > 1) {
			xx = x3;
			yy = y3;
		} else {
			xx = x2 + param * c;
			yy = y2 + param * d;
		}
		double dx = x - xx;
		double dy = y - yy;
		return Math.sqrt(dx * dx + dy * dy) <= 5;
	}

	@SuppressWarnings("static-access")
	void dragSelect(Rectangle box, boolean strict) {
		if (!additive)
			clearSelection();
		if (mode == SelectMode.POINT || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.points.size(); i++) {
				Joint p = scene.points.get(i);
				if (box.contains(p.x, p.y))
					toggle(p, i, SelectMode.POINT);
			}
		}
		if (mode == SelectMode.LINE || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.lines.size(); i++) {
				Line l = scene.lines.get(i);
				if ((strict && l.isWithin(box)) || (!strict && l.touches(box)))
					toggle(l, i, SelectMode.LINE);
			}
		}
		if (mode == SelectMode.POLYGON || mode == SelectMode.ALL) {
			for (int i = 0; i < scene.polygons.size(); i++) {
				Panal p = scene.polygons.get(i);
				if ((strict && p.isWithin(box)) || (!strict && p.touches(box)))
					toggle(p, i, SelectMode.POLYGON);
			}
		}
	}

	public static void updateMode(int MODE) {
		if (MODE == 0) {
			setMode(SelectMode.OFF, Integer.MAX_VALUE);
		} else if (MODE == 1) {
			setMode(SelectMode.ALL, Integer.MAX_VALUE);
		} else if (MODE == 2) {
			setMode(SelectMode.POINT, Integer.MAX_VALUE);
		} else if (MODE == 3) {
			setMode(SelectMode.LINE, Integer.MAX_VALUE);
		} else if (MODE == 4) {
			setMode(SelectMode.POLYGON, Integer.MAX_VALUE);
		}
	}
}
