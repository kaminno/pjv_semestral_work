package Exceptions;

public class ItemNotEquipedException extends Exception{

    public ItemNotEquipedException(String message) {
	super("Item is not equiped yet: " + message);
    }
}
