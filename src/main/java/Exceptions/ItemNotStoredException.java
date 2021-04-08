package Exceptions;

public class ItemNotStoredException extends Exception {

    public ItemNotStoredException(String message) {
	super("Item is not stored: " + message);
    }
}
