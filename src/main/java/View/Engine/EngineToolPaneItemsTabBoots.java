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
 * item boots panel
 * @author honzuna
 */
public class EngineToolPaneItemsTabBoots extends JPanel {

    private JLabel iconBoots;
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
    public EngineToolPaneItemsTabBoots() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	// create layout design
	iconBoots = new JLabel(GameIcons.ARMOR_BOOTS.getIcon());
	iconBoots.setToolTipText("Boots");
	iconBoots.setBackground(new Color(255, 231, 150));
	iconBoots.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
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

	add(iconBoots);
	add(panelRight);
    }

    /**
     * graphic visualisation of selecting item
     */
    public void selectBoots() {
	iconBoots.setOpaque(true);
	iconBoots.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfArmor.setEditable(true);
	tfDurability.setEditable(true);
	selected = true;
    }

    /**
     * set item unselect
     */
    public void unselectBoots() {
	iconBoots.setOpaque(false);
	iconBoots.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfArmor.setEditable(false);
	tfDurability.setEditable(false);
	selected = false;
    }

    /**
     *
     * @return
     */
    public JLabel getIconBoots() {
	return iconBoots;
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
