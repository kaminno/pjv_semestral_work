package View.Engine;

import Model.Map.MapSize;
import Model.Terrains.TerrainType;
import View.GameIcons;
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

/**
 * panel with map visualisation
 * @author honzuna
 */
public class EngineMapPanel extends JPanel {

    private static int mapIds = 0;
    private final int id;
    private final int gameBoardWidth;
    private final int gameBoardHeight;
    private GridLayout gridLayout;

    private ArrayList<ArrayList<JLabel>> mapBoard;

    /**
     *
     * @param widthW
     * @param heightH
     */
    public EngineMapPanel(int widthW, int heightH) {
	super();
	if (widthW <= 0 || heightH <= 0) {
	    throw new IllegalArgumentException("Error: width and height must be > 0");
	}
	this.id = getNewId();
	// width and height are game fields size, not the JPanel size!
	this.gameBoardWidth = widthW;
	this.gameBoardHeight = heightH;
	setSize(new Dimension(MapSize.getSIZE().getWidth(), MapSize.getSIZE().getHeight()));

	gridLayout = new GridLayout(gameBoardHeight, gameBoardWidth);
	setLayout(gridLayout);

	this.mapBoard = new ArrayList(gameBoardHeight);
	for (int i = 0; i < gameBoardHeight; i++) {
	    this.mapBoard.add(i, new ArrayList(gameBoardWidth));
	    for (int j = 0; j < gameBoardWidth; j++) {
		mapBoard.get(i).add(j, null);
	    }
	}
    }

    /**
     * save panel as an image of specific extension
     * @param name
     * @param type
     */
    public void saveImage(String name, String type) {
	BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = image.createGraphics();
	paint(g2);
	try {
	    ImageIO.write(image, type, new File(name + "." + type));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     *
     * @param terrain
     * @param x
     * @param y
     */
    public void addTerrain(JLabel terrain, int x, int y) {
	mapBoard.get(y).add(x, terrain);
	add(terrain, y * gameBoardWidth + x);
	revalidate();
	repaint();
    }

    /**
     *
     * @param x
     * @param y
     */
    public void removeTerrain(int x, int y) {
	mapBoard.get(y).remove(x);
	remove(y * gameBoardWidth + x);
	revalidate();
	repaint();
    }

    /**
     * fill the map panel with icons of specific terrain type
     * @param type
     */
    public void fillTheMapWithGround(TerrainType type) {
	// set icon coresponds to terrain type
	ImageIcon i = null;
	for (GameIcons gi : GameIcons.values()) {
	    if (type.getName() == gi.getLabel()) {
		i = gi.getIcon();
		break;
	    }
	}
	// go through the grid layout and adding JLabels with icon from above
	for (int h = 0; h < this.gameBoardHeight; h++) {
	    for (int w = 0; w < this.gameBoardWidth; w++) {
		JLabel terrain = new JLabel(i);
		add(terrain);
		mapBoard.get(h).add(w, terrain);
	    }
	}
	revalidate();
	repaint();
    }

    private static int getNewId() {
	int id = getMapIds();
	updateId();
	return id;
    }

    private static int getMapIds() {
	return mapIds;
    }

    private static void updateId() {
	mapIds += 1;
    }

    /**
     *
     * @return
     */
    public int getId() {
	return id;
    }

    /**
     *
     * @return
     */
    public int getGameBoardWidth() {
	return gameBoardWidth;
    }

    /**
     *
     * @return
     */
    public int getGameBoardHeight() {
	return gameBoardHeight;
    }

    /**
     *
     * @return
     */
    public GridLayout getGridLayout() {
	return gridLayout;
    }
}
