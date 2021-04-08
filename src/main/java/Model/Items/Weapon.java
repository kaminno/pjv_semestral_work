package Model.Items;

import Exceptions.WrongEquipmentTypeForWearableItemException;

public class Weapon extends WearableItem{
    //private final WeaponType type;
    private int damage;
    private int bonusMoves;
    private int durability;
    private boolean broken;

    public Weapon(String name, int weight, int damage, int bonusMoves, int durability, EquipmentType type) throws WrongEquipmentTypeForWearableItemException {
	super(name, weight, type);
	boolean ok = false;
	for (WeaponType w : WeaponType.values()){
	    if (w.label.equals(type.label)){
		if (damage > 0 && durability >= 0){
		    this.damage = damage;
		    this.broken = false;
		    this.durability = durability;
		    if (this.durability == 0){
			this.broken = true;
		    }
		}
		else{
		    throw new IllegalArgumentException("Damage and durability must be a positive number!");
		}
		this.bonusMoves = bonusMoves;
		ok = true;
		break;
	    }
	}
	if (!ok){
	    throw new WrongEquipmentTypeForWearableItemException("Wrong equipment type: expect WeaponType-like, but get another!");
	}
    }

    public int getDamage() {
	return damage;
    }

    public int getBonusMoves() {
	return bonusMoves;
    }

    public int getDurability() {
	return durability;
    }

    public boolean isBroken() {
	return broken;
    }
    
    public void setDurability(int durability){
	//try{
	    if (durability < 0){
		throw new IllegalArgumentException("Durability must be positive");
	    }
	    else{
		this.broken = false;
		if(durability == 0){
		    this.broken = true;
		}
		this.durability = durability;
	    }
//	}
//	catch (IllegalArgumentException e){
//	    System.out.println("Durability must be a positive number!");
//	}
    }
    
    
}
