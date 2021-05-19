package Model.Terrains;

/**
 *
 * @author honzuna
 */
public enum TerrainType {

    /**
     *
     */
    GRASS("grass"),

    /**
     *
     */
    LOAM("loam"),

    /**
     *
     */
    SAND("sand"),

    /**
     *
     */
    WATER("water"),

    /**
     *
     */
    ROCK("rock"),

    /**
     *
     */
    WALL("wall"),

    /**
     *
     */
    BLANC("blanc");

    private final String name;

    private TerrainType(String name) {
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
