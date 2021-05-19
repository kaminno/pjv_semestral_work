package Model.Items;

import Exceptions.ItemNotUsableNowException;

/**
 * usable item class
 * @author honzuna
 */
public class UsableItem extends Item {

    private final int totalUseNumber;
    private int currentUseNumber;
    private boolean usable;

    /**
     *
     * @param name
     * @param weight
     * @param totalUseNumber
     */
    public UsableItem(String name, int weight, int totalUseNumber) {
	super(name, weight);
	if (totalUseNumber < 1) {
	    throw new IllegalArgumentException("Number of use must be a positive number");
	}
	this.totalUseNumber = totalUseNumber;
	this.currentUseNumber = totalUseNumber;
	this.usable = true;
    }

    /**
     * count the item usable number
     * @throws ItemNotUsableNowException
     */
    public void use() throws ItemNotUsableNowException {
	if (usable) {
	    currentUseNumber -= 1;
	    if (currentUseNumber == 0) {
		usable = false;
	    }
	} else {
	    throw new ItemNotUsableNowException("Number of uses is 0");
	}
    }

    /**
     *
     * @return
     */
    public int getTotalUseNumber() {
	return totalUseNumber;
    }

    /**
     *
     * @return
     */
    public int getCurrentUseNumber() {
	return currentUseNumber;
    }

    /**
     *
     * @return
     */
    public boolean isUsable() {
	return usable;
    }

}
