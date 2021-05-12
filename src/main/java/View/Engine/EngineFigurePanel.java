package View.Engine;

import Model.Map.MapSize;
import Model.Terrains.TerrainSize;
import Model.Terrains.TerrainType;
import View.GameIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineFigurePanel extends JPanel{
    private static int mapIds = 0;
    private final int id;
    private final int figureBoardWidth;
    private final int figureBoardHeight;
    private GridLayout gridLayout;
    private ArrayList<ArrayList<JLabel>> figureBoard;

    public EngineFigurePanel(int width, int height) {
	super();
	if (width <= 0 || height <= 0){
	    throw new IllegalArgumentException("Error: width and height must be > 0");
	}
	this.id = getNewId();
	// width and height are game fields size, not the JPanel size!
	this.figureBoardWidth = width;
	this.figureBoardHeight = height;
	//setSize(new Dimension(height * TerrainSize.HEIGHT.getSize(), width * TerrainSize.WIDTH.getSize()));
	setSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight()));
	//setSize(new Dimension(width * TerrainSize.WIDTH.getSize(), height * TerrainSize.HEIGHT.getSize()));
	setBackground(new Color(0, 0, 0, 0));
	gridLayout = new GridLayout(height, width);
	setLayout(gridLayout);
	setOpaque(false);
	
	this.figureBoard = new ArrayList(height);
	for (int i = 0; i < height; i++){
		this.figureBoard.add(i, new ArrayList(width));
		for (int j = 0; j < width; j++){
		    figureBoard.get(i).add(j, null);
		    
		}
	    }
	for (int h = 0; h < this.figureBoardHeight; h++){
	    for (int w = 0; w < this.figureBoardWidth; w++){
		//JLabel figure = new JLabel(GameIcons.TRANSPARENT.getIcon());
		JLabel figure = new JLabel(GameIcons.PLAYER_RIGHT_1.getIcon());
		add(figure);
		figureBoard.get(h).add(w, figure);
	    }
	}
	System.out.println("New figures created :)");
    }
    
    public void addFigure(JLabel figure, int x, int y){
	//figureBoard.get(y).add(x, figure);
	//add(figure, y * gameBoardWidth + x);
	
	figureBoard.get(y).add(x, figure);
	//mapBoard.get(y).get(x).add(figure);
	remove(y * figureBoardWidth + x);
	add(figureBoard.get(y).get(x), y * figureBoardWidth + x);
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
}
