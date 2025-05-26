package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

import drawing.SwitchTool;

public class MainWindow extends JFrame {

	public static JPanel DrawingPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6252203059439893565L;

	public static JToggleButton memberToolBarButtons[] = new JToggleButton[2];
	public static JComboBox<String> SelectDropDown;

	/**
	 * Creates the window to add a new member
	 */
	public MainWindow() {

		setTitle("Structural Solver");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel ButtonBar = createUpperButtonBar();
		ButtonBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, ButtonBar.getPreferredSize().height));
		add(ButtonBar);

		JTabbedPane ToolBarTabs = new JTabbedPane(); // for changing content areas

		// Member Tab
		JPanel memberToolBarPanel = memberToolBar();
		ToolBarTabs.addTab("Member", memberToolBarPanel);

		add(ToolBarTabs);

		DrawingPanel = new DrawingPanel();
		DrawingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		add(DrawingPanel);
		// SwingUtilities.invokeLater(DrawingPanel::new);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setVisible(true);
	}

	/**
	 * creates the button bar at the top of the window
	 * 
	 * @return JPanel containing the button bar
	 */
	private JPanel createUpperButtonBar() {

		JPanel toolBarPanel = new JPanel();
		toolBarPanel.setLayout(new FlowLayout(10)); // Horizontal BoxLayout
		toolBarPanel.setBackground(Color.GRAY);

		// Creating buttons
		JButton OpenB = new JButton("Open");
		OpenB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Primary Button 1 clicked"));

		JButton SaveB = new JButton("Save");
		SaveB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Primary Button 2 clicked"));

		JButton SaveAsB = new JButton("Save As");
		SaveAsB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Primary Button 3 clicked"));

		// Create file drop down
		JPopupMenu FileMenu = new JPopupMenu();

		JMenuItem OpenFileB = new JMenuItem("Open");
		FileMenu.add(OpenFileB);
		OpenFileB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open File clicked"));

		JMenuItem SaveFileB = new JMenuItem("Save");
		FileMenu.add(SaveFileB);
		SaveFileB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Save File clicked"));

		JMenuItem SaveAsFileB = new JMenuItem("Save As");
		FileMenu.add(SaveAsFileB);
		SaveAsFileB.addActionListener(e -> JOptionPane.showMessageDialog(this, "Save As File clicked"));

		final JButton FileMenuB = new JButton();
		FileMenuB.setText("File");
		FileMenuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				FileMenu.show(FileMenuB, FileMenuB.getBounds().x,
						FileMenuB.getBounds().y + FileMenuB.getBounds().height);
			}
		});

		toolBarPanel.add(FileMenuB);
		toolBarPanel.add(OpenB);
		toolBarPanel.add(SaveB);
		toolBarPanel.add(SaveAsB);
		return toolBarPanel;
	}

	/**
	 * The JPanel desplayed for the Member Tool Bar tab
	 * 
	 * @return JPanel for the Member Tool Bar tab
	 */
	private JPanel memberToolBar() {

		JPanel memberToolBar = new JPanel();

		memberToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		String[] SelectList = { "OFF", "ALL", "JOINT", "MEMBER", "PANAL" };
		SelectDropDown = new JComboBox<>(SelectList);
		memberToolBar.add(SelectDropDown);

		SelectDropDown.addActionListener(e -> {
			String MODE = SelectDropDown.getSelectedItem().toString();
			if (MODE.equals("OFF")) {

			} else if (MODE.equals("ALL")) {
				SwitchTool.switchTool(0, 1);
			} else if (MODE.equals("JOINT")) {
				SwitchTool.switchTool(0, 2);
			} else if (MODE.equals("MEMBER")) {
				SwitchTool.switchTool(0, 3);
			} else if (MODE.equals("PANAL")) {
				SwitchTool.switchTool(0, 4);
			}

		});

		// Create new CrossSection
//		JPanel AddNewCrossSectionPanal = new JPanel();
//		AddNewCrossSectionPanal.setLayout(new BoxLayout(AddNewCrossSectionPanal, BoxLayout.Y_AXIS));
//		JButton AddNewCrossSectionButton = new JButton("New Cross-Section");
//		AddNewCrossSectionButton.addActionListener(e -> CreateNewMemberCrossSection());
//		AddNewCrossSectionPanal.add(AddNewCrossSectionButton);
//		memberToolBar.add(AddNewCrossSectionPanal);

		// Draw Member
		JPanel DrawMember = new JPanel();
		DrawMember.setLayout(new BoxLayout(DrawMember, BoxLayout.Y_AXIS));
		memberToolBarButtons[1] = new JToggleButton("Line");
		memberToolBarButtons[1].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("CLICK");
					SwitchTool.switchTool(1, 0);
				} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
					System.out.println("UNCLICK");
					SwitchTool.switchTool(0, 1);
				}
			}
		});
		
		DrawMember.add(memberToolBarButtons[1]);
		memberToolBar.add(DrawMember);

		memberToolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		return memberToolBar;
	}

	private void CreateNewMemberCrossSection() {
		System.out.print("CreateNewMemeber");
		new NewMember();
	}

	/**
	 * Repaints the drawing panel
	 * 
	 * @param newPanel what the panel is not to look like
	 */
	public void UpadateDrawingPanel(JPanel newPanel) {
		remove(DrawingPanel);
		DrawingPanel = newPanel;
		DrawingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		add(DrawingPanel);
		validate();
		repaint();

	}

}
