package game.gameengine;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Model.Figures.Player;
import Model.Items.Bag;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.Weapon;

public class NewMain {

    public static void main(String[] args) throws NotEnoughInventoryFreeSpaceException, ItemNotStoredException, ItemEquipedYetException, ItemNotEquipedException, ItemStoredYetException {
	
	Item i = new Item("huh", 3);
	Weapon w = new Weapon("Meč", 7, 3, 0, 5, EquipmentType.MAIN_HAND);
	Bag b = new Bag("batůžek", 4, 5, EquipmentType.BAG);
	Gear g = new Gear("helma", 14, 3, 4, EquipmentType.HEAD);
	Gear l = new Gear("kaťata", 14, 3, 4, EquipmentType.LEGS);
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
    }
}
