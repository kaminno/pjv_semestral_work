package Exceptions;

public class WrongEquipmentTypeForWearableItemException extends Exception {

    public WrongEquipmentTypeForWearableItemException() {
    }

    public WrongEquipmentTypeForWearableItemException(String msg) {
	super("Wrong EquipmentType: " + msg);
    }
}
