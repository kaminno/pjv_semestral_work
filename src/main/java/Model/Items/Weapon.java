package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

/**
 * weapon class
 * @author honzuna
 */
public class Weapon extends WearableItem {

    private int damage;
    private int bonusMoves;
    private int maximumDurability;
    private int currentDurability;
    private boolean broken;

    /**
     *
     * @param name
     * @param weight
     * @param damage
     * @param bonusMoves
     * @param durability
     * @param type
     * @throws WrongEquipmentTypeForWearableItemException
     */
    public Weapon(String name, int weight, int damage, int bonusMoves, int durability, EquipmentType type) throws WrongEquipmentTypeForWearableItemException {
	super(name, weight, type);
	boolean ok = false;
	// check if the equipment type is really weapon and if the constructor values are correct
	for (WeaponType w : WeaponType.values()) {
	    if (w.label.equals(type.label)) {
		if (damage > 0 && durability >= 0) {
		    this.damage = damage;
		    this.broken = false;
		    this.maximumDurability = durability;
		    this.currentDurability = durability;
		    if (this.maximumDurability == 0) {
			this.broken = true;
		    }
		} else {
		    throw new IllegalArgumentException("Damage and durability must be a positive number!");
		}
		this.bonusMoves = bonusMoves;
		ok = true;
		break;
	    }
	}
	if (!ok) {
	    throw new WrongEquipmentTypeForWearableItemException("Wrong equipment type: expect WeaponType-like, but get another!");
	}
    }

    /**
     *
     * @return
     */
    public int getDamage() {
	return damage;
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
    public boolean isBroken() {
	return broken;
    }

    /**
     *
     * @return
     */
    public int getMaximumDurability() {
	return maximumDurability;
    }

    /**
     *
     * @return
     */
    public int getCurrentDurability() {
	return currentDurability;
    }

    /**
     * update durability value
     * @param coef
     */
    public void updateDurability(int coef) {
	currentDurability += coef;
	if (currentDurability == 0) {
	    this.broken = true;
	}
    }

    /**
     * set the durability value and checks if the weapon is broken or not
     * @param durability
     */
    public void setDurability(int durability) {
	if (durability < 0) {
	    throw new IllegalArgumentException("Durability must be positive");
	} else {
	    this.broken = false;
	    if (durability == 0) {
		this.broken = true;
	    }
	    this.maximumDurability = durability;
	}
    }
}
