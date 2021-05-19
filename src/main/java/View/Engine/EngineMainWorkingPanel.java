package View.Engine;

import Model.Map.MapSize;
import View.GameIcons;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// Main (red) panel, where the new map panel is located
public class EngineMainWorkingPanel extends JPanel{
    private LinkedList<EngineMapPanel> maps;
    private JLayeredPane layers;
    EngineMapPanel currentMap;
    EngineFigurePanel currentFigures;
    BorderLayout brl;
    JPanel jpp;

    public EngineMainWorkingPanel() {
	super();
	//layers = new JLayeredPane();
	//layers.setSize(MapSize.getSIZE().getHeight(), MapSize.getSIZE().getWidth());
	//layers.setBounds(100, 0, MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight());
	//layers.setSize(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight());
	//layers.setVisible(true);
	//layers.setSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight()));
	//brl = new BorderLayout();
	//setLayout(brl);
	
	setBackground(new Color(89, 140, 145));
	//setBackground(new Color(0, 117, 130));
	currentMap = null;
	currentFigures = null;
	//layers.add(currentMap, 1);
	//layers.add(currentFigures, 2);
	
	//add(layers, BorderLayout.CENTER);
	//add(layers);
	maps = new LinkedList();
	
	
    }
    
    public void addNewMap(EngineMapPanel newMap){
	// add new map
	if(currentMap != null){
	    remove(0);
	}
	//maps.add(newMap);
	currentMap = newMap;
	currentFigures = new EngineFigurePanel(newMap.getGameBoardWidth(), newMap.getGameBoardHeight());
	//currentFigures = new EngineFigurePanel(newMap.getGameBoardHeight(), newMap.getGameBoardWidth());
	
	currentMap.setBounds(100, 0, newMap.getHeight(), newMap.getWidth());
	currentFigures.setBounds(100, 0, newMap.getHeight(), newMap.getWidth());
//	jpp = new JPanel();
//	jpp.setBackground(Color.BLUE);
//	jpp.setLayout(new GridLayout(newMap.getGameBoardHeight(), newMap.getGameBoardWidth()));
//	//jpp.add(new JLabel(GameIcons.WALL.getIcon()));
//	jpp.setSize(newMap.getWidth(), newMap.getHeight());
//	jpp.setBounds(100, 0, newMap.getHeight(), newMap.getWidth());
//	for (int h = 0; h < newMap.getGameBoardHeight(); h++){
//	    for (int w = 0; w < newMap.getGameBoardWidth(); w++){
//		JLabel terrain = new JLabel(GameIcons.WALL.getIcon());
//		jpp.add(terrain);
//	    }
//	}
	//add(currentMap, BorderLayout.CENTER);
	//jpp.setOpaque(true);
	//layers.add(jpp, 10, 1);
	
	//layers.add(currentFigures, 10, 0);
	//layers.add(currentMap, 10, 1);
	add(currentMap);//, BorderLayout.CENTER);
//	currentMap.setVisible(true);
//	currentFigures.setVisible(true);
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
