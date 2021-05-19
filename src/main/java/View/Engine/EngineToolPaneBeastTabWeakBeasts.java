package View.Engine;

import View.GameIcons;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * normal beasts panel of tabbed pane
 * @author honzuna
 */
public class EngineToolPaneBeastTabWeakBeasts extends JPanel {

    private JLabel batIcon;
    private JLabel batLblCount;
    private JLabel batLblHealth;
    private JLabel batLblArmor;
    private JLabel batLblDamage;
    private JTextField batTfHealth;
    private JTextField batTfArmor;
    private JTextField batTfDamage;
    private JTextField batTfCount;
    private JPanel panelBat;
    private JLabel skeletonIcon;
    private JLabel skeletonLblCount;
    private JLabel skeletonLblHealth;
    private JLabel skeletonLblArmor;
    private JLabel skeletonLblDamage;
    private JTextField skeletonTfHealth;
    private JTextField skeletonTfArmor;
    private JTextField skeletonTfDamage;
    private JTextField skeletonTfCount;
    private JPanel panelSkeleton;
    private GridBagLayout myLayout;
    private GridBagConstraints myConstraints;

    /**
     *
     */
    public EngineToolPaneBeastTabWeakBeasts() {
	myLayout = new GridBagLayout();
	myConstraints = new GridBagConstraints();
	myConstraints.fill = GridBagConstraints.HORIZONTAL;
	init();
    }

    private void init() {
	initBat();
	add(new JSeparator(SwingConstants.VERTICAL));
	initSkeleton();
    }

    private void initBat() {
	// create bat layout design
	panelBat = new JPanel();
	batIcon = new JLabel(GameIcons.BEAST_BAT_1.getIcon());
	batIcon.setToolTipText("Bat");
	JPanel panelRight = new JPanel();

	JPanel jp1 = new JPanel();
	jp1.setLayout(myLayout);
	batLblHealth = new JLabel(" Health: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 1;
	jp1.add(batLblHealth, myConstraints);

	batTfHealth = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 2;
	batTfHealth.setText("20");
	jp1.add(batTfHealth, myConstraints);

	batLblArmor = new JLabel(" Armor: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 1;
	jp1.add(batLblArmor, myConstraints);

	batTfArmor = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 2;
	batTfArmor.setText("0");
	jp1.add(batTfArmor, myConstraints);

	JPanel jp2 = new JPanel();
	jp2.setLayout(myLayout);
	batLblDamage = new JLabel(" Damage: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 1;
	jp2.add(batLblDamage, myConstraints);

	batTfDamage = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 2;
	batTfDamage.setText("5");
	jp2.add(batTfDamage, myConstraints);

	batLblCount = new JLabel(" Count: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 1;
	jp2.add(batLblCount, myConstraints);

	batTfCount = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 2;
	batTfCount.setText("5");
	jp2.add(batTfCount, myConstraints);

	// add components to panel
	panelRight.add(jp1);
	panelRight.add(jp2);
	panelBat.add(batIcon);
	panelBat.add(panelRight);
	add(panelBat);
    }

    private void initSkeleton() {
	// skeleton layout design
	panelSkeleton = new JPanel();
	skeletonIcon = new JLabel(GameIcons.BEAST_SKELETON.getIcon());
	skeletonIcon.setToolTipText("Skeleton");
	JPanel panelRight = new JPanel();

	JPanel jp1 = new JPanel();
	jp1.setLayout(myLayout);
	skeletonLblHealth = new JLabel(" Health: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 1;
	jp1.add(skeletonLblHealth, myConstraints);

	skeletonTfHealth = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 2;
	skeletonTfHealth.setText("35");
	jp1.add(skeletonTfHealth, myConstraints);

	skeletonLblArmor = new JLabel(" Armor: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 1;
	jp1.add(skeletonLblArmor, myConstraints);

	skeletonTfArmor = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 2;
	skeletonTfArmor.setText("3");
	jp1.add(skeletonTfArmor, myConstraints);

	JPanel jp2 = new JPanel();
	jp2.setLayout(myLayout);
	skeletonLblDamage = new JLabel(" Damage: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 1;
	jp2.add(skeletonLblDamage, myConstraints);

	skeletonTfDamage = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 0;
	myConstraints.gridwidth = 2;
	skeletonTfDamage.setText("6");
	jp2.add(skeletonTfDamage, myConstraints);

	skeletonLblCount = new JLabel(" Count: ");
	myConstraints.gridx = 0;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 1;
	jp2.add(skeletonLblCount, myConstraints);

	skeletonTfCount = new JTextField(5);
	myConstraints.gridx = 1;
	myConstraints.gridy = 1;
	myConstraints.gridwidth = 2;
	skeletonTfCount.setText("2");
	jp2.add(skeletonTfCount, myConstraints);

	// add components to panel
	panelRight.add(jp1);
	panelRight.add(jp2);
	panelSkeleton.add(skeletonIcon);
	panelSkeleton.add(panelRight);
	add(panelSkeleton);
    }

    /**
     *
     * @return
     */
    public JLabel getBatIcon() {
	return batIcon;
    }

    /**
     *
     * @return
     */
    public JLabel getBatLblCount() {
	return batLblCount;
    }

    /**
     *
     * @return
     */
    public JLabel getBatLblHealth() {
	return batLblHealth;
    }

    /**
     *
     * @return
     */
    public JLabel getBatLblArmor() {
	return batLblArmor;
    }

    /**
     *
     * @return
     */
    public JLabel getBatLblDamage() {
	return batLblDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getBatTfHealth() {
	return batTfHealth;
    }

    /**
     *
     * @return
     */
    public JTextField getBatTfArmor() {
	return batTfArmor;
    }

    /**
     *
     * @return
     */
    public JTextField getBatTfDamage() {
	return batTfDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getBatTfCount() {
	return batTfCount;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelBat() {
	return panelBat;
    }

    /**
     *
     * @return
     */
    public JLabel getSkeletonIcon() {
	return skeletonIcon;
    }

    /**
     *
     * @return
     */
    public JLabel getSkeletonLblCount() {
	return skeletonLblCount;
    }

    /**
     *
     * @return
     */
    public JLabel getSkeletonLblHealth() {
	return skeletonLblHealth;
    }

    /**
     *
     * @return
     */
    public JLabel getSkeletonLblArmor() {
	return skeletonLblArmor;
    }

    /**
     *
     * @return
     */
    public JLabel getSkeletonLblDamage() {
	return skeletonLblDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getSkeletonTfHealth() {
	return skeletonTfHealth;
    }

    /**
     *
     * @return
     */
    public JTextField getSkeletonTfArmor() {
	return skeletonTfArmor;
    }

    /**
     *
     * @return
     */
    public JTextField getSkeletonTfDamage() {
	return skeletonTfDamage;
    }

    /**
     *
     * @return
     */
    public JTextField getSkeletonTfCount() {
	return skeletonTfCount;
    }

    /**
     *
     * @return
     */
    public JPanel getPanelSkeleton() {
	return panelSkeleton;
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
}
