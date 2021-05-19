package Exceptions;

public class ItemEquipedYetException extends Exception {

    public ItemEquipedYetException(String message) {
	super("Item is eqiup yet: " + message);
    }
}
