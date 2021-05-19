package Model.Items;

/**
 * key class
 * @author honzuna
 */
public class Key extends UsableItem {

    private final boolean universal;
    private int doorId;

    /**
     *
     * @param universal
     * @param doorId
     * @param name
     * @param weight
     * @param totalUseNumber
     */
    public Key(boolean universal, int doorId, String name, int weight, int totalUseNumber) {
	super(name, weight, totalUseNumber);
	this.universal = universal;
	this.doorId = doorId;
    }
}
