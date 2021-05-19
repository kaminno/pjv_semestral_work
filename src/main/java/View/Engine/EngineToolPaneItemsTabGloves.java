package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * items gloves item
 * @author honzuna
 */
public class EngineToolPaneItemsTabGloves extends JPanel {

    private JLabel iconGloves;
    private JLabel lblArmor;
    private JLabel lblDurability;
    private JTextField tfArmor;
    private JTextField tfDurability;
    private JPanel panelRight;
    private boolean selected;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;

    /**
     *
     */
    public EngineToolPaneItemsTabGloves() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	// layout design
	iconGloves = new JLabel(GameIcons.ARMOR_GLOVES.getIcon());
	iconGloves.setToolTipText("Gloves");
	iconGloves.setBackground(new Color(255, 231, 150));
	iconGloves.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	panelRight = new JPanel();
	panelRight.setLayout(myLayout);

	lblArmor = new JLabel(" Armor: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 1;
	panelRight.add(lblArmor, myConstraints);

	tfArmor = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 2;
	panelRight.add(tfArmor, myConstraints);
	tfArmor.setEditable(false);

	lblDurability = new JLabel(" Durability: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 1;
	panelRight.add(lblDurability, myConstraints);

	tfDurability = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 2;
	panelRight.add(tfDurability, myConstraints);
	tfDurability.setEditable(false);

	add(iconGloves);
	add(panelRight);
    }

    /**
     * graphics select visualisation
     */
    public void selectGloves() {
	iconGloves.setOpaque(true);
	iconGloves.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfArmor.setEditable(true);
	tfDurability.setEditable(true);
	selected = true;
    }

    /**
     * graphics unselect visualisation
     */
    public void unselectGloves() {
	iconGloves.setOpaque(false);
	iconGloves.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfArmor.setEditable(false);
	tfDurability.setEditable(false);
	selected = false;
    }

    /**
     *
     * @return
     */
    public JLabel getIconGloves() {
	return iconGloves;
    }

    /**
     *
     * @return
     */
    public JLabel getLblArmor() {
	return lblArmor;
    }

    /**
     *
     * @return
     */
    public JLabel getLblDurability() {
	return lblDurability;
    }

    /**
     *
     * @return
     */
    public JTextField getTfArmor() {
	return tfArmor;
    }

    /**
     *
     * @return
     */
    public JTextField getTfDurability() {
	return tfDurability;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelRight() {
	return panelRight;
    }

    /**
     *
     * @return
     */
    public boolean isSelected() {
	return selected;
    }

    /**
     *
     * @return
     */
    public GridBagLayout getMyLayout() {
	return myLayout;
    }

    /**
     *
     * @return
     */
    public GridBagConstraints getMyConstraints() {
	return myConstraints;
    }

    /**
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
