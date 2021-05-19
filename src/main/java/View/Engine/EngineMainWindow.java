package View.Engine;

import Controller.EngineController;
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
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * main engine window with all listeners and components adding
 *
 * @author honzuna
 */
public class EngineMainWindow extends JFrame {

    private EngineController controller;
    private Container pane;
    private EngineMenu menu;
    private EngineToolPane toolPane;
    private EngineMainWorkingPanel mainPanel;
    private TerrainType currentTerrainType = null;
    private EngineBottomPanel bottomPanel;
    Map map = null;

    /**
     *
     * @param title
     * @param controller
     * @throws HeadlessException
     */
    public EngineMainWindow(String title, EngineController controller) throws HeadlessException {
	super(title);
	this.controller = controller;
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setMinimumSize(new Dimension(1200, 800 + 10));

	pane = this.getContentPane();
	pane.setLayout(new BorderLayout());

	createWindow();
	setActions();
	this.setVisible(true);

	// remove the map picture on program closing
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		File f = new File("resources/current.map.png");
		f.delete();
		System.exit(0);
	    }
	});
    }

    private void createWindow() {
	menu = new EngineMenu();
	setJMenuBar(menu);
	Image icon = Toolkit.getDefaultToolkit().getImage("resources/helmet_icon.jpg");
	setIconImage(icon);

	// add components
	bottomPanel = new EngineBottomPanel();
	pane.add(bottomPanel, BorderLayout.SOUTH);
	toolPane = new EngineToolPane();
	pane.add(toolPane, BorderLayout.NORTH);
	mainPanel = new EngineMainWorkingPanel();
	pane.add(mainPanel, BorderLayout.CENTER);
    }

    private void setActions() {
	// create new map on click on specific icon
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
		    } catch (WrongTerrainType ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    // fill the (view) map with icons of specific type from "fill" dialog
		    newMap.fillTheMapWithGround(type);

		    // add new map
		    mainPanel.addNewMap(newMap);
		    menu.getMenuRun().getRun().setEnabled(true);
		    menu.getMenuEdit().getExport().setEnabled(true);
		    bottomPanel.getLblCurrentTerrain().setText("blanc");
		    bottomPanel.repaint();
		    menu.getMenuFile().getMenuItemSaveMap().setEnabled(true);
		    mainPanel.revalidate();
		    mainPanel.repaint();

		    // add listener to make possible to change terrain on specific icon click
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

				// add terrain to the specific position on map
				// TODO - adding ground with bonus velocity
				map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
				mainPanel.getCurrentMap().removeTerrain(sx, sy);
				mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x % 40) / 40, (y - y % 40) / 40);
			    }
			}
		    });
		}
	    }
	});

	// remove map on click on specific icon
	toolPane.getTerrainTab().getPanelNewMap().getLabelRemoveMap().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (mainPanel.getCurrentMap() != null) {
		    // asking dialogue
		    int n = JOptionPane.showConfirmDialog(
			    mainPanel,
			    "Do you really want to remove this map?",
			    "Remove Map?",
			    JOptionPane.YES_NO_OPTION);
		    if (n == 0) {
			// remove map and set specific menu items to enable=false
			mainPanel.getCurrentMap().saveImage("moje_mapa", "png");
			mainPanel.removeMap(mainPanel.getCurrentMap().getId());
			menu.getMenuRun().getRun().setEnabled(false);
			menu.getMenuFile().getMenuItemSaveMap().setEnabled(false);
		    }
		}
	    }
	});

	// set hide tab to false
	menu.getMenuTools().getToolHideTabPane().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		toolPane.setVisible(false);
		menu.getMenuTools().getToolShowTabPane().setEnabled(true);
		menu.getMenuTools().getToolHideTabPane().setEnabled(false);
	    }
	});

	// save current map as image on click in menu-edit-export
	menu.getMenuEdit().getExport().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export map");

		int userSelection = fileChooser.showSaveDialog(pane);

		// if dialogue is confirm, save image
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    String[] spl = fileToSave.getAbsolutePath().split("\\.");
		    mainPanel.getCurrentMap().saveImage(spl[0],
			    spl[spl.length - 1]);
		}
	    }
	});

	// show tool pane
	menu.getMenuTools().getToolShowTabPane().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		toolPane.setVisible(true);
		menu.getMenuTools().getToolShowTabPane().setEnabled(false);
		menu.getMenuTools().getToolHideTabPane().setEnabled(true);
	    }
	});

	// exit program on click on specific item in menu-file
	menu.getMenuFile().getMenuItemExit().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	// create new map on click on item in menu-file
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
		    } catch (WrongTerrainType ex) {
			Logger.getLogger(EngineMainWindow.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    // fill the (view) map with icons of specific type from "fill" dialog
		    newMap.fillTheMapWithGround(type);

		    // add new map
		    mainPanel.addNewMap(newMap);
		    menu.getMenuRun().getRun().setEnabled(true);
		    menu.getMenuEdit().getExport().setEnabled(true);
		    bottomPanel.getLblCurrentTerrain().setText("blanc");
		    bottomPanel.repaint();
		    menu.getMenuFile().getMenuItemSaveMap().setEnabled(true);
		    mainPanel.revalidate();
		    mainPanel.repaint();

		    // add listener to make terrain changes on click possible
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

				// change terrain
				map.addTerrain(sy, sx, new Terrain(currentTerrainType.getName(), currentTerrainType));
				mainPanel.getCurrentMap().removeTerrain(sx, sy);
				mainPanel.getCurrentMap().addTerrain(new JLabel(icon), (x - x % 40) / 40, (y - y % 40) / 40);
			    }
			}
		    });
		}
	    }
	});

	// load item list from file
	menu.getMenuFile().getMenuItemLoadItems().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"PJV files", "pjv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(pane);
		// if dialogue is accepted
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File fileItemsToLoad = chooser.getSelectedFile();
		    // split on . and check if the file has corresponding format
		    String[] spl = fileItemsToLoad.getAbsolutePath().split("\\.");
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
			// if file format is correct, try to parse it correctly
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
				// split each line on ,
				String[] splitLine = line.split("\\,");
				if (splitLine.length != 3) {
				    throw new IllegalArgumentException("Wrong data length");
				}
				// for corresponding number at the beggining of line set the item as equiped
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

	// save current items to file
	menu.getMenuFile().getMenuItemSaveItems().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Items");

		int userSelection = fileChooser.showSaveDialog(pane);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    try {
			String[] spl = fileToSave.getAbsolutePath().split("\\.");
			FileWriter writer = new FileWriter(spl[0] + ".items.pjv");
			// for each selected item save its values
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

	// load map info from file
	menu.getMenuFile().getMenuItemLoadMap().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"PJV files", "pjv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(pane);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File fileItemsToLoad = chooser.getSelectedFile();
		    // split file name on .
		    String[] spl = fileItemsToLoad.getAbsolutePath().split("\\.");
		    // check the correct file name format
		    if (!spl[spl.length - 1].equals("pjv")) {
			JOptionPane.showMessageDialog(mainPanel,
				"Wrong file extension",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    }
		    if (!spl[spl.length - 2].equals("map")) {
			JOptionPane.showMessageDialog(mainPanel,
				"Try to load file that does not contain map info",
				"Loading Error",
				JOptionPane.WARNING_MESSAGE);
			return;
		    } else {
			// if the name format is correct, try to parse its content
			try {
			    Scanner mapScan = new Scanner(fileItemsToLoad);
			    int counter = 0;
			    int mapHeight = MapSize.getSIZE().getHeight() / 40;
			    int mapWidth = MapSize.getSIZE().getWidth() / 40;
			    // create new map and new mapview
			    Map tmpMap = new Map(mapHeight, mapWidth);
			    EngineMapPanel newMap = new EngineMapPanel(mapWidth, mapHeight);
			    newMap.fillTheMapWithGround(TerrainType.BLANC);
			    ImageIcon icon = GameIcons.BLANC.getIcon();

			    // iterate through the file to fill the map
			    for (int h = 0; h < mapHeight; h++) {
				String line = mapScan.nextLine();
				String[] terrainTypes = line.split(",");
				int w = 0;
				for (String terrain : terrainTypes) {
				    for (TerrainType terrainType : TerrainType.values()) {
					if (terrain.equals(terrainType.getName())) {
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
				    w++;
				}
				tmpMap.printMapTerrain();
				if (tmpMap.getMapTerrain().get(h).size() != mapWidth) {
				    throw new WrongTerrainType();
				}
			    }
			    // set the created map to the current map
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

			    // add listener to change the map terrain on click
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
				    }
				}
			    });
			} catch (WrongTerrainType wtt) {
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

	// same current map to file
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
			// go through the current map and save all lines
			for (int h = 0; h < map.getMapHeight(); h++) {
			    String s = map.getMapTerrain().get(h).get(0).getName();
			    for (int w = 1; w < map.getMapWidth(); w++) {
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

	// set current terrain type according to the icon clicked
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

	// make possible to set players coordinates
	toolPane.getPlayerTab().getPanelNewPlayer().getLabelNewPlayer().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		// map must be created
		if (mainPanel.getCurrentMap() != null) {
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (toolPane.getSelectedIndex() == 1) {
				// set player coordinates based on mouse click on the map board
				int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
				int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
				int x = e.getX();
				int y = e.getY();

				// set player in the map figures list and remove it from the old position it was
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
	
	// make possible to change boss coordinates on click on the map board
	toolPane.getBeastTab().getPanelBosses().getWarPigIcon().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (mainPanel.getCurrentMap() != null) {
		    mainPanel.getCurrentMap().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if (toolPane.getSelectedIndex() == 3) {
				// set boss coordinates by click on the map
				int height = MapSize.SIZE.getHeight() / TerrainSize.HEIGHT.getSize();
				int width = MapSize.SIZE.getWidth() / TerrainSize.WIDTH.getSize();
				int x = e.getX();
				int y = e.getY();

				// add boss to the map figures list
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
			}
		    ;
		}

	    
	);
	}}
	});
	
	// set items/weapon as selected/unselected by the clicking on their icons
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

	// run the game if all data are correct
	menu.getMenuRun().getRun().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		mainPanel.getCurrentMap().saveImage("resources/current.map", "png");

		// check the player data and create the player instance
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

		// check the boss data and create a boss instance
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

		// some item is selectid, check its attribures values and then create new instance of it and add it to the model
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

		// checks if the bat values are correct and create its instances
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
		
		// check if the skeleton values are correct and create its instances
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

		// create new map
		controller.createNewMap(map);

		controller.equipItems();

		// run the game
		controller.runGame();
	    }
	});
    }

    /**
     *
     * @return
     */
    public EngineToolPane getToolPane() {
	return toolPane;
    }

    /**
     *
     * @return
     */
    public EngineMainWorkingPanel getMainPanel() {
	return mainPanel;
    }

    /**
     *
     * @return
     */
    public EngineMenu getMenu() {
	return menu;
    }

    /**
     *
     * @return
     */
    public EngineController getController() {
	return controller;
    }

    /**
     *
     * @return
     */
    public Container getPane() {
	return pane;
    }

    /**
     *
     * @return
     */
    public TerrainType getCurrentTerrainType() {
	return currentTerrainType;
    }

    /**
     *
     * @return
     */
    public Map getMap() {
	return map;
    }

}
