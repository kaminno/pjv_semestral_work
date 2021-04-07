package Model.Figures;


import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Model.Items.Equipment;
import Model.Items.EquipmentType;
import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.WearableItem;
import java.util.EnumMap;

/**
 *
 * @author honzuna
 */
public class Player extends Figure{
    private final int baseDamage;
    private final int baseArmor;
    private final int baseMoves;
    private final int baseSpeed;
    private int currentDamage;
    private int currentArmor;
    private int currentMoves;
    private int currentSpeed;
    private int loadWeight = 0;
    private Equipment equipment;
    private Inventory inventory;

    /**
     *
     * @param name
     * @param health
     * @param armor
     * @param damage
     * @param moves
     * @param speed
     * @param baseInventorySpace
     */
    public Player(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace) {
	super(name, health);
	if (armor >= 0 && moves > 0 && speed > 0 && health > 0){
	    this.baseDamage = damage;
	    this.currentDamage = damage;
	    this.baseArmor = armor;
	    this.currentArmor = armor;
	    this.baseMoves = moves;
	    this.currentMoves = moves;
	    this.baseSpeed = speed;
	    this.currentSpeed = speed;
	    this.equipment = new Equipment();
	    this.inventory = new Inventory(baseInventorySpace);
	}
	else{
	    throw new IllegalArgumentException("Armor and durability must be a positive number!");
	}
    }

    public EnumMap<EquipmentType, WearableItem> getEquipment() {
	return equipment.getEquipment();
    }
    
    public void equip(WearableItem item) throws ItemNotStoredException, ItemEquipedYetException, NotEnoughInventoryFreeSpaceException, ItemNotEquipedException, ItemStoredYetException{
	if (item.isStored()){
	    if (equipment.getPieceOfEquipment(item.getType()) == null){
		this.removeFromInventory(item);
		equipment.add(item);
		loadWeight += item.getWeight();
	    }
	    else{
		this.removeFromInventory(item);
		WearableItem newItem = equipment.getPieceOfEquipment(item.getType());
		this.storeToInventory(newItem);
		this.unequip(newItem);
		equipment.add(item);
		loadWeight += item.getWeight();
	    }
	}
	else{
	    throw new ItemNotStoredException("Can not equip item that is not in inventory!");
	}
    }
    
    public void unequip(WearableItem item) throws ItemNotEquipedException, NotEnoughInventoryFreeSpaceException, ItemStoredYetException{
	if(item.isEquiped()){
	    this.storeToInventory(item);
	    equipment.remove(item);
	    loadWeight -= item.getWeight();
	}
	else{
	    throw new ItemNotEquipedException("Can not unequip item that is not equiped!");
	}
    }
    
    public void storeToInventory(Item item) throws NotEnoughInventoryFreeSpaceException, ItemStoredYetException{
	inventory.storeItem(item);
	loadWeight += item.getWeight();
    }
    
    public void removeFromInventory(Item item) throws ItemNotStoredException {
	inventory.removeItem(item);
        loadWeight -= item.getWeight();
    }
}
