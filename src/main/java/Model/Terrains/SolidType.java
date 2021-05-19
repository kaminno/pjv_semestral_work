package Model.Terrains;

/**
 *
 * @author honzuna
 */
public enum SolidType {

    /**
     *
     */
    ROCK("rock"),

    /**
     *
     */
    WALL("wall");

    private final String name;

    private SolidType(String name) {
	this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }
}
