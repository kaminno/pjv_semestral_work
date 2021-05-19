package Model.Items;

/**
 *
 * @author honzuna
 */
public enum WeaponType {

    /**
     *
     */
    MAIN_HAND("main hand"),

    /**
     *
     */
    SECOND_HAND("second hand");

    /**
     *
     */
    public final String label;

    private WeaponType(String label) {
	this.label = label;
    }
}
