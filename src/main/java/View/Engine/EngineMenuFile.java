package View.Engine;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuFile extends JMenu{
    private JMenuItem menuItemNewGame;
    private JMenuItem menuItemNewMap;
    private JMenuItem menuItemLoadGame;
    private JMenuItem menuItemSaveGame;
    private JMenuItem menuItemExit;

    public EngineMenuFile() {
	super("File");
	initMenuFile();
    }
    
    private void initMenuFile(){
	menuItemNewGame = new JMenuItem("New Game", new ImageIcon("resources/icon_new_file.png"));
	menuItemNewMap = new JMenuItem("New Map");
	menuItemLoadGame = new JMenuItem("Load Game", new ImageIcon("resources/icon_load_file.png"));
	menuItemSaveGame = new JMenuItem("Save Game");
	menuItemExit = new JMenuItem("Exit", new ImageIcon("resources/icon_exit.png"));
	
	this.add(menuItemNewGame);
	this.add(menuItemNewMap);
	menuItemNewMap.setEnabled(false);
	this.add(menuItemLoadGame);
	this.addSeparator();
	this.add(menuItemSaveGame);
	this.addSeparator();
	this.add(menuItemExit);
    }

    public JMenuItem getMenuItemNewGame() {
	return menuItemNewGame;
    }

    public JMenuItem getMenuItemLoadGame() {
	return menuItemLoadGame;
    }

    public JMenuItem getMenuItemSaveGame() {
	return menuItemSaveGame;
    }

    public JMenuItem getMenuItemExit() {
	return menuItemExit;
    }
}
