package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import drawing.Dimentions;
import drawing.DrawLine;

public class MainWindow extends JFrame {

	public static JPanel DrawingPanel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252203059439893565L;

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

		DrawingPanel = new JPanel();
		DrawingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		add(DrawingPanel);

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

		// Create new CrossSection
		JPanel AddNewCrossSectionPanal = new JPanel();
		AddNewCrossSectionPanal.setLayout(new BoxLayout(AddNewCrossSectionPanal, BoxLayout.Y_AXIS));
		JButton AddNewCrossSectionButton = new JButton("New Cross-Section");
		AddNewCrossSectionButton.addActionListener(e -> CreateNewMemberCrossSection());
		AddNewCrossSectionPanal.add(AddNewCrossSectionButton);
		memberToolBar.add(AddNewCrossSectionPanal);
		
		// Draw Member
		JPanel DrawMember = new JPanel();
		DrawMember.setLayout(new BoxLayout(DrawMember, BoxLayout.Y_AXIS));
		JButton Line = new JButton("Line");
		Line.addActionListener(e -> DrawLine.drawLine());
		DrawMember.add(Line);
		memberToolBar.add(DrawMember);
		
		// Dimention Member
				JPanel DimentionMember = new JPanel();
				DimentionMember.setLayout(new BoxLayout(DimentionMember, BoxLayout.Y_AXIS));
				JButton Dimention = new JButton("Dimention");
				Dimention.addActionListener(e -> Dimentions.dimention());
				DimentionMember.add(Dimention);
				memberToolBar.add(DimentionMember);

		memberToolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		return memberToolBar;
	}

	private void CreateNewMemberCrossSection() {
		System.out.print("CreateNewMemeber");
		new NewMember();
	}
	
	public void UpadateDrawingPanel(JPanel newPanel) {
		remove(DrawingPanel);
		DrawingPanel = newPanel;
		DrawingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		add(DrawingPanel);
		validate();
		repaint();

	}

}
