package View.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class EngineMenu extends JMenuBar {
    private EngineMenuFile menuFile;
    private EngineMenuEdit menuEdit;
    private EngineMenuTools menuTools;
    private EngineMenuRun menuRun;
    
    
    public EngineMenu() {
	initMenu();
    }
    
    private void initMenu(){
	initMenuFile();
	initMenuEdit();
	initMenuTools();
	initMenuRun();
    }
    
    private void initMenuFile(){
	menuFile = new EngineMenuFile();
	add(menuFile);
    }
    
    private void initMenuEdit(){
	menuEdit = new EngineMenuEdit();
	add(menuEdit);
    }
    
    private void initMenuTools(){
	menuTools = new EngineMenuTools();
	add(menuTools);
    }
    
    private void initMenuRun(){
	menuRun = new EngineMenuRun();
	add(menuRun);
    }

    public EngineMenuFile getMenuFile() {
	return menuFile;
    }

    public EngineMenuTools getMenuTools() {
	return menuTools;
    }
    
    public EngineMenuEdit getMenuEdit(){
	return menuEdit;
    }

    public EngineMenuRun getMenuRun() {
	return menuRun;
    }
}