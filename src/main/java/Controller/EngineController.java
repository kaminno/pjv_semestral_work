package Controller;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Exceptions.WrongEquipmentTypeForWearableItemException;
import Model.Figures.Beast;
import Model.Figures.Figure;
import Model.Figures.Player;
import Model.GameModel;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Weapon;
import Model.Items.WearableItem;
import Model.Map.Map;
import Model.Map.MapSize;
import Model.Terrains.TerrainType;
import View.Game.GameMainWindow;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * controller class that contains methods to transform values from engine GUI to model class
 * @author honzuna
 */
public class EngineController {

    private final GameModel model;
    private GameController controller;

    /**
     * controller needs a model instance
     * @param model
     */
    public EngineController(GameModel model) {
	this.model = model;
    }

    /**
     * add given map to model
     * @param map
     */
    public void createNewMap(Map map) {
	model.setMap(map);
    }

    /**
     * create new instance of player and add it to model
     * @param name
     * @param health
     * @param armor
     * @param damage
     * @param moves
     * @param speed
     * @param baseInventorySpace
     */
    public void createNewPlayer(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace) {
	Player player = new Player(name, health, armor, damage, moves, speed, baseInventorySpace);
	model.setPlayer(player);
    }

    /**
     * create new boss and add it to the model with coordinates at origin
     * @param name
     * @param health
     * @param armor
     * @param damage
     */
    public void createBoss(String name, int health, int armor, int damage) {
	int x = 0;
	int y = 0;
	Beast boss = new Beast(name, health, armor, damage, x, y);
	model.setBoss(boss);
    }

    /**
     * creates gear class and add it to model starting items list
     * @param name
     * @param armor
     * @param durability
     * @param et
     * @throws WrongEquipmentTypeForWearableItemException
     */
    public void createPieceOfGear(String name, int armor, int durability, EquipmentType et) throws WrongEquipmentTypeForWearableItemException {
	Gear gear = new Gear(name, 1, armor, durability, et);
	model.getStartingItems().add(gear);
    }

    /**
     * creates weapon class and add it to model starting items list
     * @param name
     * @param damage
     * @param durability
     * @param et
     * @throws WrongEquipmentTypeForWearableItemException
     */
    public void createWeapon(String name, int damage, int durability, EquipmentType et) throws WrongEquipmentTypeForWearableItemException {
	Weapon weapon = new Weapon(name, 1, damage, 0, durability, et);
	model.getStartingItems().add(weapon);
    }

    /**
     * iterates through model starting items list and add these items to the player instance equipment attribute
     */
    public void equipItems() {
	List<WearableItem> items = model.getStartingItems();
	// item has to be stored to be able to equip
	for (WearableItem i : items) {
	    if (i.isStored()) {
		continue;
	    }
	    try {
		model.getPlayer().storeToInventory(i);
	    } catch (NotEnoughInventoryFreeSpaceException | ItemStoredYetException ex) {
		Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    try {
		model.getPlayer().equip(i);
	    } catch (ItemNotStoredException | ItemEquipedYetException | NotEnoughInventoryFreeSpaceException | ItemNotEquipedException | ItemStoredYetException ex) {
		Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    /**
     * returns model instance
     * @return
     */
    public GameModel getModel() {
	return model;
    }

    /**
     * open new window where the game is runnig
     */
    public void runGame() {
	try {
	    controller = new GameController(model);
	    JFrame f = new GameMainWindow("PJV Game", controller, model.getPlayer());
	} catch (HeadlessException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * checks it the player coordinates lies inside the game board
     * @param map
     * @param x
     * @param y
     * @return
     */
    public boolean hasPlayerCorrectCoordinates(Map map, int x, int y) {
	// recount the x, y coordinates to the small game fields (of icon size) to check if there is a solid terrain or not
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;
	// get terrain type of the specific small field
	TerrainType terrainType = map.getMapTerrain().get(sy).get(sx).getType();
	if (terrainType.getName().equals("wall") || terrainType.getName().equals("rock")) {
	    return false;
	}
	return true;
    }

    /**
     * return the allowed beast count, preventing too much beasts in the game
     * @param map
     * @return
     */
    public int mapFreeSpaceForBeasts(Map map) {
	int size = map.getMapHeight() * map.getMapWidth();
	// iterate through the whole map fields and decrease the map size for each solid terrain
	for (int h = 0; h < map.getMapHeight(); h++) {
	    for (int w = 0; w < map.getMapWidth(); w++) {
		if ("wall".equals(map.getMapTerrain().get(h).get(w).getType().getName()) || "rock".equals(map.getMapTerrain().get(h).get(w).getType().getName())) {
		    size--;
		}
	    }
	}
	// return 1/10 of the free size that could be fill by the beasts
	return (int) (size / 10);
    }

    /**
     * checks if the beast has correct coordinates, that means if the beast's coordinates lies inside the game board
     * @param x
     * @param y
     * @param map
     * @return
     */
    public boolean hasBeastCorrectCoordinates(int x, int y, Map map) {
	// recount the x, y coordinates to get the small field coordinate
	int sx = (x - x % 40) / 40;
	int sy = (y - y % 40) / 40;
	// get terrain type at these 'new' coordinates
	TerrainType terrainType = map.getMapTerrain().get(sy).get(sx).getType();
	// if there is a solid terrain or another figure (even in the neighbourhood), return false
	if ("wall".equals(terrainType.getName()) || "rock".equals(terrainType.getName())) {
	    return false;
	}
	if (map.getMapFigures().get(sy).get(sx) != null) {
	    return false;
	}
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		try {
		    Figure fig = map.getMapFigures().get(sy - 1 + i).get(sx - 1 + j);
		    if (fig != null && fig.getName().equals("player")) {
			return false;
		    }
		} catch (IndexOutOfBoundsException iobe) {
		}
	    }
	}
	return true;
    }

    /**
     * creates num bat instances and add them to the model bats list
     * @param h
     * @param a
     * @param d
     * @param map
     * @param num
     */
    public void createBat(int h, int a, int d, Map map, int num) {
	int c = 0;
	int mapSize = mapFreeSpaceForBeasts(map);
	// iterate through the allowed count and size and create a bat with random allowed coordinates
	while (c < num && c < mapSize) {
	    Random rand = new Random();
	    int x = rand.nextInt(MapSize.getSIZE().getWidth() - 8);
	    int y = rand.nextInt(MapSize.getSIZE().getHeight() - 30);
	    if (hasBeastCorrectCoordinates(x, y, map)) {
		Beast bst = new Beast("bat", h, a, d, x, y);
		model.getBats().add(bst);
		c++;
	    }
	}
    }

    /**
     * creates num skeleton instances and add them to the model skeletons list
     * @param h
     * @param a
     * @param d
     * @param map
     * @param num
     */
    public void createSkeleton(int h, int a, int d, Map map, int num) {
	int c = 0;
	int mapSize = mapFreeSpaceForBeasts(map);
	// iterate through the allowed count and size and create a skeleton with random allowed coordinates
	while (c < num && c < mapSize) {
	    Random rand = new Random();
	    int x = rand.nextInt(MapSize.getSIZE().getWidth() - 8);
	    int y = rand.nextInt(MapSize.getSIZE().getHeight() - 30);
	    if (hasBeastCorrectCoordinates(x, y, map)) {
		Beast bst = new Beast("bat", h, a, d, x, y);
		model.getSkeletons().add(bst);
		c++;
	    }
	}
    }
}
