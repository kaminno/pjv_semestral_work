package View.Engine;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuFile extends JMenu{
    private JMenuItem menuItemNewMap;
    private JMenuItem menuItemLoadItems;
    private JMenuItem menuItemLoadMap;
    private JMenuItem menuItemSaveItems;
    private JMenuItem menuItemSaveMap;
    private JMenuItem menuItemExit;

    public EngineMenuFile() {
	super("File");
	initMenuFile();
    }
    
    private void initMenuFile(){
	menuItemNewMap = new JMenuItem("New Map", new ImageIcon("resources/icon_new_file.png"));
	menuItemLoadItems = new JMenuItem("Load Items", new ImageIcon("resources/icon_load_file.png"));
	menuItemLoadMap = new JMenuItem("Load Map", new ImageIcon("resources/icon_load_file.png"));
	menuItemSaveItems = new JMenuItem("Save Items");
	menuItemSaveMap = new JMenuItem("Save Map");
	menuItemSaveMap.setEnabled(false);
	menuItemExit = new JMenuItem("Exit", new ImageIcon("resources/icon_exit.png"));
	
	this.add(menuItemNewMap);
	this.add(menuItemLoadMap);
	this.add(menuItemLoadItems);
	this.addSeparator();
	this.add(menuItemSaveMap);
	this.add(menuItemSaveItems);
	this.addSeparator();
	this.add(menuItemExit);
    }

    public JMenuItem getMenuItemLoadItems() {
	return menuItemLoadItems;
    }

    public JMenuItem getMenuItemSaveItems() {
	return menuItemSaveItems;
    }

    public JMenuItem getMenuItemExit() {
	return menuItemExit;
    }

    public JMenuItem getMenuItemNewMap() {
	return menuItemNewMap;
    }

    public JMenuItem getMenuItemLoadMap() {
	return menuItemLoadMap;
    }

    public JMenuItem getMenuItemSaveMap() {
	return menuItemSaveMap;
    }
}
