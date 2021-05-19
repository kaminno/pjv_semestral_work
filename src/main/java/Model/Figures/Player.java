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
     * @param health
     * @param armor
     * @param damage
     * @param moves
     * @param speed
     * @param baseInventorySpace
     */
    public Player(String name, int health, int armor, int damage, int moves, int speed, int baseInventorySpace) {
	super(name, health);
	if (armor >= 0 && damage > 0 && moves > 0 && speed > 0 && health > 0){
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
	    throw new IllegalArgumentException("Armor, damage, moves and speed must be a positive number!");
	}
    }

    public EnumMap<EquipmentType, WearableItem> getEquipment() {
	return equipment.getEquipment();
    }
    
    public void equip(WearableItem item) throws ItemNotStoredException, ItemEquipedYetException, NotEnoughInventoryFreeSpaceException, ItemNotEquipedException, ItemStoredYetException{
	if (item.isStored()){
	    if (equipment.getPieceOfEquipment(item.getType()) == null){
		if (item instanceof Bag){
		    inventory.updateCapacity(((Bag) item).getSize());
		}
		this.removeFromInventory(item);
		equipment.add(item);
		loadWeight += item.getWeight();
	    }
	    else{
		this.removeFromInventory(item);
		WearableItem newItem = equipment.getPieceOfEquipment(item.getType());
		if (item instanceof Bag){
		    inventory.updateCapacity(-((Bag) item).getSize());
		    inventory.updateCapacity(((Bag) item).getSize());
		}
		this.storeToInventory(newItem);
		this.unequip(newItem);
		equipment.add(item);
		loadWeight += item.getWeight();
	    }
	    updateStats();
	}
	else{
	    throw new ItemNotStoredException("Can not equip item that is not in inventory!");
	}
    }
    
    public void unequip(WearableItem item) throws ItemNotEquipedException, NotEnoughInventoryFreeSpaceException, ItemStoredYetException{
	if(item.isEquiped()){
	    if (item instanceof Bag){
		inventory.updateCapacity(-((Bag) item).getSize());
	    }
	    this.storeToInventory(item);
	    equipment.remove(item);
	    loadWeight -= item.getWeight();
	    updateStats();
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
    
    private void updateStats(){
	currentArmor = baseArmor + bonusArmor;
	currentDamage = baseDamage + bonusDamage;
	currentMoves = baseMoves + bonusMoves;
	currentSpeed = baseSpeed + bonusSpeed;
	for (WearableItem i : equipment.getEquipment().values()){
	    if (i instanceof Gear){
		currentArmor += ((Gear) i).getArmor();
	    }
	    else if (i instanceof Weapon){
		currentDamage += ((Weapon) i).getDamage();
		currentMoves += ((Weapon) i).getBonusMoves();
	    }
	}
    }
    
    public void attack(Figure enemy){
	enemy.defend(currentDamage);
    }
    
    public void defend(int damage){
	int injury = damage - getCurrentArmor();
	if(injury < 0){
	    injury = 0;
	}
	setCurrentHealth(getCurrentHealth() - injury);
	if(currentHealth < 0){
	    currentHealth = 0;
	}
    }
    
    public boolean isAlive(){
	return getCurrentHealth() > 0;
    }

    public int getBaseArmor() {
	return baseArmor;
    }

    public int getxPosition() {
	return xPosition;
    }

    public int getBaseDamage() {
	return baseDamage;
    }

    public int getBaseMoves() {
	return baseMoves;
    }

    public int getBaseSpeed() {
	return baseSpeed;
    }

    public int getBonusDamage() {
	return bonusDamage;
    }

    public int getBonusArmor() {
	return bonusArmor;
    }

    public int getBonusMoves() {
	return bonusMoves;
    }

    public int getBonusSpeed() {
	return bonusSpeed;
    }

    public int getCurrentDamage() {
	return currentDamage;
    }

    public int getCurrentArmor() {
	return currentArmor;
    }

    public int getCurrentMoves() {
	return currentMoves;
    }

    public int getCurrentSpeed() {
	return currentSpeed;
    }

    public int getyPosition() {
	return yPosition;
    }

    public Inventory getInventory() {
	return inventory;
    }
    
    public Equipment equipment(){
	return equipment;
    }

    public void setBonusDamage(int bonusDamage) {
	this.bonusDamage = bonusDamage;
    }

    public void setBonusArmor(int bonusArmor) {
	this.bonusArmor = bonusArmor;
    }

    public void setBonusMoves(int bonusMoves) {
	this.bonusMoves = bonusMoves;
    }

    public void setBonusSpeed(int bonusSpeed) {
	this.bonusSpeed = bonusSpeed;
    }

    public void setEquipment(Equipment equipment) {
	this.equipment = equipment;
    }

    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
    }

    @Override
    public String toString() {
	return "Player{" + "baseHealth=" + maxHealth  + ", baseDamage=" + baseDamage + ", baseArmor=" + baseArmor + ", baseMoves=" + baseMoves + ", baseSpeed=" + baseSpeed + '}';
    }
}
