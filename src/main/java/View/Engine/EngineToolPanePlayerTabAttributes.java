package View.Engine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EngineToolPanePlayerTabAttributes extends JPanel{
    private JLabel lblBaseHealth;
    private JLabel lblBaseArmor;
    private JLabel lblBaseAttackDamage;
    private JLabel lblBaseSpeed;
    private JTextField tfBaseHealth;
    private JTextField tfBaseArmor;
    private JTextField tfBaseAttackDamage;
    private JTextField tfBaseSpeed;
    private JPanel panelLeft;
    private JPanel panelRight;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;

    public EngineToolPanePlayerTabAttributes() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }
    
    private void init(){
	panelLeft = new JPanel();
	panelLeft.setLayout(myLayout);
	panelRight = new JPanel();
	panelRight.setLayout(myLayout);
	
	lblBaseHealth = new JLabel("Health: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        panelLeft.add(lblBaseHealth, myConstraints);
 
        tfBaseHealth = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        panelLeft.add(tfBaseHealth, myConstraints);
 
        lblBaseArmor = new JLabel("Armor: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        panelLeft.add(lblBaseArmor, myConstraints);
 
        tfBaseArmor = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
        panelLeft.add(tfBaseArmor, myConstraints);
	
	lblBaseAttackDamage = new JLabel(" Damage: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        panelRight.add(lblBaseAttackDamage, myConstraints);
 
        tfBaseAttackDamage = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
        panelRight.add(tfBaseAttackDamage, myConstraints);
	
	lblBaseSpeed = new JLabel(" Speed: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        panelRight.add(lblBaseSpeed, myConstraints);
 
        tfBaseSpeed = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
        panelRight.add(tfBaseSpeed, myConstraints);
	
	tfBaseHealth.setText("50");
	tfBaseArmor.setText("2");
	tfBaseAttackDamage.setText("7");
	tfBaseSpeed.setText("2");
	
	add(panelLeft);
	add(panelRight);
    }

    public JTextField getTfBaseHealth() {
	return tfBaseHealth;
    }

    public JTextField getTfBaseArmor() {
	return tfBaseArmor;
    }

    public JTextField getTfBaseAttackDamage() {
	return tfBaseAttackDamage;
    }

    public JTextField getTfBaseSpeed() {
	return tfBaseSpeed;
    }
    
}
