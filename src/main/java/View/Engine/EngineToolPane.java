package View.Engine;

import java.io.IOException;
import javax.swing.JTabbedPane;

/**
 * tabbed pane with specific items
 * @author honzuna
 */
public class EngineToolPane extends JTabbedPane {

    private EngineToolPaneTerrainTab terrainTab;
    private EngineToolPanePlayerTab playerTab;
    private EngineToolPaneItemsTab itemsTab;
    private EngineToolPaneBeastTab beastTab;

    /**
     *
     */
    public EngineToolPane() {
	init();
    }

    private void init() {
	try {
	    terrainTab = new EngineToolPaneTerrainTab();
	    addTab("Terrain", terrainTab);
	} catch (IOException e) {
	    System.out.println("Hehe + " + e);
	}

	playerTab = new EngineToolPanePlayerTab();
	addTab("Player", playerTab);

	itemsTab = new EngineToolPaneItemsTab();
	addTab("Items", itemsTab);

	beastTab = new EngineToolPaneBeastTab();
	addTab("Beasts", beastTab);
    }

    /**
     *
     * @return
     */
    public EngineToolPaneTerrainTab getTerrainTab() {
	return terrainTab;
    }

    /**
     *
     * @return
     */
    public EngineToolPanePlayerTab getPlayerTab() {
	return playerTab;
    }

    /**
     *
     * @return
     */
    public EngineToolPaneItemsTab getItemsTab() {
	return itemsTab;
    }

    /**
     *
     * @return
     */
    public EngineToolPaneBeastTab getBeastTab() {
	return beastTab;
    }
}
