package Controller;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Exceptions.WrongEquipmentTypeForWearableItemException;
import Model.Figures.Beast;
import Model.Figures.Player;
import Model.GameModel;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Item;
import Model.Items.Weapon;
import Model.Items.WearableItem;
import Model.Map.Map;
import View.Engine.EngineMainWindow;
import View.Game.GameMainWindow;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
}
