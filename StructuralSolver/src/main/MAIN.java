package main;

import UI.MainWindow;
import drawing.SwitchTool;

public class MAIN {

	public static MainWindow masterWindow;

	public static void main(String[] args) {

		masterWindow = new MainWindow();
		SwitchTool.switchTool(0, 0);
	}

}
