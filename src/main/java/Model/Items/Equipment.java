package Model.Items;

import Exceptions.ItemEquipedYetException;
import Exceptions.ItemNotEquipedException;
import java.util.EnumMap;

public class Equipment {
    private final EnumMap<EquipmentType, WearableItem> equipment = new EnumMap(EquipmentType.class);

    public Equipment() {
	for (EquipmentType e : EquipmentType.values()){
	    equipment.put(e, null);
	}
    }

    public EnumMap<EquipmentType, WearableItem> getEquipment(){
	return equipment;
    }
    
    public WearableItem getPieceOfEquipment(EquipmentType position){
	return equipment.get(position);
    }
    
    public void add(WearableItem item) throws ItemEquipedYetException{
	if (!item.isEquiped()){
	    equipment.put(item.getType(), item);
	    item.setEquiped(true);
	}
	else{
	    throw new ItemEquipedYetException("Item is equiped yet!");
	}
    }
    
    public void remove(WearableItem item) throws ItemNotEquipedException{
	if (item.isEquiped()){
	    equipment.put(item.getType(), null);
	    item.setEquiped(false);
//	    if (item instanceof Gear){
//		Gear g = (Gear)item;
//		for (EquipmentType e : EquipmentType.values()){
//		    if (e.label.equals(g.getType().label)){
//			equipment.put(e, null);
//			item.setEquiped(false);
//		    }
//		}
//	    }
//	    else if (item instanceof Weapon){
//		Weapon w = (Weapon)item;
//		for (EquipmentType e : EquipmentType.values()){
//		    if (e.label.equals(w.getType().label)){
//			equipment.put(e, null);
//			item.setEquiped(false);
//		    }
//		}
//	    }
//	    else if (item instanceof Bag){
//		Bag b = (Bag)item;
//		for (EquipmentType e : EquipmentType.values()){
//		    if (e.label.equals(b.getType().label)){
//			equipment.put(e, null);
//			item.setEquiped(false);
//		    }
//		}
//	    }
//	    else{
//		throw new IllegalArgumentException();
//	    }
	}
	else{
	    throw new ItemNotEquipedException("Can not remove item that is not equiped!");
	}
    }
}
