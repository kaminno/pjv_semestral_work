package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

public class Bag extends WearableItem {

    private int size;

    public Bag(String name, int weight, int size, EquipmentType type) throws WrongEquipmentTypeForWearableItemException {
	super(name, weight, type);
	boolean ok = false;
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

    public int getSize() {
	return size;
    }
}
