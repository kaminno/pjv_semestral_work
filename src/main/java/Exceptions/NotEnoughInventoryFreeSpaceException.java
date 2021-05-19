package Exceptions;

public class NotEnoughInventoryFreeSpaceException extends Exception {

    /**
     *
     * @param message
     */
    public NotEnoughInventoryFreeSpaceException(String message) {
	super("Inventory is full - " + message);
    }
}
