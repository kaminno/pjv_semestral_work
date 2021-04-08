package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

public class Gear extends WearableItem{
    private int armor;
    private int durability;
    private boolean broken;
    
    public Gear(String name, int weight, int armor, int durability, EquipmentType type) throws WrongEquipmentTypeForWearableItemException{
	super(name, weight, type);
	boolean ok = false;
	for (GearType g : GearType.values()){
	    if (g.label.equals(type.label)){
		if (armor > 0 && durability >= 0){
		    this.armor = armor;
		    this.broken = false;
		    this.durability = durability;
		    if (this.durability == 0){
			this.broken = true;
		    }
		}
		else{
		    throw new IllegalArgumentException("Armor and durability must be a positive number!");
		}
		ok = true;
		break;
	    }
	}
	if (!ok){
	    throw new WrongEquipmentTypeForWearableItemException("Wrong equipment type: expect GearType-like, but get another!");
	}
    }

    public int getArmor() {
	return armor;
    }

    public int getDurability() {
	return durability;
    }

    public boolean isBroken() {
	return broken;
    }

    public void setDurability(int durability){
	if (durability < 0){
	    throw new IllegalArgumentException("Negative durability");
	}
	else{
	    this.broken = false;
	    if(durability == 0){
	        this.broken = true;
	    }
	    this.durability = durability;
	}
    }
}
