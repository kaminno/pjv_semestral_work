package View.Engine;

import Controller.EngineController;
import Controller.GameController;
import Exceptions.WrongEquipmentTypeForWearableItemException;
import Exceptions.WrongTerrainType;
import Model.Figures.Beast;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

public class EngineMainWindow extends JFrame {

    private EngineController controller;
    private Container pane;
    private EngineMenu menu;
    private EngineToolPane toolPane;
    private EngineMainWorkingPanel mainPanel;
    private TerrainType currentTerrainType = null;
    private EngineBottomPanel bottomPanel;
    Map map = null;

    public EngineMainWindow(String title, EngineController controller) throws HeadlessException {
	super(title);
	this.controller = controller;
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setMinimumSize(new Dimension(1200, 800 + 10));
	//this.setSize(1200, 800);
	//this.setMaximumSize(new Dimension(828, 666));
	pane = this.getContentPane();

	pane.setLayout(new BorderLayout());

	createWindow();
	setActions();
	this.setVisible(true);
    }

    private void createWindow() {
	menu = new EngineMenu();
	setJMenuBar(menu);
	Image icon = Toolkit.getDefaultToolkit().getImage("resources/helmet_icon.jpg");
	setIconImage(icon);

	bottomPanel = new EngineBottomPanel();
	pane.add(bottomPanel, BorderLayout.SOUTH);
	toolPane = new EngineToolPane();
	pane.add(toolPane, BorderLayout.NORTH);
	mainPanel = new EngineMainWorkingPanel();
	pane.add(mainPanel, BorderLayout.CENTER);
    }

    private void setActions() {

	toolPane.getTerrainTab().getPanelNewMap().getLabelNewMap().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		// set number of icons/game fields as height and width

		int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
		int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
		// create the "fill" dialog
		Object[] possibilities = {"blanc", "grass", "sand", "loam", "water"};
		String s = (String) JOptionPane.showInputDialog(
			mainPanel,
			"Fill with: ",
			"Choose terrain to fill",
			JOptionPane.PLAIN_MESSAGE,
			GameIcons.NEW_MAP.getIcon(),
			possibilities,
			"blanc");

		// set the type variable to the chosen type from dialog above
		TerrainType type = null;
		for (TerrainType t : TerrainType.values()) {
		    if (t.getName() == s) {
			type = t;
			break;
		    }
		}

		if (type != null) {
		    if (mainPanel.getCurrentMap() != null) {
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
		    menu.getMenuEdit().getExport().setEnabled(true);
		    bottomPanel.getLblCurrentTerrain().setText("blanc");
		    bottomPanel.repaint();
		    menu.getMenuFile().getMenuItemSaveMap().setEnabled(true);
		    mainPanel.revalidate();
		    mainPanel.repaint();

		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    int x = e.getX();
			    int y = e.getY();

			    if (toolPane.getSelectedIndex() == 0) {
				// set changing icon
				ImageIcon icon = GameIcons.BLANC.getIcon();
				if (currentTerrainType == null) {
				    currentTerrainType = TerrainType.BLANC;
				} else {
				    for (GameIcons gi : GameIcons.values()) {
					if (gi.getLabel() == currentTerrainType.getName()) {
					    icon = gi.getIcon();
					    break;
					}
				    }
				}

				int sx = (x - x % 40) / 40;
				int sy = (y - y % 40) / 40;

				// TODO - adding ground with bonus velocity
				map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
				mainPanel.getCurrentMap().removeTerrain(sx, sy);
				mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x % 40) / 40, (y - y % 40) / 40);
				//map.printMapTerrain();
			    }
			}
		    });
		}
	    }
	});

	toolPane.getTerrainTab().getPanelNewMap().getLabelRemoveMap().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (mainPanel.getCurrentMap() != null) {
		    int n = JOptionPane.showConfirmDialog(
			    mainPanel,
			    "Do you really want to remove this map?",
			    "Remove Map?",
			    JOptionPane.YES_NO_OPTION);
		    if (n == 0) {
			mainPanel.getCurrentMap().saveImage("moje_mapa", "png");
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
			menu.getMenuRun().getRun().setEnabled(false);
			menu.getMenuFile().getMenuItemSaveMap().setEnabled(false);
		    }
		}
	    }
	});

	menu.getMenuTools().getToolHideTabPane().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		toolPane.setVisible(false);
		menu.getMenuTools().getToolShowTabPane().setEnabled(true);
		menu.getMenuTools().getToolHideTabPane().setEnabled(false);
	    }
	});

	menu.getMenuEdit().getExport().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export map");

		int userSelection = fileChooser.showSaveDialog(pane);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    String[] spl = fileToSave.getAbsolutePath().split("\\.");
		    mainPanel.getCurrentMap().saveImage(spl[0],
			    spl[spl.length - 1]);
		}
	    }
	});

	menu.getMenuTools().getToolShowTabPane().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		toolPane.setVisible(true);
		menu.getMenuTools().getToolShowTabPane().setEnabled(false);
		menu.getMenuTools().getToolHideTabPane().setEnabled(true);
	    }
	});

	menu.getMenuFile().getMenuItemExit().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	menu.getMenuFile().getMenuItemNewMap().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
		int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
		// create the "fill" dialog
		Object[] possibilities = {"blanc", "grass", "sand", "loam", "water"};
		String s = (String) JOptionPane.showInputDialog(
			mainPanel,
			"Fill with: ",
			"Choose terrain to fill",
			JOptionPane.PLAIN_MESSAGE,
			GameIcons.NEW_MAP.getIcon(),
			possibilities,
			"blanc");

		// set the type variable to the chosen type from dialog above
		TerrainType type = null;
		for (TerrainType t : TerrainType.values()) {
		    if (t.getName() == s) {
			type = t;
			break;
		    }
		}

		if (type != null) {
		    if (mainPanel.getCurrentMap() != null) {
			// if there is a map, delete it
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
		    }
		    // create new map panel (view)
		    EngineMapPanel newMap = new EngineMapPanel(width, height);
		    // create new map class (in model)
		    map = new Map(height, width);
		    try {
			map.fillTheMapWithGround(type.getName(), type, 0, 0);
			//System.out.println("Map size: " + map.getMapTerrain().get(0).size());
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
		    menu.getMenuEdit().getExport().setEnabled(true);
		    bottomPanel.getLblCurrentTerrain().setText("blanc");
		    bottomPanel.repaint();
		    menu.getMenuFile().getMenuItemSaveMap().setEnabled(true);
		    mainPanel.revalidate();
		    mainPanel.repaint();

		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    int x = e.getX();
			    int y = e.getY();

			    if (toolPane.getSelectedIndex() == 0) {
				// set changing icon
				ImageIcon icon = GameIcons.BLANC.getIcon();
				if (currentTerrainType == null) {
				    currentTerrainType = TerrainType.BLANC;
				} else {
				    for (GameIcons gi : GameIcons.values()) {
					if (gi.getLabel() == currentTerrainType.getName()) {
					    icon = gi.getIcon();
					    break;
					}
				    }
				}

				int sx = (x - x % 40) / 40;
				int sy = (y - y % 40) / 40;

				map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
				mainPanel.getCurrentMap().removeTerrain(sx, sy);
				mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x % 40) / 40, (y - y % 40) / 40);
			    }
			}
		    });
		}
	    }
	});

	menu.getMenuFile().getMenuItemLoadItems().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"PJV files", "pjv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(pane);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File fileItemsToLoad = chooser.getSelectedFile();
		    //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    String[] spl = fileItemsToLoad.getAbsolutePath().split("\\.");
		    System.out.println("item file: " + spl[0] + " - " + spl[spl.length - 2] + " - " + spl[spl.length - 1]);
		    if (!spl[spl.length - 1].equals("pjv")) {
			JOptionPane.showMessageDialog(mainPanel,
				"Wrong file extension",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } else if (!spl[spl.length - 2].equals("items")) {
			JOptionPane.showMessageDialog(mainPanel,
				"Try to load file that does not contain items info",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } else {
			System.out.println("You chose to open this file: "
				+ chooser.getSelectedFile().getName());
			try {
			    Scanner myScan = new Scanner(fileItemsToLoad);
			    int counter = 0;
			    while (myScan.hasNextLine()) {
				if (counter == 7) {
				    JOptionPane.showMessageDialog(mainPanel,
					    "Too much data lines",
					    "Loading Error",
					    JOptionPane.WARNING_MESSAGE);
				    return;
				}
				String line = myScan.nextLine();
				String[] splitLine = line.split("\\,");
				if (splitLine.length != 3) {
				    throw new IllegalArgumentException("Wrong data length");
				}
				if (splitLine[0].equals("0")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getItemsTab().getPanelHelmet().getTfArmor().setText(splitLine[1]);
				    toolPane.getItemsTab().getPanelHelmet().getTfDurability().setText(splitLine[2]);
				    toolPane.getItemsTab().getPanelHelmet().selectHelmet();
				} else if (splitLine[0].equals("1")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getItemsTab().getPanelChest().getTfArmor().setText(splitLine[1]);
				    toolPane.getItemsTab().getPanelChest().getTfDurability().setText(splitLine[2]);
				    toolPane.getItemsTab().getPanelChest().selectChest();
				} else if (splitLine[0].equals("2")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getItemsTab().getPanelLegs().getTfArmor().setText(splitLine[1]);
				    toolPane.getItemsTab().getPanelLegs().getTfDurability().setText(splitLine[2]);
				    toolPane.getItemsTab().getPanelLegs().selectLegs();
				} else if (splitLine[0].equals("3")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getItemsTab().getPanelGloves().getTfArmor().setText(splitLine[1]);
				    toolPane.getItemsTab().getPanelGloves().getTfDurability().setText(splitLine[2]);
				    toolPane.getItemsTab().getPanelGloves().selectGloves();
				} else if (splitLine[0].equals("4")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getItemsTab().getPanelBoots().getTfArmor().setText(splitLine[1]);
				    toolPane.getItemsTab().getPanelBoots().getTfDurability().setText(splitLine[2]);
				    toolPane.getItemsTab().getPanelBoots().selectBoots();
				} else if (splitLine[0].equals("5")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDamage().setText(splitLine[1]);
				    toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDurability().setText(splitLine[1]);
				    toolPane.getPlayerTab().getPanelWeapons().selectMainWeapon();
				} else if (splitLine[0].equals("6")) {
				    int ar = Integer.parseUnsignedInt(splitLine[1]);
				    int du = Integer.parseUnsignedInt(splitLine[2]);
				    toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDamage().setText(splitLine[1]);
				    toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDurability().setText(splitLine[1]);
				    toolPane.getPlayerTab().getPanelWeapons().selectSecondWeapon();
				}
				counter++;
			    }
			} catch (FileNotFoundException ex) {
			    Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			    JOptionPane.showMessageDialog(mainPanel,
				    "Load was not successful",
				    "Loading Error",
				    JOptionPane.WARNING_MESSAGE);
			    return;
			} catch (IllegalArgumentException iae) {
			    JOptionPane.showMessageDialog(mainPanel,
				    "Wrong line length",
				    "Loading Error",
				    JOptionPane.WARNING_MESSAGE);
			    return;
			}
		    }

		}
	    }
	});

	menu.getMenuFile().getMenuItemSaveItems().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Items");

		int userSelection = fileChooser.showSaveDialog(pane);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    try {
			String[] spl = fileToSave.getAbsolutePath().split("\\.");
			FileWriter writer = new FileWriter(spl[0] + ".items.pjv");
			if (toolPane.getItemsTab().getPanelHelmet().isSelected()) {
			    writer.write(0 + "," + toolPane.getItemsTab().getPanelHelmet().getTfArmor().getText() + "," + toolPane.getItemsTab().getPanelHelmet().getTfDurability().getText() + "\n");
			}
			if (toolPane.getItemsTab().getPanelChest().isSelected()) {
			    writer.write(1 + "," + toolPane.getItemsTab().getPanelChest().getTfArmor().getText() + "," + toolPane.getItemsTab().getPanelChest().getTfDurability().getText() + "\n");
			}
			if (toolPane.getItemsTab().getPanelLegs().isSelected()) {
			    writer.write(2 + "," + toolPane.getItemsTab().getPanelLegs().getTfArmor().getText() + "," + toolPane.getItemsTab().getPanelLegs().getTfDurability().getText() + "\n");
			}
			if (toolPane.getItemsTab().getPanelGloves().isSelected()) {
			    writer.write(3 + "," + toolPane.getItemsTab().getPanelGloves().getTfArmor().getText() + "," + toolPane.getItemsTab().getPanelGloves().getTfDurability().getText() + "\n");
			}
			if (toolPane.getItemsTab().getPanelBoots().isSelected()) {
			    writer.write(4 + "," + toolPane.getItemsTab().getPanelBoots().getTfArmor().getText() + "," + toolPane.getItemsTab().getPanelBoots().getTfDurability().getText() + "\n");
			}
			if (toolPane.getPlayerTab().getPanelWeapons().isMainSelected()) {
			    writer.write(5 + "," + toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDamage().getText() + "," + toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDurability().getText() + "\n");
			}
			if (toolPane.getPlayerTab().getPanelWeapons().isSecondSelected()) {
			    writer.write(6 + "," + toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDamage().getText() + "," + toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDurability().getText() + "\n");
			}

			writer.close();
		    } catch (IOException ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(mainPanel,
				"Save was not successful",
				"Saving Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
	    }
	});
	
	menu.getMenuFile().getMenuItemLoadMap().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"PJV files", "pjv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(pane);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File fileItemsToLoad = chooser.getSelectedFile();
		    //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    String[] spl = fileItemsToLoad.getAbsolutePath().split("\\.");
		    if(!spl[spl.length-1].equals("pjv")){
			JOptionPane.showMessageDialog(mainPanel,
				"Wrong file extension",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    if(!spl[spl.length-2].equals("map")){
			JOptionPane.showMessageDialog(mainPanel,
				"Try to load file that does not contain map info",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    else{
			try{
			    Scanner mapScan = new Scanner(fileItemsToLoad);
			    int counter = 0;
			    int mapHeight = MapSize.getSIZE().getHeight() / 40;
			    int mapWidth = MapSize.getSIZE().getWidth()/ 40;
			    //while (mapScan.hasNextLine()) {
			    Map tmpMap = new Map(mapHeight, mapWidth);
			    EngineMapPanel newMap = new EngineMapPanel(mapWidth, mapHeight);
			    newMap.fillTheMapWithGround(TerrainType.BLANC);
			    ImageIcon icon = GameIcons.BLANC.getIcon();
			    //System.out.println("Map tmp size: " + tmpMap.getMapTerrain().get(0).size());
			    for(int h = 0; h < mapHeight; h++){
				String line = mapScan.nextLine();
				String[] terrainTypes = line.split(",");
				int w = 0;
				//System.out.println("Map tmp size: " + tmpMap.getMapTerrain().get(h).size());
				for(String terrain : terrainTypes){
				    for(TerrainType terrainType : TerrainType.values()){
					if(terrain.equals(terrainType.getName())){
					    tmpMap.addTerrain(h, w, new Terrain(terrain, terrainType));
					    for (GameIcons gi : GameIcons.values()) {
						if (gi.getLabel().equals(terrain)) {
						    icon = gi.getIcon();
						    break;
						}
					    }
					    newMap.removeTerrain(w, h);
					    newMap.addTerrain(new JLabel(icon), w, h);
					}
				    }
				    //System.out.println("Map tmp size: " + tmpMap.getMapTerrain().get(h).size());
				    w++;
				}
				tmpMap.printMapTerrain();
				//System.out.println("Map size: " + tmpMap.getMapTerrain().get(h).size());
				if(tmpMap.getMapTerrain().get(h).size() != mapWidth){
				    throw new WrongTerrainType();
				}
			    }
			    map = tmpMap;
			    tmpMap.printMapTerrain();
			    if (mainPanel.getCurrentMap() != null) {
				// if there is a map, delete it
				mainPanel.removeMap(mainPanel.getCurrentMap().getId());
			    }
			    mainPanel.addNewMap(newMap);
			    menu.getMenuRun().getRun().setEnabled(true);
			    menu.getMenuEdit().getExport().setEnabled(true);
			    menu.getMenuFile().getMenuItemSaveMap().setEnabled(true);
			    mainPanel.revalidate();
			    mainPanel.repaint();
			    
			    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				    int x = e.getX();
				    int y = e.getY();

				    if (toolPane.getSelectedIndex() == 0) {
					// set changing icon
					ImageIcon icon = GameIcons.BLANC.getIcon();
					if (currentTerrainType == null) {
					    currentTerrainType = TerrainType.BLANC;
					} else {
					    for (GameIcons gi : GameIcons.values()) {
						if (gi.getLabel() == currentTerrainType.getName()) {
						    icon = gi.getIcon();
						    break;
						}
					    }
					}

					int sx = (x - x % 40) / 40;
					int sy = (y - y % 40) / 40;

					// TODO - adding ground with bonus velocity
					map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
					mainPanel.getCurrentMap().removeTerrain(sx, sy);
					mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x % 40) / 40, (y - y % 40) / 40);
					//map.printMapTerrain();
				    }
				}
			    });
			}catch(WrongTerrainType wtt){
			    JOptionPane.showMessageDialog(mainPanel,
				    "Load was not successful",
				    "Loading Error",
				    JOptionPane.WARNING_MESSAGE);
			    return;
			} catch (FileNotFoundException ex) {
			    Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			    JOptionPane.showMessageDialog(mainPanel,
				    "Load was not successful",
				    "Loading Error",
				    JOptionPane.WARNING_MESSAGE);
			    return;
			}
		    }
		}
	    }
	});
	
	menu.getMenuFile().getMenuItemSaveMap().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Map");

		int userSelection = fileChooser.showSaveDialog(pane);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    try {
			String[] spl = fileToSave.getAbsolutePath().split("\\.");
			FileWriter writer = new FileWriter(spl[0] + ".map.pjv");
			for(int h = 0; h < map.getMapHeight(); h++){
			    String s = map.getMapTerrain().get(h).get(0).getName();
			    for(int w = 1; w < map.getMapWidth(); w++){
				s += "," + map.getMapTerrain().get(h).get(w).getName();
			    }
			    writer.write(s + "\n");
			}
			writer.close();
		    } catch (IOException ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(mainPanel,
		    	"Save was not successful",
		    	"Saving Error",
		    	JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
	    }
	});

	toolPane.getTerrainTab().getPanelGround().getLabelGrass().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.GRASS;
		bottomPanel.getLblCurrentTerrain().setText("grass");
		bottomPanel.repaint();
	    }
	});

	toolPane.getTerrainTab().getPanelGround().getLabelSand().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.SAND;
		bottomPanel.getLblCurrentTerrain().setText("sand");
		bottomPanel.repaint();
	    }
	});

	toolPane.getTerrainTab().getPanelGround().getLabelLoam().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.LOAM;
		bottomPanel.getLblCurrentTerrain().setText("loam");
		bottomPanel.repaint();
	    }
	});

	toolPane.getTerrainTab().getPanelGround().getLabelWater().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.WATER;
		bottomPanel.getLblCurrentTerrain().setText("water");
		bottomPanel.repaint();
	    }
	});

	toolPane.getTerrainTab().getPanelSolid().getLabelRock().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.ROCK;
		bottomPanel.getLblCurrentTerrain().setText("rock");
		bottomPanel.repaint();
	    }
	});

	toolPane.getTerrainTab().getPanelSolid().getLabelWall().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		currentTerrainType = TerrainType.WALL;
		bottomPanel.getLblCurrentTerrain().setText("wall");
		bottomPanel.repaint();
	    }
	});

	toolPane.getPlayerTab().getPanelNewPlayer().getLabelNewPlayer().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (mainPanel.getCurrentMap() != null) {
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			//Image im = null;
			public void mouseClicked(MouseEvent e) {
			    if (toolPane.getSelectedIndex() == 1) {
				int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
				int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
				int x = e.getX();
				int y = e.getY();

				if (map != null && controller.hasPlayerCorrectCoordinates(map, x, y)) {
				    ArrayList<ArrayList<Figure>> figures = map.getMapFigures();
				    for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
					    if (map.getMapFigures().get(i).get(j) != null && map.getMapFigures().get(i).get(j) instanceof Player) {
						map.getMapFigures().get(i).remove(j);
						map.getMapFigures().get(i).add(j, null);
					    }
					}
				    }
				    int sx = (x - x % 40) / 40;
				    int sy = (y - y % 40) / 40;
				    toolPane.getPlayerTab().getPanelNewPlayer().getCoorX().setText(Integer.toString(x));
				    toolPane.getPlayerTab().getPanelNewPlayer().getCoorY().setText(Integer.toString(y));
				    repaint();
				    map.addFigure(sy, sx, new Player("player", 1, 1, 1, 1, 1, 1));
				}
			    }
			}
		    ;
		}
	    
	);
	}
	    }  
	});
	
	toolPane.getBeastTab().getPanelBosses().getWarPigIcon().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (mainPanel.getCurrentMap() != null) {
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			//Image im = null;
			public void mouseClicked(MouseEvent e) {
			    if (toolPane.getSelectedIndex() == 3) {
				int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
				int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
				int x = e.getX();
				int y = e.getY();

				if (map != null && controller.hasBeastCorrectCoordinates(x, y, map)) {
				    ArrayList<ArrayList<Figure>> figures = map.getMapFigures();
				    for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
					    if (map.getMapFigures().get(i).get(j) != null && map.getMapFigures().get(i).get(j) instanceof Beast) {
						map.getMapFigures().get(i).remove(j);
						map.getMapFigures().get(i).add(j, null);
					    }
					}
				    }
				    int sx = (x - x % 40) / 40;
				    int sy = (y - y % 40) / 40;
				    toolPane.getBeastTab().getPanelBosses().getCoorX().setText(Integer.toString(x));
				    toolPane.getBeastTab().getPanelBosses().getCoorY().setText(Integer.toString(y));
				    repaint();
				    map.addFigure(sy, sx, new Beast("boss", 1, 1, 1, 1, 1));
				}
			    }
			};
		});
	    }}
	});
	
	toolPane.getPlayerTab().getPanelWeapons().getIconMainWeapon().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getPlayerTab().getPanelWeapons().isMainSelected()) {
		    toolPane.getPlayerTab().getPanelWeapons().unselectMainWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		} else {
		    toolPane.getPlayerTab().getPanelWeapons().selectMainWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
	    }
	});

	toolPane.getPlayerTab().getPanelWeapons().getIconSecondWeapon().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getPlayerTab().getPanelWeapons().isSecondSelected()) {
		    toolPane.getPlayerTab().getPanelWeapons().unselectSecondWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		} else {
		    toolPane.getPlayerTab().getPanelWeapons().selectSecondWeapon();
		    toolPane.getPlayerTab().getPanelWeapons().repaint();
		}
	    }
	});

	toolPane.getItemsTab().getPanelHelmet().getIconHelmet().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getItemsTab().getPanelHelmet().isSelected()) {
		    toolPane.getItemsTab().getPanelHelmet().unselectHelmet();
		    toolPane.getItemsTab().getPanelHelmet().repaint();
		} else {
		    toolPane.getItemsTab().getPanelHelmet().selectHelmet();
		    toolPane.getItemsTab().getPanelHelmet().repaint();
		}
	    }
	});

	toolPane.getItemsTab().getPanelChest().getIconChest().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getItemsTab().getPanelChest().isSelected()) {
		    toolPane.getItemsTab().getPanelChest().unselectChest();
		    toolPane.getItemsTab().getPanelChest().repaint();
		} else {
		    toolPane.getItemsTab().getPanelChest().selectChest();
		    toolPane.getItemsTab().getPanelChest().repaint();
		}
	    }
	});

	toolPane.getItemsTab().getPanelLegs().getIconLegs().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getItemsTab().getPanelLegs().isSelected()) {
		    toolPane.getItemsTab().getPanelLegs().unselectLegs();
		    toolPane.getItemsTab().getPanelLegs().repaint();
		} else {
		    toolPane.getItemsTab().getPanelLegs().selectLegs();
		    toolPane.getItemsTab().getPanelLegs().repaint();
		}
	    }
	});

	toolPane.getItemsTab().getPanelGloves().getIconGloves().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getItemsTab().getPanelGloves().isSelected()) {
		    toolPane.getItemsTab().getPanelGloves().unselectGloves();
		    toolPane.getItemsTab().getPanelGloves().repaint();
		} else {
		    toolPane.getItemsTab().getPanelGloves().selectGloves();
		    toolPane.getItemsTab().getPanelGloves().repaint();
		}
	    }
	});

	toolPane.getItemsTab().getPanelBoots().getIconBoots().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (toolPane.getItemsTab().getPanelBoots().isSelected()) {
		    toolPane.getItemsTab().getPanelBoots().unselectBoots();
		    toolPane.getItemsTab().getPanelBoots().repaint();
		} else {
		    toolPane.getItemsTab().getPanelBoots().selectBoots();
		    toolPane.getItemsTab().getPanelBoots().repaint();
		}
	    }
	});

	menu.getMenuRun().getRun().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		//controller.createNewMap(map);

		mainPanel.getCurrentMap().saveImage("resources/current.map", "png");

		try {
		    int health = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseHealth().getText());
		    int armor = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseArmor().getText());
		    int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseAttackDamage().getText());
		    int speed = Integer.parseInt(toolPane.getPlayerTab().getPanelAttributes().getTfBaseSpeed().getText());
		    int invSpace = toolPane.getPlayerTab().getPanelInventory().getInventorySize();
		    controller.createNewPlayer("New Player", health, armor, damage, 1, speed, invSpace);
		    int x = Integer.parseInt(toolPane.getPlayerTab().getPanelNewPlayer().getCoorX().getText());
		    int y = Integer.parseInt(toolPane.getPlayerTab().getPanelNewPlayer().getCoorY().getText());
		    controller.getModel().getPlayer().setxPosition(x);
		    controller.getModel().getPlayer().setyPosition(y);
		} catch (NumberFormatException nfe) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Player attribures or coordinates are wrong",
			    "Wrong Attributes",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		} catch (IllegalArgumentException iae) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Player attribures are wrong",
			    "Wrong Attributes",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}

		try {
		    int bossHealth = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfHealth().getText());
		    int bossArmor = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfArmor().getText());
		    int bossDamage = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getWarPigTfDamage().getText());
		    controller.createBoss("Boss", bossHealth, bossArmor, bossDamage);
		    int x = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getCoorX().getText());
		    int y = Integer.parseInt(toolPane.getBeastTab().getPanelBosses().getCoorY().getText());
		    controller.getModel().getBoss().setxPosition(x);
		    controller.getModel().getBoss().setyPosition(y);
		} catch (NumberFormatException nfe) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Boss attribures or coordinates are wrong",
			    "Wrong Stats",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		} catch (IllegalArgumentException iae) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Boss attribures are wrong",
			    "Wrong Stats",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}

		if (toolPane.getItemsTab().getPanelHelmet().isSelected()) {
		    try {
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelHelmet().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelHelmet().getTfDurability().getText());
			controller.createPieceOfGear("Helmet", armor, durability, EquipmentType.HEAD);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Helmet attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Helmet attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if (toolPane.getItemsTab().getPanelChest().isSelected()) {
		    try {
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelChest().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelChest().getTfDurability().getText());
			controller.createPieceOfGear("Breast Armor", armor, durability, EquipmentType.CHEST);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Chest attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Chest attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if (toolPane.getItemsTab().getPanelLegs().isSelected()) {
		    try {
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelLegs().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelLegs().getTfDurability().getText());
			controller.createPieceOfGear("Leg Armor", armor, durability, EquipmentType.LEGS);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Legs attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Legs attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if (toolPane.getItemsTab().getPanelGloves().isSelected()) {
		    try {
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelGloves().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelGloves().getTfDurability().getText());
			controller.createPieceOfGear("Gloves", armor, durability, EquipmentType.HANDS);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Gloves attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Gloves attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if (toolPane.getItemsTab().getPanelBoots().isSelected()) {
		    try {
			int armor = Integer.parseInt(toolPane.getItemsTab().getPanelBoots().getTfArmor().getText());
			int durability = Integer.parseInt(toolPane.getItemsTab().getPanelBoots().getTfDurability().getText());
			controller.createPieceOfGear("Boots", armor, durability, EquipmentType.FEET);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Boot attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Boot attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}

		if (toolPane.getPlayerTab().getPanelWeapons().isMainSelected()) {
		    try {
			int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDamage().getText());
			int durability = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfMainWeaponDurability().getText());
			controller.createWeapon("Sword", damage, durability, EquipmentType.MAIN_HAND);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Main weapon attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Main weapon attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}
		if (toolPane.getPlayerTab().getPanelWeapons().isSecondSelected()) {
		    try {
			int damage = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDamage().getText());
			int durability = Integer.parseInt(toolPane.getPlayerTab().getPanelWeapons().getTfSecondWeaponDurability().getText());
			controller.createWeapon("Dagger", damage, durability, EquipmentType.SECOND_HAND);
		    } catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(mainPanel,
				"Second weapon attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } catch (WrongEquipmentTypeForWearableItemException myEx) {
		    } catch (IllegalArgumentException iae) {
			JOptionPane.showMessageDialog(mainPanel,
				"Second weapon attributes are wrong",
				"Wrong Stats",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		}

		try {
		    int count = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getBatTfCount().getText());
		    if (count < 0 || count > controller.mapFreeSpaceForBeasts(map)) {
			JOptionPane.showMessageDialog(mainPanel,
				"Bat count is too low or too high",
				"Wrong Bat Count",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    int health = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getBatTfHealth().getText());
		    int armor = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getBatTfArmor().getText());
		    int damage = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getBatTfDamage().getText());
		    controller.createBat(health, armor, damage, map, count);
		} catch (NumberFormatException nfe) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Wrong bat data",
			    "Wrong Data",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}
		try {
		    int count = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getSkeletonTfCount().getText());
		    if (count < 0 || count > controller.mapFreeSpaceForBeasts(map)) {
			JOptionPane.showMessageDialog(mainPanel,
				"Skeleton count is too low or too high",
				"Wrong Bat Count",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    int health = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getSkeletonTfHealth().getText());
		    int armor = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getSkeletonTfArmor().getText());
		    int damage = Integer.parseInt(toolPane.getBeastTab().getPanelWeakBeasts().getSkeletonTfDamage().getText());
		    controller.createSkeleton(health, armor, damage, map, count);
		} catch (NumberFormatException nfe) {
		    JOptionPane.showMessageDialog(mainPanel,
			    "Wrong skeleton data",
			    "Wrong Data",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}

		controller.createNewMap(map);

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
