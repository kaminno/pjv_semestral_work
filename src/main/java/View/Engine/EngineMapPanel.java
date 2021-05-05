package View.Engine;

import Model.Terrains.TerrainSize;
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
	super();
	if (width <= 0 || height <= 0){
	    throw new IllegalArgumentException("Error: width and height must be > 0");
	}
	this.id = getNewId();
	// width and height are game fields size, not the JPanel size!
	this.width = width;
	this.height = height;
	setSize(new Dimension(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize()));
	System.out.println("s: " + getSize());
	System.out.println("constructor height: " + height * TerrainSize.HEIGHT.getSize());
	System.out.println("width: " + width * TerrainSize.WIDTH.getSize());
	//setSize(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize());
	//setMinimumSize(new Dimension(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize()));
	setBackground(Color.WHITE);
	//setOpaque(true);
	gridLayout = new GridLayout(height, width);
	setLayout(gridLayout);
	System.out.println("s: " + getSize());
	//gridLayout.preferredLayoutSize(this);
	
	this.mapBoard = new ArrayList(height);
	for (int i = 0; i < height; i++){
		this.mapBoard.add(i, new ArrayList(width));
		for (int j = 0; j < width; j++){
		    mapBoard.get(i).add(j, null);
		}
	    }
	//revalidate();
	//repaint();
	//setVisible(true);
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
	// set icon coresponds to terrain type
	ImageIcon i = null;
		for(GameIcons gi : GameIcons.values()){
		    if(type.getName() == gi.getLabel()){
			System.out.println("adding icon");
			i = gi.getIcon();
			System.out.println("i: " + i.getIconHeight());
			System.out.println("i: " + i.getIconWidth());
			break;
		    }
		}
	// sets size (from consultation)
	setSize(new Dimension(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize()));
	setLayout(gridLayout);
	System.out.println(i.getIconHeight());
	System.out.println(i.getIconWidth());
	// go through the grid layout and adding JLabels with icon from above
	for (int h = 0; h < this.height; h++){
	    for (int w = 0; w < this.width; w++){
		System.out.println("width " + this.getWidth());
		System.out.println("height " + this.getHeight());
		System.out.println("s: " + getSize());
		JLabel terrain = new JLabel(i);
		System.out.println("jl: " + terrain.getSize());
		//terrain.setSize(40, 40);
		//terrain.setVisible(true);
		//add(terrain, h * width + w);
		//this.add(terrain, 0);
		
		// add terrain (JLabel) to the JPanel
		add(terrain);
		mapBoard.get(h).add(w, terrain);
		//terrain.setVisible(true);
		//terrain.revalidate();
		//terrain.repaint();
		System.out.println("component count: " + getComponentCount());
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
