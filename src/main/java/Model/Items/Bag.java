package Model.Items;

public class Bag extends WearableItem {
    private int size;

    public Bag(String name, int weight, int size, EquipmentType type) {
	super(name, weight, type);
	for (BagType b : BagType.values()){
	    if (b.label.equals(type.label)){
		this.size = size;
		break;
	    }
	}
    }
    
    public int getSize() {
	return size;
    }
}
