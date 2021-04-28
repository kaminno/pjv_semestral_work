package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuEdit extends JMenu{
    private JMenuItem edit1;

    public EngineMenuEdit() {
	super("Edit");
	initMenuTools();
    }
    
    private void initMenuTools(){
	edit1 = new JMenuItem("Edit 1");
	this.add(edit1);
    }

    public JMenuItem getEdit1() {
	return edit1;
    }
}
