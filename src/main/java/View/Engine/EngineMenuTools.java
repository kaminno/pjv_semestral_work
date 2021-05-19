package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * menu tools item
 * @author honzuna
 */
public class EngineMenuTools extends JMenu {

    private JMenuItem toolShowTabPane;
    private JMenuItem toolHideTabPane;

    /**
     *
     */
    public EngineMenuTools() {
	super("Tools");
	initMenuTools();
    }

    private void initMenuTools() {
	toolShowTabPane = new JMenuItem("Show Tools");
	toolShowTabPane.setEnabled(false);
	toolHideTabPane = new JMenuItem("Hide Tools");
	this.add(toolShowTabPane);
	this.add(toolHideTabPane);
    }

    /**
     *
     * @return
     */
    public JMenuItem getToolShowTabPane() {
	return toolShowTabPane;
    }

    /**
     *
     * @return
     */
    public JMenuItem getToolHideTabPane() {
	return toolHideTabPane;
    }

}
