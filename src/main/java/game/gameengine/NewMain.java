package game.gameengine;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Exceptions.WrongEquipmentTypeForWearableItemException;
import Exceptions.WrongTerrainType;
import Model.Figures.Player;
import Model.Items.Bag;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.Weapon;
import Model.Map.Map;
import Model.Terrains.Ground;
import Model.Terrains.TerrainType;
import java.util.ArrayList;

public class NewMain {

    public static void main(String[] args) throws NotEnoughInventoryFreeSpaceException, ItemNotStoredException, ItemEquipedYetException, ItemNotEquipedException, ItemStoredYetException, WrongEquipmentTypeForWearableItemException, WrongTerrainType {
	
	Item i = new Item("huh", 3);
	Weapon w = new Weapon("Meč", 7, 3, 0, 5, EquipmentType.MAIN_HAND);
	Bag b = new Bag("batůžek", 4, 5, EquipmentType.BAG);
	Gear g = new Gear("helma", 14, 3, 4, EquipmentType.HEAD);
	Gear l = new Gear("kaťata", 14, 3, 4, EquipmentType.LEGS);
	//Gear o = new Gear("kaťata", 14, 3, 4, EquipmentType.BAG);
	Player p = new Player("Honza", 3, 5, 2, 1, 20, 5);
	p.storeToInventory(g);
	p.storeToInventory(w);
	p.storeToInventory(l);
	p.storeToInventory(b);
	System.out.println(g.getArmor() + " " + g.getDurability() + " " + g.getName() + " " + g.isBroken() + " " + g.getType());
	g.setDurability(0);
	System.out.println(g.getArmor() + " " + g.getDurability() + " " + g.getName() + " " + g.isBroken());
	System.out.println("Item: " + i.getName() + " and weight: " + i.getWeight());
	System.out.println("Player: " + p.getEquipment());
	System.out.println("Player: " + p.getEquipment().keySet());
	System.out.println("Player: " + p.getEquipment().values());
	p.equip(g);
	p.equip(w);
	p.equip(l);
	p.equip(b);
	System.out.println("Player: " + p.getEquipment().values());
	System.out.println("player: " + ((Gear)p.getEquipment().get(EquipmentType.LEGS)).getName());
	System.out.println(p.getEquipment().get(EquipmentType.CHEST) instanceof Gear);
	System.out.println(g.getType());
	
	Map map = new Map(5, 7);
	for (int j = 0; j < 5; j++){
	    for (int c = 0; c < 7; c++){
		if (c + j == 4 || c + j == 10){
		    continue;
		}
		map.addTerrain(j, c, new Ground("Tráva", TerrainType.GRASS, 0, 0));
	    }
	}
	map.addFigure(3, 4, p);
	map.addFigure(4, 6, p);
	
	map.printMapTerrain();
	map.printMapFigures();
	
	map.fillTheMapWithGround("Ananas", TerrainType.SAND, 0, 0);
	
	map.printMapTerrain();
	
//	ArrayList<String>[] list = new ArrayList[6];
//	for(int j = 0; j < 6; j++){
//	    for (int c = 0; c < 6; c++)
//	    {
//		list[i][c] = "";
//	    }
//	    
//	}
    }
}
