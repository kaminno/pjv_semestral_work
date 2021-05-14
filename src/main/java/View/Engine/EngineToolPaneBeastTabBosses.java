package View.Engine;

import View.GameIcons;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EngineToolPaneBeastTabBosses extends JPanel{
    private JLabel warPigIcon;
    private JLabel warPigLblCount;
    private JLabel warPigLblHealth;
    private JLabel warPigLblArmor;
    private JLabel warPigLblDamage;
    private JTextField warPigTfHealth;
    private JTextField warPigTfArmor;
    private JTextField warPigTfDamage;
    private JTextField warPigTfCount;
    private JPanel panelWarPig;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;

    public EngineToolPaneBeastTabBosses() {
	myLayout = new GridBagLayout();
	setLayout(myLayout);
	myConstraints = new GridBagConstraints();
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }
    
    private void init(){
	initWarPig();
    }
    
    private void initWarPig(){
	panelWarPig = new JPanel();
	warPigIcon = new JLabel(GameIcons.BEAST_WAR_PIG.getIcon());
	warPigIcon.setToolTipText("War Pig");
	JPanel panelRight = new JPanel();
	
	JPanel jp1 = new JPanel();
	jp1.setLayout(myLayout);
	warPigLblHealth = new JLabel(" Health: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        jp1.add(warPigLblHealth, myConstraints);
 
        warPigTfHealth = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
	warPigTfHealth.setText("75");
        jp1.add(warPigTfHealth, myConstraints);
 
        warPigLblArmor = new JLabel(" Armor: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        jp1.add(warPigLblArmor, myConstraints);
 
        warPigTfArmor = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
	warPigTfArmor.setText("6");
        jp1.add(warPigTfArmor, myConstraints);
	
	JPanel jp2 = new JPanel();
	jp2.setLayout(myLayout);
	warPigLblDamage = new JLabel(" Damage: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        jp2.add(warPigLblDamage, myConstraints);
 
        warPigTfDamage = new JTextField(5);
        myConstraints.gridx = 1;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 2;
	warPigTfDamage.setText("10");
        jp2.add(warPigTfDamage, myConstraints);
 
        warPigLblCount = new JLabel(" Count: ");
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 1;
        jp2.add(warPigLblCount, myConstraints);
 
        warPigTfCount = new JTextField(5);
	warPigTfCount.setEditable(false);
	warPigTfCount.setText("1");
        myConstraints.gridx = 1;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 2;
        jp2.add(warPigTfCount, myConstraints);

	panelRight.add(jp1);
	panelRight.add(jp2);
	panelWarPig.add(warPigIcon);
	panelWarPig.add(panelRight);
	add(panelWarPig);
    }

    public JLabel getWarPigIcon() {
	return warPigIcon;
    }

    public JLabel getWarPigLblCount() {
	return warPigLblCount;
    }

    public JLabel getWarPigLblHealth() {
	return warPigLblHealth;
    }

    public JLabel getWarPigLblArmor() {
	return warPigLblArmor;
    }

    public JLabel getWarPigLblDamage() {
	return warPigLblDamage;
    }

    public JTextField getWarPigTfHealth() {
	return warPigTfHealth;
    }

    public JTextField getWarPigTfArmor() {
	return warPigTfArmor;
    }

    public JTextField getWarPigTfDamage() {
	return warPigTfDamage;
    }

    public JTextField getWarPigTfCount() {
	return warPigTfCount;
    }

    public JPanel getPanelWarPig() {
	return panelWarPig;
    }

    public GridBagLayout getMyLayout() {
	return myLayout;
    }

    public GridBagConstraints getMyConstraints() {
	return myConstraints;
    }
    
}
