package View.Game;

import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class EquipmentView extends JPanel{
    private JLabel title;
    private JLabel playerImage;
    private JLabel equipmentHelmet;
    private JLabel equipmentChest;
    private JLabel equipmentLegs;
    private JLabel equipmentGloves;
    private JLabel equipmentBoots;
    private JLabel equipmentMainHand;
    private JLabel equipmentSecondHand;
    private JLabel lblHealth;
    private JLabel lblArmor;
    private JLabel lblDamage;
    private JLabel lblSpeed;
    private JLabel playerHealth;
    private JLabel playerArmor;
    private JLabel playerDamage;
    private JLabel playerSpeed;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelBottom;
    private JPanel panelCenter;
    private Color itemColor;
    private Color borderColor;
    private Border itemBorder;
    private boolean shown;

    public EquipmentView() {
	setLayout(new BorderLayout());
	shown = false;
	setVisible(shown);
	itemColor = new Color(242, 201, 65);
	borderColor = new Color(99, 59, 19);
	itemBorder = BorderFactory.createLineBorder(borderColor, 3);
	setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	
	init();
    }
    
    private void init(){
	initTop();
	initLeft();
	initCenter();
	initBottom();
    }
    
    private void initBottom(){
	panelBottom = new JPanel();
	panelBottom.setBackground(borderColor);
	panelBottom.setLayout(new GridLayout(2, 2));
	
	JPanel jp1 = new JPanel();
	jp1.setBackground(borderColor);
	lblHealth = new JLabel("Health:");
	lblHealth.setAlignmentX(LEFT_ALIGNMENT);
	playerHealth = new JLabel();
	lblHealth.setForeground(Color.WHITE);
	playerHealth.setForeground(Color.WHITE);
	jp1.add(lblHealth);
	jp1.add(playerHealth);
	jp1.setAlignmentX(LEFT_ALIGNMENT);
	panelBottom.add(jp1);
	
	JPanel jp3 = new JPanel();
	jp3.setBackground(borderColor);
	lblDamage = new JLabel("Attack Damage:");
	lblDamage.setAlignmentX(LEFT_ALIGNMENT);
	playerDamage = new JLabel();
	lblDamage.setForeground(Color.WHITE);
	playerDamage.setForeground(Color.WHITE);
	jp3.add(lblDamage);
	jp3.add(playerDamage);
	jp3.setAlignmentX(LEFT_ALIGNMENT);
	panelBottom.add(jp3);
	
	JPanel jp2 = new JPanel();
	jp2.setBackground(borderColor);
	lblArmor = new JLabel("Armor:");
	lblArmor.setAlignmentX(LEFT_ALIGNMENT);
	playerArmor = new JLabel();
	lblArmor.setForeground(Color.WHITE);
	playerArmor.setForeground(Color.WHITE);
	jp2.add(lblArmor);
	jp2.add(playerArmor);
	jp2.setAlignmentX(LEFT_ALIGNMENT);
	panelBottom.add(jp2);
	
	JPanel jp4 = new JPanel();
	jp4.setBackground(borderColor);
	lblSpeed = new JLabel("Speed:");
	lblSpeed.setAlignmentX(LEFT_ALIGNMENT);
	playerSpeed = new JLabel();
	lblSpeed.setForeground(Color.WHITE);
	playerSpeed.setForeground(Color.WHITE);
	jp4.add(lblSpeed);
	jp4.add(playerSpeed);
	jp4.setAlignmentX(LEFT_ALIGNMENT);
	panelBottom.add(jp4);
	
	add(panelBottom, BorderLayout.SOUTH);
    }
    
    private void initTop(){
	panelTop = new JPanel();
	panelTop.setBackground(borderColor);
	
	title = new JLabel("Character");
	title.setAlignmentX(CENTER_ALIGNMENT);
	title.setForeground(Color.WHITE);
	panelTop.add(title, BorderLayout.NORTH);
	
	add(panelTop, BorderLayout.NORTH);
    }
    
    private void initCenter(){
	panelCenter = new JPanel();
	panelCenter.setBackground(borderColor);
	panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));
	
	playerImage = new JLabel(new ImageIcon(new ImageIcon("resources/equipment_player_image.png").getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT)));
	playerImage.setBackground(itemColor);
	playerImage.setOpaque(true);
	playerImage.setAlignmentX(CENTER_ALIGNMENT);
	playerImage.setBorder(BorderFactory.createLineBorder(borderColor, 5));
	panelCenter.add(playerImage);
	
	JPanel pl = new JPanel();
	pl.setBackground(borderColor);
	equipmentMainHand = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentMainHand.setBackground(itemColor);
	equipmentMainHand.setBorder(itemBorder);
	equipmentMainHand.setOpaque(true);
	pl.add(equipmentMainHand);
	
	equipmentSecondHand = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentSecondHand.setBackground(itemColor);
	equipmentSecondHand.setBorder(itemBorder);
	equipmentSecondHand.setOpaque(true);
	pl.add(equipmentSecondHand);
	panelCenter.add(pl);
	
	add(panelCenter, BorderLayout.CENTER);
    }
    
    private void initLeft(){
	panelLeft = new JPanel();
	panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
	panelLeft.setBackground(borderColor);
	
	equipmentHelmet = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentHelmet.setBackground(itemColor);
	equipmentHelmet.setBorder(itemBorder);
	equipmentHelmet.setOpaque(true);
	panelLeft.add(equipmentHelmet);
	
	equipmentChest = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentChest.setBackground(itemColor);
	equipmentChest.setBorder(itemBorder);
	equipmentChest.setOpaque(true);
	panelLeft.add(equipmentChest);
	
	equipmentLegs = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentLegs.setBackground(itemColor);
	equipmentLegs.setBorder(itemBorder);
	equipmentLegs.setOpaque(true);
	panelLeft.add(equipmentLegs);
	
	equipmentGloves = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentGloves.setBackground(itemColor);
	equipmentGloves.setBorder(itemBorder);
	equipmentGloves.setOpaque(true);
	panelLeft.add(equipmentGloves);
	
	equipmentBoots = new JLabel(GameIcons.TRANSPARENT.getIcon());
	equipmentBoots.setBackground(itemColor);
	equipmentBoots.setBorder(itemBorder);
	equipmentBoots.setOpaque(true);
	panelLeft.add(equipmentBoots);
	
	add(panelLeft, BorderLayout.WEST);
    }

    public void setShown(boolean shown) {
	this.shown = shown;
    }

    public boolean isShown() {
	return shown;
    }

    public JLabel getTitle() {
	return title;
    }

    public JLabel getPlayerImage() {
	return playerImage;
    }

    public JLabel getEquipmentHelmet() {
	return equipmentHelmet;
    }

    public JLabel getEquipmentChest() {
	return equipmentChest;
    }

    public JLabel getEquipmentLegs() {
	return equipmentLegs;
    }

    public JLabel getEquipmentGloves() {
	return equipmentGloves;
    }

    public JLabel getEquipmentBoots() {
	return equipmentBoots;
    }

    public JLabel getEquipmentMainHand() {
	return equipmentMainHand;
    }

    public JLabel getEquipmentSecondHand() {
	return equipmentSecondHand;
    }

    public JLabel getLblHealth() {
	return lblHealth;
    }

    public JLabel getLblArmor() {
	return lblArmor;
    }

    public JLabel getLblDamage() {
	return lblDamage;
    }

    public JLabel getLblSpeed() {
	return lblSpeed;
    }

    public JLabel getPlayerHealth() {
	return playerHealth;
    }

    public JLabel getPlayerArmor() {
	return playerArmor;
    }

    public JLabel getPlayerDamage() {
	return playerDamage;
    }

    public JLabel getPlayerSpeed() {
	return playerSpeed;
    }

    public JPanel getPanelTop() {
	return panelTop;
    }

    public JPanel getPanelLeft() {
	return panelLeft;
    }

    public JPanel getPanelBottom() {
	return panelBottom;
    }

    public JPanel getPanelCenter() {
	return panelCenter;
    }

    public Color getItemColor() {
	return itemColor;
    }

    public Color getBorderColor() {
	return borderColor;
    }

    public Border getItemBorder() {
	return itemBorder;
    }

    public void setPlayerImage(JLabel playerImage) {
	this.playerImage = playerImage;
    }

    public void setEquipmentHelmet(JLabel equipmentHelmet) {
	this.equipmentHelmet = equipmentHelmet;
    }

    public void setEquipmentChest(JLabel equipmentChest) {
	this.equipmentChest = equipmentChest;
    }

    public void setEquipmentLegs(JLabel equipmentLegs) {
	this.equipmentLegs = equipmentLegs;
    }

    public void setEquipmentGloves(JLabel equipmentGloves) {
	this.equipmentGloves = equipmentGloves;
    }

    public void setEquipmentBoots(JLabel equipmentBoots) {
	this.equipmentBoots = equipmentBoots;
    }

    public void setEquipmentMainHand(JLabel equipmentMainHand) {
	this.equipmentMainHand = equipmentMainHand;
    }

    public void setEquipmentSecondHand(JLabel equipmentSecondHand) {
	this.equipmentSecondHand = equipmentSecondHand;
    }

    public void setPlayerHealth(JLabel playerHealth) {
	this.playerHealth = playerHealth;
    }

    public void setPlayerArmor(JLabel playerArmor) {
	this.playerArmor = playerArmor;
    }

    public void setPlayerDamage(JLabel playerDamage) {
	this.playerDamage = playerDamage;
    }

    public void setPlayerSpeed(JLabel playerSpeed) {
	this.playerSpeed = playerSpeed;
    }
}
