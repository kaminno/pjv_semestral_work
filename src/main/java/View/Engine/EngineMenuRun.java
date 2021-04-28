package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EngineMenuRun extends JMenu{
    private JMenuItem run;

    public EngineMenuRun() {
	super("Run");
	init();
    }
    
    private void init(){
	run = new JMenuItem("Run Game");
	add(run);
    }

    public JMenuItem getRun() {
	return run;
    }
}
