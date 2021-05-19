package Exceptions;

/**
 *
 * @author honzuna
 */
public class WrongTerrainType extends Exception {

    public WrongTerrainType() {
    }

    /**
     *
     * @param msg
     */
    public WrongTerrainType(String msg) {
	super("Wrong terrain type: " + msg);
    }
}
