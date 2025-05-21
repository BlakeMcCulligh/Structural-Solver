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

import crossSections.AngleSection;
import crossSections.CSection;
import crossSections.CircleSection;
import crossSections.HollowCircleSection;
import crossSections.HollowRectSection;
import crossSections.ISection;
import crossSections.RectSection;
import crossSections.SquareSection;
import crossSections.TSection;
import main.UnitConvertion;

/**
 * JFrame that alows the user to create a new member through a list of diferent
 * kinds, and setting the inforormation about it
 * 
 * @author blake M.
 * 
 * @version 1.0 
 * May 20, 2025 
 * -Solid Square, Rect, and Circle; Hollow Rect, and Circle; Angle, I, C, and  t Cross-sections added
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

		// Solid Rectangle Tab
		JPanel createRectSectionPanel = createRectSection();
		CrossSectionTabs.addTab("Solid Rectangle", createRectSectionPanel);

		// Solid Circle Tab
		JPanel createCirlceSectionPanel = createCircleSection();
		CrossSectionTabs.addTab("Solid Circle", createCirlceSectionPanel);

		// Hollow Rectangle Tab
		JPanel createHollowRectSectionPanel = createHollowRectSection();
		CrossSectionTabs.addTab("Hollow Rectangle", createHollowRectSectionPanel);

		// Hollow Circle Tab
		JPanel createHollowCircleSectionPanel = createHollowCircleSection();
		CrossSectionTabs.addTab("Hollow Cirlce", createHollowCircleSectionPanel);

		// Angle Tab
		JPanel createAngleSectionPanel = createAngleSection();
		CrossSectionTabs.addTab("Angle", createAngleSectionPanel);

		// I-Section Tab
		JPanel createISectionPanel = createISection();
		CrossSectionTabs.addTab("I-Section", createISectionPanel);

		// C-Section Tab
		JPanel createCSectionPanel = createCSection();
		CrossSectionTabs.addTab("C-Section", createCSectionPanel);

		// T-Section Tab
		JPanel createTSectionPanel = createTSection();
		CrossSectionTabs.addTab("T-Secton", createTSectionPanel);

		add(CrossSectionTabs);

		this.setSize(825, 500);
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

		dispose();
		
		new SquareSection(d, material, L, k);

	}

	/**
	 * The JPanel desplayed for the Angle tab
	 * 
	 * @return JPanel for the Angle tab
	 */
	private JPanel createAngleSection() {

		JPanel AngleSectionPanel = new JPanel();

		AngleSectionPanel.setLayout(new BoxLayout(AngleSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		AngleSectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		AngleSectionPanel.add(WidthPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		AngleSectionPanel.add(ThicknessPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		AngleSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		AngleSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		AngleSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> AngleSectionCreated(diameterPanal, WidthPanal, ThicknessPanal, LengthPanal,
				EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		AngleSectionPanel.add(CreatePanal);

		return AngleSectionPanel;
	}

	/**
	 * When a angle section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param dPanel JPanel storing thickness
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void AngleSectionCreated(JPanel dPanel, JPanel bPanel, JPanel tPanel, JPanel LPanel, JPanel KPanel,
			JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new AngleSection(d, b, t, material, L, k);

	}

	/**
	 * The JPanel desplayed for the Solid circle tab
	 * 
	 * @return JPanel for the Solid circle tab
	 */
	private JPanel createCircleSection() {

		JPanel CircleSectionPanel = new JPanel();

		CircleSectionPanel.setLayout(new BoxLayout(CircleSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		CircleSectionPanel.add(diameterPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		CircleSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		CircleSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		CircleSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(
				e -> CircleSectionCreated(diameterPanal, LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		CircleSectionPanel.add(CreatePanal);

		return CircleSectionPanel;
	}

	/**
	 * When a circle section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void CircleSectionCreated(JPanel dPanel, JPanel LPanel, JPanel KPanel, JPanel MPanel) {

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

		dispose();
		
		new CircleSection(d, material, L, k);

	}

	/**
	 * The JPanel desplayed for the C-section tab
	 * 
	 * @return JPanel for the C-section tab
	 */
	private JPanel createCSection() {

		JPanel CSectionPanel = new JPanel();

		CSectionPanel.setLayout(new BoxLayout(CSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		CSectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		CSectionPanel.add(WidthPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		CSectionPanel.add(ThicknessPanal);

		JPanel wPanal = DoubleTextBox("W:", true);
		CSectionPanel.add(wPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		CSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		CSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		CSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> CSectionCreated(diameterPanal, WidthPanal, ThicknessPanal, wPanal,
				LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		CSectionPanel.add(CreatePanal);

		return CSectionPanel;
	}

	/**
	 * When a C section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param dPanel JPanel storing thickness
	 * @param dPanel JPanel storing w
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void CSectionCreated(JPanel dPanel, JPanel bPanel, JPanel tPanel, JPanel wPanel, JPanel LPanel,
			JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField wValue = (JTextField) wPanel.getComponent(1);
		JComboBox<String> wUnit = (JComboBox<String>) wPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double w = UnitConvertion.linearTomm(Double.parseDouble(wValue.getText()), wUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new CSection(d, b, t, w, material, L, k);

	}

	/**
	 * The JPanel desplayed for the Hollow Circle tab
	 * 
	 * @return JPanel for the Hollow Circle tab
	 */
	private JPanel createHollowCircleSection() {

		JPanel HollowCircleSectionPanel = new JPanel();

		HollowCircleSectionPanel.setLayout(new BoxLayout(HollowCircleSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		HollowCircleSectionPanel.add(diameterPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		HollowCircleSectionPanel.add(ThicknessPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		HollowCircleSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		HollowCircleSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		HollowCircleSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> HollowCircleSectionCreated(diameterPanal, ThicknessPanal, LengthPanal,
				EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		HollowCircleSectionPanel.add(CreatePanal);

		return HollowCircleSectionPanel;
	}

	/**
	 * When a Hollow Circle section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing thickness
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void HollowCircleSectionCreated(JPanel dPanel, JPanel tPanel, JPanel LPanel, JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new HollowCircleSection(d, t, material, L, k);

	}

	/**
	 * The JPanel desplayed for the Hollow-Rectangle tab
	 * 
	 * @return JPanel for the Hollow Rectangle tab
	 */
	private JPanel createHollowRectSection() {

		JPanel CSectionPanel = new JPanel();

		CSectionPanel.setLayout(new BoxLayout(CSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		CSectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		CSectionPanel.add(WidthPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		CSectionPanel.add(ThicknessPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		CSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		CSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		CSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> HollowRectSectionCreated(diameterPanal, WidthPanal, ThicknessPanal,
				LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		CSectionPanel.add(CreatePanal);

		return CSectionPanel;
	}

	/**
	 * When a Hollow Rect section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param dPanel JPanel storing thickness
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void HollowRectSectionCreated(JPanel dPanel, JPanel bPanel, JPanel tPanel, JPanel LPanel, JPanel KPanel,
			JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new HollowRectSection(d, b, d - 2 * t, b - 2 * t, material, L, k);

	}

	/**
	 * The JPanel desplayed for the I-section tab
	 * 
	 * @return JPanel for the I-section tab
	 */
	private JPanel createISection() {

		JPanel ISectionPanel = new JPanel();

		ISectionPanel.setLayout(new BoxLayout(ISectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		ISectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		ISectionPanel.add(WidthPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		ISectionPanel.add(ThicknessPanal);

		JPanel wPanal = DoubleTextBox("W:", true);
		ISectionPanel.add(wPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		ISectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		ISectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		ISectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> ISectionCreated(diameterPanal, WidthPanal, ThicknessPanal, wPanal,
				LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		ISectionPanel.add(CreatePanal);

		return ISectionPanel;
	}

	/**
	 * When a I section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param dPanel JPanel storing thickness
	 * @param dPanel JPanel storing w
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void ISectionCreated(JPanel dPanel, JPanel bPanel, JPanel tPanel, JPanel wPanel, JPanel LPanel,
			JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField wValue = (JTextField) wPanel.getComponent(1);
		JComboBox<String> wUnit = (JComboBox<String>) wPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double w = UnitConvertion.linearTomm(Double.parseDouble(wValue.getText()), wUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new ISection(d, b, t, w, material, L, k);

	}

	/**
	 * The JPanel desplayed for the Rectangle tab
	 * 
	 * @return JPanel for the Rectangle tab
	 */
	private JPanel createRectSection() {

		JPanel RectSectionPanel = new JPanel();

		RectSectionPanel.setLayout(new BoxLayout(RectSectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		RectSectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		RectSectionPanel.add(WidthPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		RectSectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		RectSectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		RectSectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(
				e -> RectSectionCreated(diameterPanal, WidthPanal, LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		RectSectionPanel.add(CreatePanal);

		return RectSectionPanel;
	}

	/**
	 * When a Rect section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void RectSectionCreated(JPanel dPanel, JPanel bPanel, JPanel LPanel, JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new RectSection(d, b, material, L, k);

	}

	/**
	 * The JPanel desplayed for the T-section tab
	 * 
	 * @return JPanel for the T-section tab
	 */
	private JPanel createTSection() {

		JPanel ISectionPanel = new JPanel();

		ISectionPanel.setLayout(new BoxLayout(ISectionPanel, BoxLayout.Y_AXIS));

		JPanel diameterPanal = DoubleTextBox("d:", true);
		ISectionPanel.add(diameterPanal);

		JPanel WidthPanal = DoubleTextBox("b:", true);
		ISectionPanel.add(WidthPanal);

		JPanel ThicknessPanal = DoubleTextBox("t:", true);
		ISectionPanel.add(ThicknessPanal);

		JPanel wPanal = DoubleTextBox("W:", true);
		ISectionPanel.add(wPanal);

		JPanel LengthPanal = DoubleTextBox("L:", true);
		ISectionPanel.add(LengthPanal);

		JPanel EffectiveLengthPanal = DoubleTextBox("k:", false);
		ISectionPanel.add(EffectiveLengthPanal);

		JPanel MaterialPanal = materialDropDown();
		ISectionPanel.add(MaterialPanal);

		JPanel CreatePanal = new JPanel();
		CreatePanal.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(e -> TSectionCreated(diameterPanal, WidthPanal, ThicknessPanal, wPanal,
				LengthPanal, EffectiveLengthPanal, MaterialPanal));
		CreatePanal.add(CreateButton);
		ISectionPanel.add(CreatePanal);

		return ISectionPanel;
	}

	/**
	 * When a T section is created
	 * 
	 * @param dPanel JPanel storing diameter
	 * @param dPanel JPanel storing width
	 * @param dPanel JPanel storing thickness
	 * @param dPanel JPanel storing w
	 * @param LPanel JPanel storing length
	 * @param KPanel JPanel storing effective length constant
	 * @param MPanel JPanel storing Material info
	 */
	@SuppressWarnings("unchecked")
	private void TSectionCreated(JPanel dPanel, JPanel bPanel, JPanel tPanel, JPanel wPanel, JPanel LPanel,
			JPanel KPanel, JPanel MPanel) {

		JTextField dValue = (JTextField) dPanel.getComponent(1);
		JComboBox<String> dUnit = (JComboBox<String>) dPanel.getComponent(2);

		JTextField bValue = (JTextField) bPanel.getComponent(1);
		JComboBox<String> bUnit = (JComboBox<String>) bPanel.getComponent(2);

		JTextField tValue = (JTextField) tPanel.getComponent(1);
		JComboBox<String> tUnit = (JComboBox<String>) tPanel.getComponent(2);

		JTextField wValue = (JTextField) wPanel.getComponent(1);
		JComboBox<String> wUnit = (JComboBox<String>) wPanel.getComponent(2);

		JTextField LValue = (JTextField) LPanel.getComponent(1);
		JComboBox<String> LUnit = (JComboBox<String>) LPanel.getComponent(2);

		JTextField KValue = (JTextField) KPanel.getComponent(1);

		JComboBox<String> MValue = (JComboBox<String>) MPanel.getComponent(0);

		double d = UnitConvertion.linearTomm(Double.parseDouble(dValue.getText()), dUnit.getSelectedItem().toString());
		double b = UnitConvertion.linearTomm(Double.parseDouble(bValue.getText()), bUnit.getSelectedItem().toString());
		double t = UnitConvertion.linearTomm(Double.parseDouble(tValue.getText()), tUnit.getSelectedItem().toString());
		double w = UnitConvertion.linearTomm(Double.parseDouble(wValue.getText()), wUnit.getSelectedItem().toString());
		double L = UnitConvertion.linearTomm(Double.parseDouble(LValue.getText()), LUnit.getSelectedItem().toString());
		double k = Double.parseDouble(KValue.getText());

		int material = materialDecoder(MValue);

		dispose();
		
		new TSection(d, b, t, w, material, L, k);

	}

}
