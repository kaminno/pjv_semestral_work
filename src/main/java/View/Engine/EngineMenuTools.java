package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuTools extends JMenu{
    private JMenuItem toolShowTabPane;
    private JMenuItem toolHideTabPane;

    public EngineMenuTools() {
	super("Tools");
	initMenuTools();
    }
    
    private void initMenuTools(){
	toolShowTabPane = new JMenuItem("Show Tools");
	toolShowTabPane.setEnabled(false);
	toolHideTabPane = new JMenuItem("Hide Tools");
	this.add(toolShowTabPane);
	this.add(toolHideTabPane);
    }

    public JMenuItem getToolShowTabPane() {
	return toolShowTabPane;
    }

    public JMenuItem getToolHideTabPane() {
	return toolHideTabPane;
    }
    
}
