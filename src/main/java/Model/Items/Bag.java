package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

/**
 * bag class contains new free space for player's inventory
 * @author honzuna
 */
public class Bag extends WearableItem {

    private int size;

    /**
     *
     * @param name
     * @param weight
     * @param size
     * @param type
     * @throws WrongEquipmentTypeForWearableItemException
     */
    public Bag(String name, int weight, int size, EquipmentType type) throws WrongEquipmentTypeForWearableItemException {
	super(name, weight, type);
	boolean ok = false;
	// size must be a positive number
	if (size > 0) {
	    for (BagType b : BagType.values()) {
		if (b.label.equals(type.label)) {
		    this.size = size;
		    ok = true;
		    break;
		}
	    }
	    if (!ok) {
		throw new WrongEquipmentTypeForWearableItemException("Wrong equipment type: expect BagType-like, but get another!");
	    }
	} else {
	    throw new IllegalArgumentException("Bag size must be a positive number!");
	}
    }

    /**
     *
     * @return
     */
    public int getSize() {
	return size;
    }
}
