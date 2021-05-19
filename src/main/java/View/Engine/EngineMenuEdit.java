package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuEdit extends JMenu{
    private JMenuItem export;

    public EngineMenuEdit() {
	super("Edit");
	initMenuTools();
    }
    
    private void initMenuTools(){
	export = new JMenuItem("Export map");
	export.setEnabled(false);
	this.add(export);
    }

    public JMenuItem getExport() {
	return export;
    }
}
