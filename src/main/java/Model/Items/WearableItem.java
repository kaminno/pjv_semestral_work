package Model.Items;

/**
 * wearable item class
 * @author honzuna
 */
public class WearableItem extends Item {

    private final EquipmentType type;
    private boolean equiped = false;

    /**
     *
     * @param name
     * @param weight
     * @param type
     */
    public WearableItem(String name, int weight, EquipmentType type) {
	super(name, weight);
	this.type = type;
    }

    /**
     *
     * @return
     */
    public boolean isEquiped() {
	return equiped;
    }

    /**
     *
     * @param equiped
     */
    public void setEquiped(boolean equiped) {
	this.equiped = equiped;
    }

    /**
     *
     * @return
     */
    public EquipmentType getType() {
	return type;
    }
}
