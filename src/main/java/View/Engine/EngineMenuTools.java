package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuTools extends JMenu{
    private JMenuItem tool1;
    private JMenuItem tool2;

    public EngineMenuTools() {
	super("Tools");
	initMenuTools();
    }
    
    private void initMenuTools(){
	tool1 = new JMenuItem("Tool 1");
	tool2 = new JMenuItem("Tool 2");
	this.add(tool1);
	this.add(tool2);
    }

    public JMenuItem getTool1() {
	return tool1;
    }

    public JMenuItem getTool2() {
	return tool2;
    }
}
