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
import Model.Items.Item;
import Model.Items.Weapon;
import Model.Items.WearableItem;
import Model.Map.Map;
import Model.Map.MapSize;
import Model.Terrains.SolidType;
import Model.Terrains.TerrainType;
import View.Engine.EngineMainWindow;
import View.Game.GameMainWindow;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class EngineController {
    private GameModel model;
    private GameController controller;

    public EngineController(GameModel model) {
	this.model = model;
    }
    
    public void createNewMap(Map map){
	model.setMap(map);
    }
    
    public void createNewPlayer(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace){
	Player player = new Player(name, health, armor, damage, moves, speed, baseInventorySpace);
	model.setPlayer(player);
    }
    
    public void createBoss(String name, int health, int armor, int damage){
	int x = 0;
	int y = 0;
	Beast boss = new Beast(name, health, armor, damage, x, y);
	model.setBoss(boss);
    }
    
    public void createPieceOfGear(String name, int armor, int durability, EquipmentType et) throws WrongEquipmentTypeForWearableItemException{
	Gear gear = new Gear(name, 1, armor, durability, et);
	model.getStartingItems().add(gear);
    }
    
    public void createWeapon(String name, int damage, int durability, EquipmentType et) throws WrongEquipmentTypeForWearableItemException{
	Weapon weapon = new Weapon(name, 1, damage, 0, durability, et);
	model.getStartingItems().add(weapon);	
    }
    
    public void equipItems() {
	List<WearableItem> items = model.getStartingItems();
	for(WearableItem i : items){
	    if(i.isStored()){
		System.out.println("Stored: " + i.getName());
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
	    System.out.println("Item: " + i.toString());
	}
    }

    public GameModel getModel() {
	return model;
    }
    
    public void runGame(){
	try {
	    controller = new GameController(model);
	    JFrame f = new GameMainWindow("Game", controller, model.getPlayer());
	} catch (HeadlessException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(EngineController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public boolean hasPlayerCorrectCoordinates(Map map, int x, int y){
	int sx = (x - x%40)/40;
	int sy = (y - y%40)/40;
	TerrainType terrainType = map.getMapTerrain().get(sy).get(sx).getType();
	if(terrainType.getName() ==  "wall" || terrainType.getName() == "rock"){
	    return false;
	}
	return true;
    }
    
    public int mapFreeSpaceForBeasts(Map map){
	int size = map.getMapHeight() * map.getMapWidth();
	for(int h = 0; h < map.getMapHeight(); h++){
	    for(int w = 0; w < map.getMapWidth(); w++){
		if(map.getMapTerrain().get(h).get(w).getType().getName() == "wall" || map.getMapTerrain().get(h).get(w).getType().getName() == "rock"){
		    size--;
		}
	    }
	}
	return (int)(size / 10);
    }
    
    public boolean hasBeastCorrectCoordinates(int x, int y, Map map){
	int sx = (x - x%40)/40;
	int sy = (y - y%40)/40;
	TerrainType terrainType = map.getMapTerrain().get(sy).get(sx).getType();
	if(terrainType.getName() ==  "wall" || terrainType.getName() == "rock"){
	    return false;
	}
	if(map.getMapFigures().get(sy).get(sx) != null){
	    return false;
	}
	for(int i = 0; i < 3; i++){
	    for(int j = 0; j < 3; j++){
		try{
		    Figure fig = map.getMapFigures().get(sy-1+i).get(sx-1+j);
		    if(fig != null && fig.getName().equals("player")){
			return false;
		    }
		}catch(IndexOutOfBoundsException iobe){
		    continue;
		}
	    }
	}
	return true;
    }
    
    public void createBat(int h, int a, int d, Map map, int num){
	int c = 0;
	int mapSize = mapFreeSpaceForBeasts(map);
	while(c < num && c < mapSize){
	    Random rand = new Random();
	    int x = rand.nextInt(MapSize.getSIZE().getWidth()-8);
	    int y = rand.nextInt(MapSize.getSIZE().getHeight()-30);
	    if(hasBeastCorrectCoordinates(x, y, map)){
		Beast bst = new Beast("bat", h, a, d, x, y);
		model.getBats().add(bst);
		c++;
	    }
	}
    }
    
    public void createSkeleton(int h, int a, int d, Map map, int num){
	int c = 0;
	int mapSize = mapFreeSpaceForBeasts(map);
	while(c < num && c < mapSize){
	    Random rand = new Random();
	    int x = rand.nextInt(MapSize.getSIZE().getWidth()-8);
	    int y = rand.nextInt(MapSize.getSIZE().getHeight()-30);
	    if(hasBeastCorrectCoordinates(x, y, map)){
		Beast bst = new Beast("bat", h, a, d, x, y);
		model.getSkeletons().add(bst);
		c++;
	    }
	}
    }
}
