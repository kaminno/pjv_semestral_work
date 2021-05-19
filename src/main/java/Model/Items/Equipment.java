package Model.Items;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import java.util.EnumMap;

/**
 * Equipment class contains player's equipment
 * @author honzuna
 */
public class Equipment {

    private final EnumMap<EquipmentType, WearableItem> equipment = new EnumMap(EquipmentType.class);

    /**
     *
     */
    public Equipment() {
	for (EquipmentType e : EquipmentType.values()) {
	    equipment.put(e, null);
	}
    }

    /**
     *
     * @return
     */
    public EnumMap<EquipmentType, WearableItem> getEquipment() {
	return equipment;
    }

    /**
     * get specific item from equipment
     * @param position
     * @return
     */
    public WearableItem getPieceOfEquipment(EquipmentType position) {
	return equipment.get(position);
    }

    /**
     * add new item instance to the equipment map on specivic position
     * @param item
     * @throws ItemEquipedYetException
     */
    public void add(WearableItem item) throws ItemEquipedYetException {
	// only unequiped items can be equiped
	if (!item.isEquiped()) {
	    equipment.put(item.getType(), item);
	    item.setEquiped(true);
	} else {
	    throw new ItemEquipedYetException("Item is equiped yet!");
	}
    }

    /**
     * remove item from equipment map
     * @param item
     * @throws ItemNotEquipedException
     */
    public void remove(WearableItem item) throws ItemNotEquipedException {
	// only equiped items can be unequiped
	if (item.isEquiped()) {
	    equipment.put(item.getType(), null);
	    item.setEquiped(false);
	} else {
	    throw new ItemNotEquipedException("Can not remove item that is not equiped!");
	}
    }
}
