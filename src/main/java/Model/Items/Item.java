package Model.Items;

/**
 * item class
 * @author honzuna
 */
public class Item {

    private final String name;
    private final int weight;
    private boolean stored;

    /**
     *
     * @param name
     * @param weight
     */
    public Item(String name, int weight) {
	if (weight < 1) {
	    throw new IllegalArgumentException("Weight must be a positive number");
	}
	if (name.length() < 1) {
	    throw new IllegalArgumentException("Name must have at least one character");
	}
	this.weight = weight;
	this.name = name;
	this.stored = false;
    }

    /**
     *
     * @return
     */
    public int getWeight() {
	return weight;
    }

    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     *
     * @return
     */
    public boolean isStored() {
	return stored;
    }

    /**
     *
     * @param stored
     */
    public void setStored(boolean stored) {
	this.stored = stored;
    }
}
