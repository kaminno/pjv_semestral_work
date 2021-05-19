package Exceptions;

/**
 *
 * @author honzuna
 */
public class ItemNotUsableNowException extends Exception {

    public ItemNotUsableNowException() {
    }

    /**
     *
     * @param msg
     */
    public ItemNotUsableNowException(String msg) {
	super("Item is not usable now, number of uses is 0: " + msg);
    }
}
