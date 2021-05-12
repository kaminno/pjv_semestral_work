package View.Engine;

import Model.Map.MapSize;
import Model.Terrains.TerrainSize;
import Model.Terrains.TerrainType;
import View.GameIcons;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineMapPanel extends JPanel{
    private static int mapIds = 0;
    private final int id;
    private final int gameBoardWidth;
    private final int gameBoardHeight;
    private GridLayout gridLayout;

    private ArrayList<ArrayList<JLabel>> mapBoard;

    public EngineMapPanel(int widthW, int heightH) {
	super();
	if (widthW <= 0 || heightH <= 0){
	    throw new IllegalArgumentException("Error: width and height must be > 0");
	}
	this.id = getNewId();
	// width and height are game fields size, not the JPanel size!
	this.gameBoardWidth = widthW;
	this.gameBoardHeight = heightH;
	//setSize(new Dimension(gameBoardWidth * TerrainSize.WIDTH.getSize(), gameBoardHeight * TerrainSize.HEIGHT.getSize()));
	//setSize(MapSize.getSIZE().getHeight(), MapSize.getSIZE().getWidth());
	
	setSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight()));
	
	System.out.println("s: " + getSize());
	System.out.println("constructor height: " + heightH * TerrainSize.HEIGHT.getSize());
	System.out.println("width: " + widthW * TerrainSize.WIDTH.getSize());
	//setSize(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize());
	//setMinimumSize(new Dimension(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize()));
	//setBackground(Color.WHITE);
	
	//setOpaque(false);
	
	gridLayout = new GridLayout(gameBoardHeight, gameBoardWidth);
	setLayout(gridLayout);
	System.out.println("s: " + getSize());
	//gridLayout.preferredLayoutSize(this);
	
	this.mapBoard = new ArrayList(gameBoardHeight);
	for (int i = 0; i < gameBoardHeight; i++){
		this.mapBoard.add(i, new ArrayList(gameBoardWidth));
		for (int j = 0; j < gameBoardWidth; j++){
		    mapBoard.get(i).add(j, null);
		}
	    }

	System.out.println("New map created :)");
    }
    
    public void saveImage(String name,String type) {
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		try{
			ImageIO.write(image, type, new File(name+"."+type));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void addTerrain(JLabel terrain, int x, int y){
//	if(mapBoard.get(y).get(x) == null){
//	    mapBoard.get(y).add(x, new smallField(terrain, null, null));
//	}
//	else{
//	    mapBoard.get(y).get(x).setTerrain(terrain);
//	}
	//removeTerrain(x, y);
	mapBoard.get(y).add(x, terrain);
	add(terrain, y * gameBoardWidth + x);
	revalidate();
	repaint();
    }
    
    public void removeTerrain(int x, int y){
	mapBoard.get(y).remove(x);
	remove(y * gameBoardWidth + x);
	revalidate();
	repaint();
    }
    
    public void fillTheMapWithGround(TerrainType type){
	// set icon coresponds to terrain type
	ImageIcon i = null;
		for(GameIcons gi : GameIcons.values()){
		    if(type.getName() == gi.getLabel()){
			i = gi.getIcon();
			break;
		    }
		}
	// go through the grid layout and adding JLabels with icon from above
	System.out.println("h before cycle" + gameBoardHeight);
	System.out.println("w before cycle" + gameBoardWidth);
	for (int h = 0; h < this.gameBoardHeight; h++){
	    for (int w = 0; w < this.gameBoardWidth; w++){
		JLabel terrain = new JLabel(i);
		//System.out.println("I: " + terrain.getIcon().getIconHeight() + " ... " + terrain.getIcon().getIconWidth());
		add(terrain);
		mapBoard.get(h).add(w, terrain);
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

    public int getGameBoardWidth() {
	return gameBoardWidth;
    }

    public int getGameBoardHeight() {
	return gameBoardHeight;
    }

    public GridLayout getGridLayout() {
	return gridLayout;
    }

//    public ArrayList<ArrayList<JLabel>> getMapBoard() {
//	return mapBoard;
//    }
}
