package Exceptions;

public class WrongTerrainType extends Exception {

    public WrongTerrainType() {
    }

    public WrongTerrainType(String msg) {
	super("Wrong terrain type: " + msg);
    }
}
