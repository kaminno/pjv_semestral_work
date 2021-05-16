package View.Engine;

import Controller.EngineController;
import Controller.GameController;
import Exceptions.WrongEquipmentTypeForWearableItemException;
import Exceptions.WrongTerrainType;
import Model.Figures.Figure;
import Model.Figures.Player;
import Model.Items.EquipmentType;
import Model.Map.Map;
import Model.Map.MapSize;
import Model.Terrains.Terrain;
import Model.Terrains.TerrainSize;
import Model.Terrains.TerrainType;
import View.Engine.EngineMenu;
import View.Game.GameMainWindow;
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EngineMainWindow extends JFrame{
    private EngineController controller;
    private Container pane;
    private EngineMenu menu;
    private EngineToolPane toolPane;
    private EngineMainWorkingPanel mainPanel;
    private TerrainType currentTerrainType = null;
    Map map = null;

    public EngineMainWindow(String title, EngineController controller) throws HeadlessException {
	super(title);
	this.controller = controller;
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(1200, 800));
	//this.setSize(1200, 800);
	//this.setMaximumSize(new Dimension(828, 666));
	pane = this.getContentPane();
	
        pane.setLayout(new BorderLayout());

	createWindow();
	setActions();
	this.setVisible(true);
    }
    
    private void createWindow(){
	menu = new EngineMenu();
	setJMenuBar(menu);
	Image icon = Toolkit.getDefaultToolkit().getImage("resources/helmet_icon.jpg");
	setIconImage(icon);
	
	toolPane = new EngineToolPane();
	pane.add(toolPane, BorderLayout.NORTH);
	mainPanel = new EngineMainWorkingPanel();
	pane.add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setActions(){
	
	toolPane.getTerrainTab().getPanelNewMap().getLabelNewMap().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent e) {
		// set number of icons/game fields as height and width

		int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
		int width = MapSize.SIZE.getWidth()/ TerrainSize.WIDTH.getSize();
		// create the "fill" dialog
		Object[] possibilities = {"blanc", "grass", "sand", "loam", "water"};
		String s = (String)JOptionPane.showInputDialog(
                    mainPanel,
                    "Fill with: ",
                    "Choose terrain to fill",
                    JOptionPane.PLAIN_MESSAGE,
                    GameIcons.NEW_MAP.getIcon(),
                    possibilities,
                    "blanc");
		
		// set the type variable to the chosen type from dialog above
		TerrainType type = null;
		for(TerrainType t : TerrainType.values()){
		    if(t.getName() == s){
			type = t;
			break;
		    }
		}
		
		if(type != null){
		    if(mainPanel.getCurrentMap() != null){
			// if there is a map, delete it
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
		    }
		    // create new map panel (view)
		    EngineMapPanel newMap = new EngineMapPanel(width, height);
		    // create new map class (in model)
		    map = new Map(height, width);
		    try {
			map.fillTheMapWithGround(type.getName(), type, 0, 0);
			//System.out.println("try");
		    } catch (WrongTerrainType ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			//System.out.println("catch");
		    }
		    //map.printMapTerrain();
		    // fill the (view) map with icons of specific type from "fill" dialog
		    newMap.fillTheMapWithGround(type);

		    // add new map
		    //mainPanel.add(newMap);
		    mainPanel.addNewMap(newMap);
		    menu.getMenuRun().getRun().setEnabled(true);
		    mainPanel.revalidate();
		    mainPanel.repaint();

		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
			    int x = e.getX();
			    int y = e.getY();
			    
			    if(toolPane.getSelectedIndex() == 0){
				// set changing icon
				ImageIcon icon = GameIcons.BLANC.getIcon();
				if(currentTerrainType == null){
				    currentTerrainType = TerrainType.BLANC;
				}
				else{
				    for(GameIcons gi : GameIcons.values()){
					if(gi.getLabel() == currentTerrainType.getName()){
					    icon = gi.getIcon();
					    break;
					}
				    }
				}

				int sx = (x - x%40)/40;
				int sy = (y - y%40)/40;

				// TODO - adding ground with bonus velocity
				map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
				mainPanel.getCurrentMap().removeTerrain(sx, sy);
				mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x%40)/40, (y - y%40)/40);
				//map.printMapTerrain();
			    }
//			    else if(toolPane.getSelectedIndex() == 1){
//				int sx = (x - x%40)/40;
//				int sy = (y - y%40)/40;
//				ArrayList<ArrayList<Figure>> figures = map.getMapFigures();
//				for(int i = 0; i < height; i++){
//				    for(int j = 0; j < width; j++){
//					if(map.getMapFigures().get(i).get(j) != null && map.getMapFigures().get(i).get(j) instanceof Player){
//					   map.getMapFigures().get(i).add(null);
//					}
//				    }
//				}
//				map.addFigure(sy, sx, new Player("Jan", 2, 3, 4, 3, 2, 3));
//				class mc{
//				    private void drawObjects(Graphics g) {
//					g.drawImage(GameIcons.PLAYER_Right_1.getIcon().getImage(), x, y, mainPanel);
//				    }
//				}
//				new mc();
//				//mainPanel.getCurrentMap().removeTerrain(sx, sy);
//				//mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x%40)/40, (y - y%40)/40);
//				map.printMapTerrain();
//			    }
			}
		    });
		}}});
	
	toolPane.getTerrainTab().getPanelNewMap().getLabelRemoveMap().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent e) {
		if(mainPanel.getCurrentMap() != null){
		    int n = JOptionPane.showConfirmDialog(
			mainPanel,
			"Do you really want to remove this map?",
			"Remove Map?",
			JOptionPane.YES_NO_OPTION);
		    if(n == 0){
			mainPanel.getCurrentMap().saveImage("moje_mapa", "png");
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
			menu.getMenuRun().getRun().setEnabled(false);
		    }
		}
		}});
	
	menu.getMenuTools().getToolHideTabPane().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){
			toolPane.setVisible(false);
			menu.getMenuTools().getToolShowTabPane().setEnabled(true);
			menu.getMenuTools().getToolHideTabPane().setEnabled(false);
		    }  
		});
//	menu.getMenuEdit().getEdit1().addActionListener(new ActionListener(){  
//	    public void actionPerformed(ActionEvent e){  
//			b.setVisible(false);
//		    }  
//		});
	menu.getMenuTools().getToolShowTabPane().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			toolPane.setVisible(true);
			menu.getMenuTools().getToolShowTabPane().setEnabled(false);
			menu.getMenuTools().getToolHideTabPane().setEnabled(true);
		    }
		});
	menu.getMenuFile().getMenuItemExit().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			System.exit(0);
		    }  
		});
	menu.getMenuFile().getMenuItemLoadGame().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){  
			JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
				    chooser.getSelectedFile().getName());
			    }
		}  
		});
//	toolPane.getTerrainTab().getPanelGround().getLabelGrass().addMouseListener(new MouseAdapter(){  
//	    public void actionPerformed(ActionEvent e){  
//			JFileChooser chooser = new JFileChooser();
//			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//				"JPG & GIF Images", "jpg", "gif");
//			    chooser.setFileFilter(filter);
//			    int returnVal = chooser.showOpenDialog(getParent());
//			    if(returnVal == JFileChooser.APPROVE_OPTION) {
//			       System.out.println("You chose to open this file: " +
//				    chooser.getSelectedFile().getName());
//			    }
//		}  
//		});
	toolPane.getTerrainTab().getPanelGround().getLabelGrass().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.GRASS;
	       System.out.println("GRASS");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelSand().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.SAND;
	       System.out.println("SAND");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelLoam().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.LOAM;
	       System.out.println("LOAM");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelGround().getLabelWater().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.WATER;
	       System.out.println("WATER");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelSolid().getLabelRock().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.ROCK;
	       System.out.println("ROCK");
	    }  
	});
	
	toolPane.getTerrainTab().getPanelSolid().getLabelWall().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
	       currentTerrainType = TerrainType.WALL;
	       System.out.println("WALL");
	    }  
	});
	
	toolPane.getPlayerTab().getPanelNewPlayer().getLabelNewPlayer().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {
		if(mainPanel.getCurrentMap() != null){
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter(){
			    Image im = null;
			    public void mouseClicked(MouseEvent e) {
				if(toolPane.getSelectedIndex() == 1){
				int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
				int width = MapSize.SIZE.getWidth()/ TerrainSize.WIDTH.getSize();
				int x = e.getX();
				int y = e.getY();
				int sx = (x - x%40)/40;
				int sy = (y - y%40)/40;
    //			    System.out.println("x: " + x);
    //			    System.out.println("y: " + y);
    //			    System.out.println("sx: " + sx);
    //			    System.out.println("sy: " + sy);
				if(map != null){
				    ArrayList<ArrayList<Figure>> figures = map.getMapFigures();
				    for(int i = 0; i < height; i++){
					for(int j = 0; j < width; j++){
					    if(map.getMapFigures().get(i).get(j) != null && map.getMapFigures().get(i).get(j) instanceof Player){
					       map.getMapFigures().get(i).remove(j);
					       map.getMapFigures().get(i).add(j, null);
					    }
					}
				    }
				    map.addFigure(sy, sx, new Player("Jan", 2, 3, 4, 3, 2, 3));

    //				class mc extends ImageIcon{
    //				    @Override
    //				    public void p(Graphics g) {
    //					super.paintComponent(g);
    //					g.drawImage(GameIcons.PLAYER_RIGHT_1.getIcon().getImage(), x, y, mainPanel.getCurrentMap());
    //					Toolkit.getDefaultToolkit().sync();
    //				    }
    //				}
				    im = GameIcons.PLAYER_RIGHT_1.getIcon().getImage();
				    //mainPanel.getCurrentMap().getGraphics().drawImage(im, x-20, y-20, mainPanel.getParent());
				    //mainPanel.getGraphics().drawImage(im, x-20, y-20, null);
				    
				    //mainPanel.getCurrentMap().getMapBoard().get(sy).get(sx).setIcon(GameIcons.PLAYER_RIGHT_1.getIcon());
				    //mc m = new mc();
				    //mainPanel.getCurrentMap().removeTerrain(sx, sy);
				    //mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x%40)/40, (y - y%40)/40);
				    map.printMapFigures();
				}
			    }
			};
		    });
		}
	    }  
	});
	
	toolPane.getPlayerTab().getPanelWeapons().getIconMainWeapon().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getPlayerTab().getPanelWeapons().isMainSelected()){
		    toolPane.getPlayerTab().getPanelWeapons().unselectMainWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
		else{
		    toolPane.getPlayerTab().getPanelWeapons().selectMainWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
	    }  
	});
	
	toolPane.getPlayerTab().getPanelWeapons().getIconSecondWeapon().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getPlayerTab().getPanelWeapons().isSecondSelected()){
		    toolPane.getPlayerTab().getPanelWeapons().unselectSecondWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
		else{
		    toolPane.getPlayerTab().getPanelWeapons().selectSecondWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
	    }  
	});
	
	toolPane.getItemsTab().getPanelHelmet().getIconHelmet().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getItemsTab().getPanelHelmet().isSelected()){
		    toolPane.getItemsTab().getPanelHelmet().unselectHelmet();
		    toolPane.getItemsTab().getPanelHelmet().repaint();
		}
		else{
		    toolPane.getItemsTab().getPanelHelmet().selectHelmet();
		    toolPane.getItemsTab().getPanelHelmet().repaint();
		}
	    }  
	});
	
	toolPane.getItemsTab().getPanelChest().getIconChest().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getItemsTab().getPanelChest().isSelected()){
		    toolPane.getItemsTab().getPanelChest().unselectChest();
		    toolPane.getItemsTab().getPanelChest().repaint();
		}
		else{
		    toolPane.getItemsTab().getPanelChest().selectChest();
		    toolPane.getItemsTab().getPanelChest().repaint();
		}
	    }  
	});
	
	toolPane.getItemsTab().getPanelLegs().getIconLegs().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getItemsTab().getPanelLegs().isSelected()){
		    toolPane.getItemsTab().getPanelLegs().unselectLegs();
		    toolPane.getItemsTab().getPanelLegs().repaint();
		}
		else{
		    toolPane.getItemsTab().getPanelLegs().selectLegs();
		    toolPane.getItemsTab().getPanelLegs().repaint();
		}
	    }  
	});
	
	toolPane.getItemsTab().getPanelGloves().getIconGloves().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getItemsTab().getPanelGloves().isSelected()){
		    toolPane.getItemsTab().getPanelGloves().unselectGloves();
		    toolPane.getItemsTab().getPanelGloves().repaint();
		}
		else{
		    toolPane.getItemsTab().getPanelGloves().selectGloves();
		    toolPane.getItemsTab().getPanelGloves().repaint();
		}
	    }  
	});
	
	toolPane.getItemsTab().getPanelBoots().getIconBoots().addMouseListener(new MouseAdapter(){  
	    public void mouseClicked(MouseEvent e)  {  
		if(toolPane.getItemsTab().getPanelBoots().isSelected()){
		    toolPane.getItemsTab().getPanelBoots().unselectBoots();
		    toolPane.getItemsTab().getPanelBoots().repaint();
		}
		else{
		    toolPane.getItemsTab().getPanelBoots().selectBoots();
		    toolPane.getItemsTab().getPanelBoots().repaint();
		}
	    }  
	});
	
	menu.getMenuRun().getRun().addActionListener(new ActionListener(){  
	    public void actionPerformed(ActionEvent e){
		controller.createNewMap(map);
		
		mainPanel.getCurrentMap().saveImage("resources/current.map", "png");
		
		try{
		    int health = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseHealth().getText());
		    int armor = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseArmor().getText());
		    int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseAttackDamage().getText());
		    int speed = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseSpeed().getText());
		    int invSpace = toolPane.getPlayerTab().getPanelInventory().getInventorySize();
		    controller.createNewPlayer("New Player", health, armor, damage, 1, speed, invSpace);
		}catch(NumberFormatException nfe){
		    JOptionPane.showMessageDialog(mainPanel,
		    "Player attribures are wrong",
		    "Wrong Attributes",
		    JOptionPane.WARNING_MESSAGE);
		    return;
		}catch(IllegalArgumentException iae){
		    JOptionPane.showMessageDialog(mainPanel,
		    "Player attribures are wrong",
		    "Wrong Attributes",
		    JOptionPane.WARNING_MESSAGE);
		    return;
		}
		
		try{
		    int bossHealth = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfHealth().getText());
		    int bossArmor = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfArmor().getText());
		    int bossDamage = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfDamage().getText());
		    controller.createBoss("Boss", bossHealth, bossArmor, bossDamage);
		}catch(NumberFormatException nfe){
		    JOptionPane.showMessageDialog(mainPanel,
		    "Boss attribures are wrong",
		    "Wrong Stats",
		    JOptionPane.WARNING_MESSAGE);
		    return;
		}catch(IllegalArgumentException iae){
		    JOptionPane.showMessageDialog(mainPanel,
		    "Boss attribures are wrong",
		    "Wrong Stats",
		    JOptionPane.WARNING_MESSAGE);
		    return;
		}
		
		if(toolPane.getItemsTab().getPanelHelmet().isSelected()){
		    try{
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelHelmet().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelHelmet().getTfDurability().getText());
			controller.createPieceOfGear("Helmet", armor, durability, EquipmentType.HEAD);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Helmet attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Helmet attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if(toolPane.getItemsTab().getPanelChest().isSelected()){
		    try{
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelChest().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelChest().getTfDurability().getText());
			controller.createPieceOfGear("Breast Armor", armor, durability, EquipmentType.CHEST);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Chest attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Chest attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if(toolPane.getItemsTab().getPanelLegs().isSelected()){
		    try{
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelLegs().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelLegs().getTfDurability().getText());
			controller.createPieceOfGear("Leg Armor", armor, durability, EquipmentType.LEGS);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Legs attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Legs attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if(toolPane.getItemsTab().getPanelGloves().isSelected()){
		    try{
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelGloves().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelGloves().getTfDurability().getText());
			controller.createPieceOfGear("Gloves", armor, durability, EquipmentType.HANDS);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Gloves attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Gloves attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if(toolPane.getItemsTab().getPanelBoots().isSelected()){
		    try{
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelBoots().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelBoots().getTfDurability().getText());
			controller.createPieceOfGear("Boots", armor, durability, EquipmentType.FEET);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Boot attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Boot attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		
		if(toolPane.getPlayerTab().getPanelWeapons().isMainSelected()){
		    try{
			int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDamage().getText());
			int durability = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDurability().getText());
			controller.createWeapon("Sword", damage, durability, EquipmentType.MAIN_HAND);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Main weapon attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Main weapon attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if(toolPane.getPlayerTab().getPanelWeapons().isSecondSelected()){
		    try{
			int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDamage().getText());
			int durability = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDurability().getText());
			controller.createWeapon("Dagger", damage, durability, EquipmentType.SECOND_HAND);
		    }catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(mainPanel,
			"Second weapon attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }catch(WrongEquipmentTypeForWearableItemException myEx){}
		    catch(IllegalArgumentException iae){
			JOptionPane.showMessageDialog(mainPanel,
			"Second weapon attributes are wrong",
			"Wrong Stats",
			JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		
		controller.equipItems();
		
		
		controller.runGame();
	    }
	});
    }

    public EngineToolPane getToolPane() {
	return toolPane;
    }

    public EngineMainWorkingPanel getMainPanel() {
	return mainPanel;
    }

    public EngineMenu getMenu() {
	return menu;
    }

    public EngineController getController() {
	return controller;
    }

    public Container getPane() {
	return pane;
    }

    public TerrainType getCurrentTerrainType() {
	return currentTerrainType;
    }

    public Map getMap() {
	return map;
    }
    
}
		
