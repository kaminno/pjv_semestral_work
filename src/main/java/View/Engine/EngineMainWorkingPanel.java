package View.Engine;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JPanel;

public class EngineMainWorkingPanel extends JPanel{
    LinkedList<EngineMapPanel> maps;
    EngineMapPanel currentMap;

    public EngineMainWorkingPanel() {
	setBackground(Color.red);
	currentMap = null;
	maps = new LinkedList();
    }
    
    public void addNewMap(EngineMapPanel newMap){
	if(currentMap != null){
	    remove(0);
	}
	//maps.add(newMap);
	currentMap = newMap;
	add(currentMap);
	currentMap.setVisible(true);
	revalidate();
	repaint();
	System.out.println("New map added");
    }
    
    public void removeMap(int mapId){
	if(currentMap != null){
	    remove(0);
	    revalidate();
	    repaint();
	    currentMap = null;
	}
//	for(EngineMapPanel map : maps){
//	    if(map.getId() == mapId){
//		maps.remove(map);
//		if(currentMap.getId() == mapId){
//		    currentMap = null;
//		}
//	    }
//	}
    }

    public EngineMapPanel getCurrentMap() {
	return currentMap;
    }
    
    
    
}
