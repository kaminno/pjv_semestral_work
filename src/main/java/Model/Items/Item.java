package Model.Items;

public class Item {
    private final String name;
    private final int weight;
    private boolean stored;
    
    public Item(String name, int weight){
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
    
    public boolean isStored(){
	return stored;
    }
    
    public void setStored(boolean stored){
	this.stored = stored;
    }
}
