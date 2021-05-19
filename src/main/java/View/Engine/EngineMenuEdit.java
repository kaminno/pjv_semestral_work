package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * edit item of menu
 * @author honzuna
 */
public class EngineMenuEdit extends JMenu {

    private JMenuItem export;

    /**
     *
     */
    public EngineMenuEdit() {
	super("Edit");
	initMenuTools();
    }

    private void initMenuTools() {
	export = new JMenuItem("Export map");
	export.setEnabled(false);
	this.add(export);
    }

    /**
     *
     * @return
     */
    public JMenuItem getExport() {
	return export;
    }
}
