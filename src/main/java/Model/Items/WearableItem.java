package Model.Items;

public class WearableItem extends Item {
    private final EquipmentType type;
    private boolean equiped = false;
    
    public WearableItem(String name, int weight, EquipmentType type){
	super(name, weight);
	this.type = type;
    }
    
    public boolean isEquiped(){
	return equiped;
    }

    public void setEquiped(boolean equiped) {
	this.equiped = equiped;
    }

    public EquipmentType getType() {
	return type;
    }
}
