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
 * players starting weapons panel
 * @author honzuna
 */
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

    /**
     *
     */
    public EngineToolPanePlayerTabWeapons() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
	itemBackground = new Color(255, 231, 150);
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	// layout
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

    /**
     * graphics design on main weapon selection
     */
    public void selectMainWeapon() {
	iconMainWeapon.setOpaque(true);
	iconMainWeapon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfMainWeaponDamage.setEditable(true);
	tfMainWeaponDurability.setEditable(true);
	mainSelected = true;
    }

    /**
     * graphics design on main weapon unselect
     */
    public void unselectMainWeapon() {
	iconMainWeapon.setOpaque(false);
	iconMainWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfMainWeaponDamage.setEditable(false);
	tfMainWeaponDurability.setEditable(false);
	mainSelected = false;
    }

    /**
     * graphics design on second weapon selection
     */
    public void selectSecondWeapon() {
	iconSecondWeapon.setOpaque(true);
	iconSecondWeapon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	tfSecondWeaponDamage.setEditable(true);
	tfSecondWeaponDurability.setEditable(true);
	secondSelected = true;
    }

    /**
     * graphics design on second weapon unselect
     */
    public void unselectSecondWeapon() {
	iconSecondWeapon.setOpaque(false);
	iconSecondWeapon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
	tfSecondWeaponDamage.setEditable(false);
	tfSecondWeaponDurability.setEditable(false);
	secondSelected = false;
    }

    /**
     *
     * @return
     */
    public JLabel getIconMainWeapon() {
	return iconMainWeapon;
    }

    /**
     *
     * @return
     */
    public JLabel getIconSecondWeapon() {
	return iconSecondWeapon;
    }

    /**
     *
     * @return
     */
    public JLabel getLblMainWeaponDamage() {
	return lblMainWeaponDamage;
    }

    /**
     *
     * @return
     */
    public JLabel getLblMainWeaponDurability() {
	return lblMainWeaponDurability;
    }

    /**
     *
     * @return
     */
    public JLabel getLblSecondWeaponDamage() {
	return lblSecondWeaponDamage;
    }

    /**
     *
     * @return
     */
    public JLabel getLblSecondWeaponDurability() {
	return lblSecondWeaponDurability;
    }

    /**
     *
     * @return
     */
    public JTextField getTfMainWeaponDamage() {
	return tfMainWeaponDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getTfMainWeaponDurability() {
	return tfMainWeaponDurability;
    }

    /**
     *
     * @return
     */
    public JTextField getTfSecondWeaponDamage() {
	return tfSecondWeaponDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getTfSecondWeaponDurability() {
	return tfSecondWeaponDurability;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelLeft() {
	return panelLeft;
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
    public JPanel getPanelMainRight() {
	return panelMainRight;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelSecondRight() {
	return panelSecondRight;
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
    public boolean isMainSelected() {
	return mainSelected;
    }

    /**
     *
     * @return
     */
    public boolean isSecondSelected() {
	return secondSelected;
    }

    /**
     *
     * @param mainSelected
     */
    public void setMainSelected(boolean mainSelected) {
	this.mainSelected = mainSelected;
    }

    /**
     *
     * @param secondSelected
     */
    public void setSecondSelected(boolean secondSelected) {
	this.secondSelected = secondSelected;
    }
}
