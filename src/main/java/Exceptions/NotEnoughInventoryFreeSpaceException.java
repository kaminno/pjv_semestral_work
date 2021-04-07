package Exceptions;

public class NotEnoughInventoryFreeSpaceException extends Exception{

    public NotEnoughInventoryFreeSpaceException(String message) {
	super(message);
    }
}
