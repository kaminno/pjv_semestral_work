package Model.Items;

public enum EquipmentType {
    HEAD("head"),
    CHEST("chest"),
    HANDS("hands"),
    LEGS("legs"),
    FEET("feet"),
    MAIN_HAND("main hand"),
    SECOND_HAND("second hand"),
    BAG("bag");

    public final String label;

    private EquipmentType(String label) {
	this.label = label;
    }
}
