package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EngineToolPaneItemsTabHelmet extends JPanel{
    private JLabel iconHelmet;
    private JLabel lblArmor;
    private JLabel lblDurability;
    private JTextField tfArmor;
    private JTextField tfDurability;
    private JPanel panelRight;
    private boolean selected;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;

    public EngineToolPaneItemsTabHelmet() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }
    
    private void init(){
	iconHelmet = new JLabel(GameIcons.ARMOR_HELMET.getIcon());
	iconHelmet.setToolTipText("Helmet");
	iconHelmet.setBackground(new Color(255, 231, 150));
	iconHelmet.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
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
	
	add(iconHelmet);
	add(panelRight);
    }
    
    public void selectHelmet(){
	iconHelmet.setOpaque(true);
	iconHelmet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfArmor.setEditable(true);
	tfDurability.setEditable(true);
	selected = true;
    }
    
    public void unselectHelmet(){
	iconHelmet.setOpaque(false);
	iconHelmet.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfArmor.setEditable(false);
	tfDurability.setEditable(false);
	selected = false;
    }

    public JLabel getIconHelmet() {
	return iconHelmet;
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

    public GridBagLayout getMyLayout() {
	return myLayout;
    }

    public GridBagConstraints getMyConstraints() {
	return myConstraints;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}