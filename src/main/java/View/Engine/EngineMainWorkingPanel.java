package View.Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * main panel where the map is located
 * @author honzuna
 */
public class EngineMainWorkingPanel extends JPanel {

    EngineMapPanel currentMap;
    BorderLayout brl;
    JPanel jpp;

    /**
     *
     */
    public EngineMainWorkingPanel() {
	super();

	setBackground(new Color(89, 140, 145));
	currentMap = null;
    }

    /**
     *
     * @param newMap
     */
    public void addNewMap(EngineMapPanel newMap) {
	if (currentMap != null) {
	    remove(0);
	}
	currentMap = newMap;
	currentMap.setBounds(100, 0, newMap.getHeight(), newMap.getWidth());
	add(currentMap);
	revalidate();
	repaint();
    }

    /**
     * removes the map from the panel
     * @param mapId
     */
    public void removeMap(int mapId) {
	if (currentMap != null) {
	    remove(0);
	    revalidate();
	    repaint();
	    currentMap = null;
	}
    }

    /**
     *
     * @return
     */
    public EngineMapPanel getCurrentMap() {
	return currentMap;
    }
}
