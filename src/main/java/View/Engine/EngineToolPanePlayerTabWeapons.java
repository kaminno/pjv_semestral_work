package View.Engine;

import View.GameIcons;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EngineToolPanePlayerTabWeapons extends JPanel {

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
    private boolean mainSelected = false;
    private boolean secondSelected = false;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelMainRight;
    private JPanel panelSecondRight;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;
    private Color itemBackground;

    public EngineToolPanePlayerTabWeapons() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	itemBackground = new Color(255, 231, 150);
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	panelLeft = new JPanel();
	panelRight = new JPanel();
	panelMainRight = new JPanel();
	panelMainRight.setLayout(myLayout);
	panelSecondRight = new JPanel();
	panelSecondRight.setLayout(myLayout);

	iconMainWeapon = new JLabel(GameIcons.WEAPON_MAIN_SWORD.getIcon());
	iconMainWeapon.setToolTipText("Main weapon");
	iconMainWeapon.setBackground(itemBackground);
	iconMainWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));

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
	iconSecondWeapon.setBackground(itemBackground);
	iconSecondWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));

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

    public void selectMainWeapon() {
	iconMainWeapon.setOpaque(true);
	iconMainWeapon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfMainWeaponDamage.setEditable(true);
	tfMainWeaponDurability.setEditable(true);
	mainSelected = true;
    }

    public void unselectMainWeapon() {
	iconMainWeapon.setOpaque(false);
	iconMainWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfMainWeaponDamage.setEditable(false);
	tfMainWeaponDurability.setEditable(false);
	mainSelected = false;
    }

    public void selectSecondWeapon() {
	iconSecondWeapon.setOpaque(true);
	iconSecondWeapon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfSecondWeaponDamage.setEditable(true);
	tfSecondWeaponDurability.setEditable(true);
	secondSelected = true;
    }

    public void unselectSecondWeapon() {
	iconSecondWeapon.setOpaque(false);
	iconSecondWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfSecondWeaponDamage.setEditable(false);
	tfSecondWeaponDurability.setEditable(false);
	secondSelected = false;
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

    public boolean isMainSelected() {
	return mainSelected;
    }

    public boolean isSecondSelected() {
	return secondSelected;
    }

    public void setMainSelected(boolean mainSelected) {
	this.mainSelected = mainSelected;
    }

    public void setSecondSelected(boolean secondSelected) {
	this.secondSelected = secondSelected;
    }
}
