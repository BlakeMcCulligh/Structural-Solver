package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import crossSections.SquareSection;
import main.UnitConvertion;

/**
 * JFrame that alows the user to create a new member through a list of diferent
 * kinds, and setting the inforormation about it
 * 
 * @author blake M.
 * 
 * @version 1.0 
 * May 16, 2025
 * -Solid Square Cross-section added
 * 
 */
public class NewMember extends JFrame {

	private static final long serialVersionUID = 1421207651600599789L;

	/**
	 * List of Length Units to Select From
	 */
	public String[] LengthUnits = { "mm", "m", "in", "ft" };

	/**
	 * Creates the window to add a new member
	 */
	public NewMember() {

		setTitle("New Member");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel ButtonBar = createUpperButtonBar();
		ButtonBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, ButtonBar.getPreferredSize().height));
		add(ButtonBar);

		JTabbedPane CrossSectionTabs = new JTabbedPane(); // for changing content areas

		// Solid Square Tab
		JPanel createSquareSectionPanel = createSquareSection();
		CrossSectionTabs.addTab("Solid Square", createSquareSectionPanel);

		// JPanel drawingPanel2 = createDrawingPanel("Content for Tab 2");
		// drawingTabs.addTab("Tab 2", drawingPanel2);

		add(CrossSectionTabs);

		this.setSize(600, 500);
		setLocationRelativeTo(null);

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
	 * Creates a JPanel with a JTextField limited to doubles, along with a label and
	 * the option to select units
	 * 
	 * @param label string to label the text box with
	 * @param unit  is the unit dropdown to be added
	 * @return JPanel with a Jlabel, JTectField, and posibly a JComboBox
	 */
	private JPanel DoubleTextBox(String label, boolean unit) {

		JPanel DoublePanal = new JPanel();
		DoublePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JLabel Doublelabel = new JLabel();
		Doublelabel.setText(label);
		DoublePanal.add(Doublelabel);

		JTextField DoubleTextBox = new JTextField(8);
		DoublePanal.add(DoubleTextBox);

		// making the text box doubles only
		DoubleTextBox.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Runnable format = new Runnable() {
					@Override
					public void run() {
						String text = DoubleTextBox.getText();
						if (!text.matches("\\d*(\\.\\d*)?")) {
							DoubleTextBox.setText(text.substring(0, text.length() - 1));
						}
					}
				};
				SwingUtilities.invokeLater(format);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		if (unit) {
			JComboBox<String> DoubleUnits = new JComboBox<>(LengthUnits);
			DoublePanal.add(DoubleUnits);
		}

		DoublePanal.setMaximumSize(new Dimension(Integer.MAX_VALUE, DoublePanal.getPreferredSize().height));

		return DoublePanal;
	}

	/**
	 * Creates a drop down the material being used
	 * 
	 * @return JPanel containing a JComboBox
	 */
	private JPanel materialDropDown() {

		JPanel MaterialPanal = new JPanel();
		MaterialPanal.setLayout(new FlowLayout(FlowLayout.RIGHT));

		String[] MaterialList = { "Cold Form Steel", "Hot Form Steel" };

		JComboBox<String> MaterialDropDown = new JComboBox<>(MaterialList);
		MaterialPanal.setMaximumSize(new Dimension(Integer.MAX_VALUE, MaterialPanal.getPreferredSize().height));
		MaterialPanal.add(MaterialDropDown);

		return MaterialPanal;
	}

	/**
	 * Changes the slected JComboBox selected itum to the coresponding intager value
	 * 
	 * 1 = cold form steel, 2 = hot form steel
	 * 
	 * @param MValue the JComboBox being decoaded
	 * @return the coresponding intager value
	 */
	private int materialDecoder(JComboBox<String> MValue) {

		int material = 0;

		if (MValue.getSelectedItem().toString().equals("Cold Form Steel")) {
			material = 1;
		} else {
			material = 2;
		}

		return material;
	}

	/**
	 * The JPanel desplayed for the Solid Square tab
	 * 
	 * @return JPanel for the Solid Square tab
	 */
	private JPanel createSquareSection() {

		JPanel SquareSectionPanel = new JPanel();

		SquareSectionPanel.setLayout(new BoxLayout(SquareSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		SquareSectionPanel.add(diameterPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		SquareSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		SquareSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		SquareSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(
				e -> SquareSectionCreated(diameterPanal, LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		SquareSectionPanel.add(CreatePanal);

		return SquareSectionPanel;
	}

	/**
	 * When a square section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void SquareSectionCreated(JPanel dPanel, JPanel LPanel, JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		new SquareSection(d, material, L, k);

	}

}
