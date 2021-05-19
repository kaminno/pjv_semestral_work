package Model.Figures;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import Model.Items.Bag;
import Model.Items.Equipment;
import Model.Items.EquipmentType;
import Model.Items.Gear;
import Model.Items.Inventory;
import Model.Items.Item;
import Model.Items.Weapon;
import Model.Items.WearableItem;
import java.util.EnumMap;

/**
 * player class
 * @author honzuna
 */
public class Player extends Figure {

    private final int baseDamage;
    private final int baseArmor;
    private final int baseMoves;
    private final int baseSpeed;
    private int currentDamage;
    private int currentArmor;
    private int currentMoves;
    private int currentSpeed;
    private int bonusDamage = 0;
    private int bonusArmor = 0;
    private int bonusMoves = 0;
    private int bonusSpeed = 0;
    private int loadWeight = 0;
    private Equipment equipment;
    private Inventory inventory;
    private int xPosition;
    private int yPosition;

    /**
     *
     * @param name
     * @param i
     * @param damage
     * @param i2
     * @param i3
     * @param i1
     * @param i5
     * @param i4
     */
    public Player(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace) {
	super(name, health);
	// sets the starting values if they are correct
	if (armor >= 0 && damage > 0 && moves > 0 && speed > 0 && health > 0) {
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
	} else {
	    throw new IllegalArgumentException("Armor, damage, moves and speed must be a positive number!");
	}
    }

    /**
     *
     * @return
     */
    public EnumMap<EquipmentType, WearableItem> getEquipment() {
	return equipment.getEquipment();
    }

    /**
     * if the given item is in inventory and is wearable, put it in equipment data structure
     * @param item
     * @throws ItemNotStoredException
     * @throws ItemEquipedYetException
     * @throws NotEnoughInventoryFreeSpaceException
     * @throws ItemNotEquipedException
     * @throws ItemStoredYetException
     */
    public void equip(WearableItem item) throws ItemNotStoredException, ItemEquipedYetException, NotEnoughInventoryFreeSpaceException, ItemNotEquipedException, ItemStoredYetException {
	// item must be in inventory to be equiped
	if (item.isStored()) {
	    // if there is no equiped item in corresponding slot
	    if (equipment.getPieceOfEquipment(item.getType()) == null) {
		// update inventory capacity and add item
		if (item instanceof Bag) {
		    inventory.updateCapacity(((Bag) item).getSize());
		}
		this.removeFromInventory(item);
		equipment.add(item);
		loadWeight += item.getWeight();
	    } else {
		// update inventory and swap inventory item with the equiped one
		this.removeFromInventory(item);
		WearableItem newItem = equipment.getPieceOfEquipment(item.getType());
		if (item instanceof Bag) {
		    inventory.updateCapacity(-((Bag) item).getSize());
		    inventory.updateCapacity(((Bag) item).getSize());
		}
		this.storeToInventory(newItem);
		this.unequip(newItem);
		equipment.add(item);
		loadWeight += item.getWeight();
	    }
	    updateStats();
	} else {
	    throw new ItemNotStoredException("Can not equip item that is not in inventory!");
	}
    }

    /**
     * removes item from equipment data structure and add it to the inventory structure
     * @param item
     * @throws ItemNotEquipedException
     * @throws NotEnoughInventoryFreeSpaceException
     * @throws ItemStoredYetException
     */
    public void unequip(WearableItem item) throws ItemNotEquipedException, NotEnoughInventoryFreeSpaceException, ItemStoredYetException {
	// item must be equiped if it would be unequip
	if (item.isEquiped()) {
	    if (item instanceof Bag) {
		inventory.updateCapacity(-((Bag) item).getSize());
	    }
	    this.storeToInventory(item);
	    equipment.remove(item);
	    loadWeight -= item.getWeight();
	    updateStats();
	} else {
	    throw new ItemNotEquipedException("Can not unequip item that is not equiped!");
	}
    }

    /**
     * @param item
     * @throws NotEnoughInventoryFreeSpaceException
     * @throws ItemStoredYetException
     */
    public void storeToInventory(Item item) throws NotEnoughInventoryFreeSpaceException, ItemStoredYetException {
	inventory.storeItem(item);
	loadWeight += item.getWeight();
    }

    /**
     * remove item from inventory
     * @param item
     * @throws ItemNotStoredException
     */
    public void removeFromInventory(Item item) throws ItemNotStoredException {
	inventory.removeItem(item);
	loadWeight -= item.getWeight();
    }

    private void updateStats() {
	currentArmor = baseArmor + bonusArmor;
	currentDamage = baseDamage + bonusDamage;
	currentMoves = baseMoves + bonusMoves;
	currentSpeed = baseSpeed + bonusSpeed;
	for (WearableItem i : equipment.getEquipment().values()) {
	    if (i instanceof Gear) {
		currentArmor += ((Gear) i).getArmor();
	    } else if (i instanceof Weapon) {
		currentDamage += ((Weapon) i).getDamage();
		currentMoves += ((Weapon) i).getBonusMoves();
	    }
	}
    }

    /**
     * call defend method on enemy
     * @param enemy
     */
    public void attack(Figure enemy) {
	enemy.defend(currentDamage);
    }

    /**
     * count injury from current armor and enemy damage and update current health
     * @param damage
     */
    public void defend(int damage) {
	int injury = damage - getCurrentArmor();
	if (injury < 0) {
	    injury = 0;
	}
	setCurrentHealth(getCurrentHealth() - injury);
	if (currentHealth < 0) {
	    currentHealth = 0;
	}
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
	return getCurrentHealth() > 0;
    }

    /**
     *
     * @return
     */
    public int getBaseArmor() {
	return baseArmor;
    }

    /**
     *
     * @return
     */
    public int getxPosition() {
	return xPosition;
    }

    /**
     *
     * @return
     */
    public int getBaseDamage() {
	return baseDamage;
    }

    /**
     *
     * @return
     */
    public int getBaseMoves() {
	return baseMoves;
    }

    /**
     *
     * @return
     */
    public int getBaseSpeed() {
	return baseSpeed;
    }

    /**
     *
     * @return
     */
    public int getBonusDamage() {
	return bonusDamage;
    }

    /**
     *
     * @return
     */
    public int getBonusArmor() {
	return bonusArmor;
    }

    /**
     *
     * @return
     */
    public int getBonusMoves() {
	return bonusMoves;
    }

    /**
     *
     * @return
     */
    public int getBonusSpeed() {
	return bonusSpeed;
    }

    /**
     *
     * @return
     */
    public int getCurrentDamage() {
	return currentDamage;
    }

    /**
     *
     * @return
     */
    public int getCurrentArmor() {
	return currentArmor;
    }

    /**
     *
     * @return
     */
    public int getCurrentMoves() {
	return currentMoves;
    }

    /**
     *
     * @return
     */
    public int getCurrentSpeed() {
	return currentSpeed;
    }

    /**
     *
     * @return
     */
    public int getyPosition() {
	return yPosition;
    }

    /**
     *
     * @return
     */
    public Inventory getInventory() {
	return inventory;
    }

    /**
     *
     * @return
     */
    public Equipment equipment() {
	return equipment;
    }

    /**
     *
     * @param bonusDamage
     */
    public void setBonusDamage(int bonusDamage) {
	this.bonusDamage = bonusDamage;
    }

    /**
     *
     * @param bonusArmor
     */
    public void setBonusArmor(int bonusArmor) {
	this.bonusArmor = bonusArmor;
    }

    /**
     *
     * @param bonusMoves
     */
    public void setBonusMoves(int bonusMoves) {
	this.bonusMoves = bonusMoves;
    }

    /**
     *
     * @param bonusSpeed
     */
    public void setBonusSpeed(int bonusSpeed) {
	this.bonusSpeed = bonusSpeed;
    }

    /**
     *
     * @param equipment
     */
    public void setEquipment(Equipment equipment) {
	this.equipment = equipment;
    }

    /**
     *
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    /**
     *
     * @param xPosition
     */
    public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
    }

    /**
     *
     * @param yPosition
     */
    public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
	return "Player{" + "baseHealth=" + maxHealth + ", baseDamage=" + baseDamage + ", baseArmor=" + baseArmor + ", baseMoves=" + baseMoves + ", baseSpeed=" + baseSpeed + '}';
    }
}
