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
 * items chest item panel
 * @author honzuna
 */
public class EngineToolPaneItemsTabChest extends JPanel {

    private JLabel iconChest;
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
    public EngineToolPaneItemsTabChest() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	// create layout design
	iconChest = new JLabel(GameIcons.ARMOR_CHEST.getIcon());
	iconChest.setToolTipText("Chest");
	iconChest.setBackground(new Color(255, 231, 150));
	iconChest.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
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

	add(iconChest);
	add(panelRight);
    }

    /**
     * graphics visualisation of item selection
     */
    public void selectChest() {
	iconChest.setOpaque(true);
	iconChest.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfArmor.setEditable(true);
	tfDurability.setEditable(true);
	selected = true;
    }

    /**
     * graphics visualisation of item unselect
     */
    public void unselectChest() {
	iconChest.setOpaque(false);
	iconChest.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfArmor.setEditable(false);
	tfDurability.setEditable(false);
	selected = false;
    }

    /**
     *
     * @return
     */
    public JLabel getIconChest() {
	return iconChest;
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
     * @return
     */
    public boolean isSelected() {
	return selected;
    }

    /**
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
