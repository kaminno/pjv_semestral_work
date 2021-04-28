package View.Engine;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EngineToolPane extends JTabbedPane{
    private EngineToolPaneTerrainTab terrainTab;
    private EngineToolPanePlayerTab playerTab;

    public EngineToolPane() {
	this.setMaximumSize(new Dimension(50, 100));
	init();
    }
    
    private void init(){
	try{
	terrainTab = new EngineToolPaneTerrainTab();
	addTab("Terrain", terrainTab);
	}catch (IOException e){
	    System.out.println("Hehe + " + e);
	}
	
	playerTab = new EngineToolPanePlayerTab();
	addTab("Player", playerTab);
    }

    public EngineToolPaneTerrainTab getTerrainTab() {
	return terrainTab;
    }

    public EngineToolPanePlayerTab getPlayerTab() {
	return playerTab;
    }
    
    
}
