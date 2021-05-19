package View.Engine;

import javax.swing.JMenuBar;

/**
 * create engine menu
 * @author honzuna
 */
public class EngineMenu extends JMenuBar {

    private EngineMenuFile menuFile;
    private EngineMenuEdit menuEdit;
    private EngineMenuTools menuTools;
    private EngineMenuRun menuRun;

    /**
     *
     */
    public EngineMenu() {
	initMenu();
    }

    private void initMenu() {
	initMenuFile();
	initMenuEdit();
	initMenuTools();
	initMenuRun();
    }

    private void initMenuFile() {
	menuFile = new EngineMenuFile();
	add(menuFile);
    }

    private void initMenuEdit() {
	menuEdit = new EngineMenuEdit();
	add(menuEdit);
    }

    private void initMenuTools() {
	menuTools = new EngineMenuTools();
	add(menuTools);
    }

    private void initMenuRun() {
	menuRun = new EngineMenuRun();
	add(menuRun);
    }

    /**
     *
     * @return
     */
    public EngineMenuFile getMenuFile() {
	return menuFile;
    }

    /**
     *
     * @return
     */
    public EngineMenuTools getMenuTools() {
	return menuTools;
    }

    /**
     *
     * @return
     */
    public EngineMenuEdit getMenuEdit() {
	return menuEdit;
    }

    /**
     *
     * @return
     */
    public EngineMenuRun getMenuRun() {
	return menuRun;
    }
}
