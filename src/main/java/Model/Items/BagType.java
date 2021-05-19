package Model.Items;

/**
 *
 * @author honzuna
 */
public enum BagType {

    /**
     *
     */
    BAG("bag");

    /**
     *
     */
    public final String label;

    private BagType(String label) {
	this.label = label;
    }
}
