package Exceptions;

/**
 *
 * @author honzuna
 */
public class ItemNotEquipedException extends Exception {

    /**
     *
     * @param message
     */
    public ItemNotEquipedException(String message) {
	super("Item is not equiped yet: " + message);
    }
}
