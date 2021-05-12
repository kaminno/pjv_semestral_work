package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EngineToolPanePlayerTabWeapons extends JPanel{
    private JLabel iconMainWeapon;
    private JLabel iconSecondWeapon;
    private JLabel lblMainWeaponDamage;
    private JLabel lblMainWeaponDurability;
    private JLabel lblSecondWeaponDamage;
    private JLabel lblSecondWeaponDurability;
    private JTextField tfMainWeaponDamage;
    private JTextField tfMainWeaponDurability;
    private JTextField tfSecondWeaponDamage;
    private JTextField tfSecondWeaponDurability;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelMainRight;
    private JPanel panelSecondRight;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;
    
    public EngineToolPanePlayerTabWeapons() {
	//setMinimumSize(new Dimension(350, 50));
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }
    
    private void init(){
	panelLeft = new JPanel();
	panelRight = new JPanel();
	panelMainRight = new JPanel();
	panelMainRight.setLayout(myLayout);
	panelSecondRight = new JPanel();
	panelSecondRight.setLayout(myLayout);
	
	iconMainWeapon = new JLabel(GameIcons.WEAPON_MAIN_SWORD.getIcon());
	iconMainWeapon.setToolTipText("Main weapon");
	iconMainWeapon.setBackground(Color.YELLOW);
	
	lblMainWeaponDamage = new JLabel("  Damage: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        panelMainRight.add(lblMainWeaponDamage, myConstraints);
 
        tfMainWeaponDamage = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        panelMainRight.add(tfMainWeaponDamage, myConstraints);
	tfMainWeaponDamage.setEditable(false);
	
	lblMainWeaponDurability = new JLabel("  Durability: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        panelMainRight.add(lblMainWeaponDurability, myConstraints);
 
        tfMainWeaponDurability = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
        panelMainRight.add(tfMainWeaponDurability, myConstraints);
	tfMainWeaponDurability.setEditable(false);
	
	panelLeft.add(iconMainWeapon);
	panelLeft.add(panelMainRight);
	
	iconSecondWeapon = new JLabel(GameIcons.WEAPON_SECOND_DAGGER.getIcon());
	iconSecondWeapon.setToolTipText("Second weapon");
	iconSecondWeapon.setBackground(Color.YELLOW);
	
	lblSecondWeaponDamage = new JLabel("  Damage: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        panelSecondRight.add(lblSecondWeaponDamage, myConstraints);
 
        tfSecondWeaponDamage = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        panelSecondRight.add(tfSecondWeaponDamage, myConstraints);
	tfSecondWeaponDamage.setEditable(false);
	
	lblSecondWeaponDurability = new JLabel("  Durability: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        panelSecondRight.add(lblSecondWeaponDurability, myConstraints);
 
        tfSecondWeaponDurability = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
        panelSecondRight.add(tfSecondWeaponDurability, myConstraints);
	tfSecondWeaponDurability.setEditable(false);
	
	panelRight.add(iconSecondWeapon);
	panelRight.add(panelSecondRight);
	
	add(panelLeft);
	add(panelRight);
    }

    public JLabel getIconMainWeapon() {
	return iconMainWeapon;
    }

    public JLabel getIconSecondWeapon() {
	return iconSecondWeapon;
    }

    public JLabel getLblMainWeaponDamage() {
	return lblMainWeaponDamage;
    }

    public JLabel getLblMainWeaponDurability() {
	return lblMainWeaponDurability;
    }

    public JLabel getLblSecondWeaponDamage() {
	return lblSecondWeaponDamage;
    }

    public JLabel getLblSecondWeaponDurability() {
	return lblSecondWeaponDurability;
    }

    public JTextField getTfMainWeaponDamage() {
	return tfMainWeaponDamage;
    }

    public JTextField getTfMainWeaponDurability() {
	return tfMainWeaponDurability;
    }

    public JTextField getTfSecondWeaponDamage() {
	return tfSecondWeaponDamage;
    }

    public JTextField getTfSecondWeaponDurability() {
	return tfSecondWeaponDurability;
    }

    public JPanel getPanelLeft() {
	return panelLeft;
    }

    public JPanel getPanelRight() {
	return panelRight;
    }

    public JPanel getPanelMainRight() {
	return panelMainRight;
    }

    public JPanel getPanelSecondRight() {
	return panelSecondRight;
    }

    public GridBagLayout getMyLayout() {
	return myLayout;
    }

    public GridBagConstraints getMyConstraints() {
	return myConstraints;
    }
}
