package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

/**
 *
 * @author honzuna
 */
public class Gear extends WearableItem {

    private int armor;
    private int currentDurability;
    private int maximumDurability;
    private boolean broken;

    /**
     *
     * @param name
     * @param weight
     * @param armor
     * @param durability
     * @param type
     * @throws WrongEquipmentTypeForWearableItemException
     */
    public Gear(String name, int weight, int armor, int durability, EquipmentType type) throws WrongEquipmentTypeForWearableItemException {
	super(name, weight, type);
	boolean ok = false;
	// iterate thwough gear type enum to check if the equipment type is really type of gear
	for (GearType g : GearType.values()) {
	    if (g.label.equals(type.label)) {
		// checks values and sets basic stats
		if (armor > 0 && durability >= 0) {
		    this.armor = armor;
		    this.broken = false;
		    this.maximumDurability = durability;
		    this.currentDurability = durability;
		    if (this.maximumDurability == 0) {
			this.broken = true;
		    }
		} else {
		    throw new IllegalArgumentException("Armor and durability must be a positive number!");
		}
		ok = true;
		break;
	    }
	}
	if (!ok) {
	    throw new WrongEquipmentTypeForWearableItemException("Wrong equipment type: expect GearType-like, but get another!");
	}
    }

    /**
     *
     * @return
     */
    public int getArmor() {
	return armor;
    }

    /**
     *
     * @return
     */
    public boolean isBroken() {
	return broken;
    }

    /**
     * sets durability and check if the item is broken or not
     * @param durability
     */
    public void setDurability(int durability) {
	if (durability < 0) {
	    throw new IllegalArgumentException("Negative durability");
	} else {
	    this.broken = false;
	    if (durability == 0) {
		this.broken = true;
	    }
	    this.maximumDurability = durability;
	}
    }

    /**
     *
     * @return
     */
    public int getCurrentDurability() {
	return currentDurability;
    }

    /**
     *
     * @return
     */
    public int getMaximumDurability() {
	return maximumDurability;
    }

    /**
     * update durability attribute
     * @param coef
     */
    public void updateDurability(int coef) {
	currentDurability += coef;
	if (currentDurability == 0) {
	    this.broken = true;
	}
    }
}
