package Model.Items;

import Exceptions.ItemNotUsableNowException;

public class UsableItem extends Item{
    private final int totalUseNumber;
    private int currentUseNumber;
    private boolean usable;

    public UsableItem(String name, int weight, int totalUseNumber) {
	super(name, weight);
	if (totalUseNumber < 1){
	    throw new IllegalArgumentException("Number of use must be a positive number");
	}
	this.totalUseNumber = totalUseNumber;
	this.currentUseNumber = totalUseNumber;
	this.usable = true;
    }
    
    public void use() throws ItemNotUsableNowException{
	if (usable){
	    currentUseNumber -= 1;
	    if (currentUseNumber == 0){
		usable = false;
	    }
	}
	else{
	    throw new ItemNotUsableNowException("Number of uses is 0");
	}
    }

    public int getTotalUseNumber() {
	return totalUseNumber;
    }

    public int getCurrentUseNumber() {
	return currentUseNumber;
    }

    public boolean isUsable() {
	return usable;
    }
    
}
