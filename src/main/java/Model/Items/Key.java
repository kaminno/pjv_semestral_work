package Model.Items;

public class Key extends UsableItem {

    private final boolean universal;
    private int doorId;

    public Key(boolean universal, int doorId, String name, int weight, int totalUseNumber) {
	super(name, weight, totalUseNumber);
	this.universal = universal;
	this.doorId = doorId;
    }
}
