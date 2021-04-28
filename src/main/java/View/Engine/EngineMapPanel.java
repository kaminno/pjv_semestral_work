package View.Engine;

import Model.Terrains.TerrainType;
import View.GameIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineMapPanel extends JPanel{
    private static int mapIds = 0;
    private final int id;
    private final int width;
    private final int height;
    private GridLayout gridLayout;
    private ArrayList<ArrayList<JLabel>> mapBoard;

    public EngineMapPanel(int width, int height) {
	if (width <= 0 || height <= 0){
	    throw new IllegalArgumentException("Error: width and height must be > 0");
	}
	this.id = getNewId();
	this.width = width;
	this.height = height;
	setSize(new Dimension(height * 20, width * 20));
	setBackground(Color.WHITE);
	//setOpaque(true);
	gridLayout = new GridLayout(height, width);
	setLayout(gridLayout);
	this.mapBoard = new ArrayList(height);
	for (int i = 0; i < height; i++){
		this.mapBoard.add(i, new ArrayList(width));
		for (int j = 0; j < width; j++){
		    mapBoard.get(i).add(j, null);
		}
	    }
	revalidate();
	repaint();
	setVisible(true);
	System.out.println("New map created :)");
    }
    
    public void addTerrain(JLabel terrain, int x, int y){
	mapBoard.get(y).add(x, terrain);
	add(terrain, y * width + x);
	revalidate();
	repaint();
    }
    
    public void removeTerrain(int x, int y){
	mapBoard.get(y).remove(x);
	remove(y * width + x);
	revalidate();
	repaint();
    }
    
    public void fillTheMapWithGround(TerrainType type){
	for (int h = 0; h < this.height; h++){
	    for (int w = 0; w < this.width; w++){
		ImageIcon i = null;
		for(GameIcons gi : GameIcons.values()){
		    if(type.getName() == gi.getLabel()){
			i = gi.getIcon();
			break;
		    }
		}
		JLabel terrain = new JLabel(i);
		mapBoard.get(h).add(w, terrain);
		add(terrain);
		terrain.setVisible(true);
		//add(terrain, h*width + w);
	    }
	}
	revalidate();
	repaint();
    }
    
    private static int getNewId(){
	int id = getMapIds();
	updateId();
	return id;
    }
    
    private static int getMapIds(){
	return mapIds;
    }
    
    private static void updateId(){
	mapIds += 1;
    }
    
    public int getId(){
	return id;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public GridLayout getGridLayout() {
	return gridLayout;
    }

    public ArrayList<ArrayList<JLabel>> getMapBoard() {
	return mapBoard;
    }
}
