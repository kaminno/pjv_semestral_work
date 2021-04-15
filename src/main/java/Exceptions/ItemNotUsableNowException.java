package Exceptions;

public class ItemNotUsableNowException extends Exception {

    public ItemNotUsableNowException() {
    }

    public ItemNotUsableNowException(String msg) {
	super("Item is not usable now, number of uses is 0: " + msg);
    }
}
