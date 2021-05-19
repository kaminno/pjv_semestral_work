package View.Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class EngineMainWorkingPanel extends JPanel {

    EngineMapPanel currentMap;
    BorderLayout brl;
    JPanel jpp;

    public EngineMainWorkingPanel() {
	super();

	setBackground(new Color(89, 140, 145));
	currentMap = null;
    }

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

    public void removeMap(int mapId) {
	if (currentMap != null) {
	    remove(0);
	    revalidate();
	    repaint();
	    currentMap = null;
	}
    }

    public EngineMapPanel getCurrentMap() {
	return currentMap;
    }
}
