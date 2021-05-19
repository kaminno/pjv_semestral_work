package Model.Items;

import Exceptions.ItemNotStoredException;
import Exceptions.ItemStoredYetException;
import Exceptions.NotEnoughInventoryFreeSpaceException;
import java.util.ArrayList;

/**
 * data structure to store player's items (item class instances)
 * @author honzuna
 */
public class Inventory {

    private final int baseCapacity;
    private int currentCapacity;
    private int freeSpace;
    private ArrayList<Item> items = new ArrayList();

    /**
     *
     * @param baseCapacity
     */
    public Inventory(int baseCapacity) {
	if (baseCapacity < 1) {
	    throw new IllegalArgumentException("Base capacity must be at least 1!");
	}
	this.baseCapacity = baseCapacity;
	this.currentCapacity = baseCapacity;
	this.freeSpace = baseCapacity;
    }

    /**
     * update capacity (enlarge the space for items)
     * @param bonusCapacity
     * @throws NotEnoughInventoryFreeSpaceException
     */
    public void updateCapacity(int bonusCapacity) throws NotEnoughInventoryFreeSpaceException {
	// capacity could not be smaller than the base value
	if (currentCapacity + bonusCapacity >= baseCapacity) {
	    // free space can not be negative
	    if (freeSpace + bonusCapacity < 0) {
		throw new NotEnoughInventoryFreeSpaceException("Remove some items before capacity changes!");
	    }
	    currentCapacity += bonusCapacity;
	    freeSpace += bonusCapacity;
	} else {
	    throw new IllegalArgumentException("To low capacity argument to change (changing base capacity): " + bonusCapacity + "!");
	}
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
	return items;
    }

    /**
     * adding item instance to the structure
     * @param item
     * @throws NotEnoughInventoryFreeSpaceException
     * @throws ItemStoredYetException
     */
    public void storeItem(Item item) throws NotEnoughInventoryFreeSpaceException, ItemStoredYetException {
	if (freeSpace == 0) {
	    throw new NotEnoughInventoryFreeSpaceException("Inventory is full");
	}
	if (item.isStored()) {
	    throw new ItemStoredYetException("Item stored yet!");
	}
	items.add(item);
	item.setStored(true);
	freeSpace -= 1;
    }

    //TODO getItem()

    /**
     * remove item from structure
     * @param item
     * @throws ItemNotStoredException
     */
    public void removeItem(Item item) throws ItemNotStoredException {
	if (item.isStored()) {
	    freeSpace += 1;
	    int i = 0;
	    for (; i < items.size(); i++) {
		if (items.get(i) == item) {
		    break;
		}
	    }
	    items.remove(i);
	    item.setStored(false);
	} else {
	    throw new ItemNotStoredException("Can not remove item that is not stored in your inventory!");
	}
    }

    /**
     *
     * @return
     */
    public int getCurrentCapacity() {
	return currentCapacity;
    }

    /**
     *
     * @return
     */
    public int getFreeSpace() {
	return freeSpace;
    }
}
