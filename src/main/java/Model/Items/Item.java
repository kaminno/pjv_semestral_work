package Model.Items;

public class Item {

    private final String name;
    private final int weight;
    private boolean stored;

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

    public int getWeight() {
	return weight;
    }

    public String getName() {
	return name;
    }

    public boolean isStored() {
	return stored;
    }

    public void setStored(boolean stored) {
	this.stored = stored;
    }
}
