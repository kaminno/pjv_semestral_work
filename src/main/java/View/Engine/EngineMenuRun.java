package View.Engine;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * run menu item
 * @author honzuna
 */
public class EngineMenuRun extends JMenu {

    private JMenuItem run;

    /**
     *
     */
    public EngineMenuRun() {
	super("Run");
	init();
    }

    private void init() {
	run = new JMenuItem("Run Game");
	run.setEnabled(false);
	add(run);
    }

    /**
     *
     * @return
     */
    public JMenuItem getRun() {
	return run;
    }
}
