package Model.Items;

public class Gear extends WearableItem{
    private int armor;
    private int durability;
    private boolean broken;
    
    public Gear(String name, int weight, int armor, int durability, EquipmentType type){
	super(name, weight, type);
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
		break;
	    }
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
	try{
	    if (durability < 0){
		throw new IllegalArgumentException();
	    }
	    else{
		this.broken = false;
		if(durability == 0){
		    this.broken = true;
		}
		this.durability = durability;
	    }
	}
	catch (IllegalArgumentException e){
	    System.out.println("Durability must be a positive number!");
	}
    }
}
