package Exceptions;

public class NotEnoughInventoryFreeSpaceException extends Exception{

    public NotEnoughInventoryFreeSpaceException(String message) {
	super("Inventory is full - " + message);
    }
}
