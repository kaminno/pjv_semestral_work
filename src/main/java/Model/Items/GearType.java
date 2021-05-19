package Model.Items;

/**
 *
 * @author honzuna
 */
public enum GearType {

    /**
     *
     */
    HEAD("head"),

    /**
     *
     */
    CHEST("chest"),

    /**
     *
     */
    HANDS("hands"),

    /**
     *
     */
    LEGS("legs"),

    /**
     *
     */
    FEET("feet");

    /**
     *
     */
    public final String label;

    private GearType(String label) {
	this.label = label;
    }
}
