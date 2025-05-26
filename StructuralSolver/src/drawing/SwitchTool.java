package drawing;

import UI.MainWindow;
import UI.Select;

public class SwitchTool {

	public static Boolean activeButton[] = { true, false };

	public static void switchTool(int tool, int MODE) {

		for (int i = 0; i < activeButton.length; i++) {
			if (i == tool) {
				activeButton[i] = true;

			} else {

				activeButton[i] = false;

				if (i > 0) {
					if (MainWindow.memberToolBarButtons[i].isSelected()) {
						MainWindow.memberToolBarButtons[i].doClick();
					}
				}

			}
		}

		if (tool == 0) {
			MainWindow.SelectDropDown.setSelectedIndex(MODE);
			Select.updateMode(MODE);
		} else {
			MainWindow.SelectDropDown.setSelectedIndex(0);
			Select.updateMode(0);
		}

		if (tool == 1) {
			DrawLine.drawLine();
		} else {
			DrawLine.end();
		}

	}

}
