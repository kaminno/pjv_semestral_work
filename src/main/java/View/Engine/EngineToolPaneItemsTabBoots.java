package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    public EngineToolPaneItemsTabBoots() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
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

    public void selectBoots() {
	iconBoots.setOpaque(true);
	iconBoots.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfArmor.setEditable(true);
	tfDurability.setEditable(true);
	selected = true;
    }

    public void unselectBoots() {
	iconBoots.setOpaque(false);
	iconBoots.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfArmor.setEditable(false);
	tfDurability.setEditable(false);
	selected = false;
    }

    public JLabel getIconBoots() {
	return iconBoots;
    }

    public JLabel getLblArmor() {
	return lblArmor;
    }

    public JLabel getLblDurability() {
	return lblDurability;
    }

    public JTextField getTfArmor() {
	return tfArmor;
    }

    public JTextField getTfDurability() {
	return tfDurability;
    }

    public JPanel getPanelRight() {
	return panelRight;
    }

    public boolean isSelected() {
	return selected;
    }

    public GridBagLayout getMyLayout() {
	return myLayout;
    }

    public GridBagConstraints getMyConstraints() {
	return myConstraints;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
