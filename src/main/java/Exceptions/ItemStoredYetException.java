package Exceptions;

public class ItemStoredYetException extends Exception {

    public ItemStoredYetException() {
    }

    public ItemStoredYetException(String msg) {
	super("Item stored yet: " + msg);
    }
}
