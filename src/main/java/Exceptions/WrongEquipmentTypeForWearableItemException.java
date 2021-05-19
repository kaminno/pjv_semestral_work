package Exceptions;

public class WrongEquipmentTypeForWearableItemException extends Exception {

    public WrongEquipmentTypeForWearableItemException() {
    }

    /**
     *
     * @param msg
     */
    public WrongEquipmentTypeForWearableItemException(String msg) {
	super("Wrong EquipmentType: " + msg);
    }
}
